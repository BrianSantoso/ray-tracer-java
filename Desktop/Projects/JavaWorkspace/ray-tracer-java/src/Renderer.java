import java.util.ArrayList;

public class Renderer {
	
	public static final float EPSILON = 1e-6f;	//Arbitrarily small value close to zero
	
	private int maxRecursionDepth;		//Max amount of recursion allowed
	private boolean lightOcclusion;	//Determines whether shadows are calculated or not
	
	/**
	 * Default constructor
	 * Instantiates a new Renderer object with maxRecursionDepth of 5,
	 * and lightOcclusion on
	 * 
	 */
	public Renderer(){
		
		this.maxRecursionDepth = 5;
		this.lightOcclusion = true;
		
	}
	
	/**
	 * Constructs a new Renderer object with given maxRecursionDepth,
	 * and lightOcclusion
	 * 
	 * @param maxRecursionDepth		Max amount of recursion allowed
	 * @param lightOcclusion		Determines whether shadows are calculated or not
	 */
	public Renderer(int maxRecursionDepth, boolean lightOcclusion){
		
		this.maxRecursionDepth = maxRecursionDepth;
		this.lightOcclusion = lightOcclusion;
		
	}
	
	/**
	 * Generates rendered image
	 * 
	 * @param scene		Scene object being rendered containing the Shapes and Lights
	 * @param camera	Camera used to render scene
	 * @param image		Image object with specified dimensions
	 * @return			new Image object containing 2 dimensional array of Pixel objects
	 * 					with calculated RGB values
	 */
	public Image render(Scene scene, Camera camera, Image image){
		
		// Should make deep clone
		Image newImage = new Image(image.getWidth(), image.getHeight());
		
		for(int x = 0; x < image.getWidth(); x++){
			
			for(int y = 0; y < image.getHeight(); y++){
				
				Vector3f projectionPoint = this.getProjectionPoint(x, y, camera, image);

	
				Ray primaryRay = new Ray(camera.getPos(), projectionPoint.minus(camera.getPos()).normalize());
				Vector3f rgb = this.getPixelColor(primaryRay, scene, camera, 0);	// Begin recursive ray tracing!
				
				newImage.setPixel(x, y, new Pixel(
						
					(int) Math.min(rgb.x, 255),
					(int) Math.min(rgb.y, 255),
					(int) Math.min(rgb.z, 255) 
						
				));
				
			}
			
		}
		
		return newImage;
	}
	
	/**
	 * Retrieves color of a pixel
	 * 
	 * @param ray				Primary being projected into a scene
	 * @param scene				Scene the ray is projected into
	 * @param camera			Camera viewing pixel
	 * @param recursionDepth	The index of recursion
	 * @return					Vector3f containing red, green, and blue values
	 */
	public Vector3f getPixelColor(Ray ray, Scene scene, Camera camera, int recursionDepth){
		
		
		CollisionPackage collisionPackage = this.rayCast(ray, scene.getShapes());		
		Vector3f rgb = this.getRayColor(collisionPackage, scene, camera);
		
		if(collisionPackage.intersection.valid){
			
			if(recursionDepth < this.maxRecursionDepth){
				
				if(scene.getShapes(collisionPackage.shapeIndex).getReflectivity() > 0){
					
					Vector3f reflectedDirecton = ray.direction.reflect(collisionPackage.intersection.normal);
					
					Ray reflectedRay = new Ray(collisionPackage.intersection.pos.plus(reflectedDirecton.scale(EPSILON)), reflectedDirecton);
					
					rgb =  rgb.scale( 1 - scene.getShapes(collisionPackage.shapeIndex).getReflectivity() ).plus( this.getPixelColor(reflectedRay, scene, camera, recursionDepth + 1 )
							  .scale(scene.getShapes(collisionPackage.shapeIndex).getReflectivity()) );
				
				}
				
			}
			
		}
		
		
		
		return rgb;
		
	}
	
	/**
	 * Gets color of ray
	 * 
	 * @param collisionPackage		CollisionPackage object containing index of an object in the scene's Shape array, and Intersection object containing if the intersection exists, the position of the intersection, the surfaceNormal of the Shape at the intersection, and the squared distance from the intersection's position to the previous intersection's position
	 * @param scene					Scene object being rendered containing the Shapes and Lights
	 * @param camera				Camera used to render scene
	 * @return
	 */
	public Vector3f getRayColor(CollisionPackage collisionPackage, Scene scene, Camera camera){
		
		Vector3f illumination = illuminate(collisionPackage, scene, camera);
		
		Vector3f rgb = scene.getShapes(collisionPackage.shapeIndex).texture.function(collisionPackage.intersection.pos);
		
		rgb = new Vector3f( 
				
			rgb.x * illumination.x,
			rgb.y * illumination.y,
			rgb.z * illumination.z
							
		);
		
		return rgb;
		
	}
	
	/**
	 * Calculates Vector3f containing illumination scalars for the red, green, blue color channels
	 * 
	 * @param collisionPackage		CollisionPackage object containing index of an object in the scene's Shape array, and Intersection object containing if the intersection exists, the position of the intersection, the surfaceNormal of the Shape at the intersection, and the squared distance from the intersection's position to the previous intersection's position
	 * @param scene					Scene object being rendered containing the Shapes and Lights
	 * @param camera				Camera used to render scene
	 * @return						Vector3f containing illumination scalars for the red, green, blue color channels
	 */
	public Vector3f illuminate(CollisionPackage collisionPackage, Scene scene, Camera camera){
		
		Vector3f ambientComponent = scene.getAmbient();
		Vector3f diffuseComponent = Vector3f.zero;
		Vector3f specularComponent = Vector3f.zero;
		
		if(collisionPackage.shapeIndex >= 0){
		
			for(int lightIndex = 0; lightIndex < scene.getLights().size(); lightIndex++){
				
				Ray shadowRay = new Ray(Vector3f.zero, Vector3f.zero);
				CollisionPackage shadowRayCollisionPackage = new CollisionPackage();
				
				if(this.lightOcclusion){
					
					shadowRay = new Ray(collisionPackage.intersection.pos.plus( collisionPackage.intersection.normal.scale(EPSILON) ),
										scene.getLights(lightIndex).getPos().minus(collisionPackage.intersection.pos).normalize());
					shadowRayCollisionPackage = rayCast(shadowRay, scene.getShapes());
				}
				
				if(this.lightOcclusion ? shadowRay.pos.getDistanceSquared(scene.getLights(lightIndex).getPos()) < shadowRayCollisionPackage.intersection.distanceSquared : true ){
					
					Vector3f diffuse = this.getDiffuse(scene, collisionPackage, lightIndex);
					Vector3f specular = this.getSpecular(scene, collisionPackage, lightIndex, camera);
					
					diffuseComponent = diffuseComponent.plus(diffuse);
					specularComponent = specularComponent.plus(specular);
					
				}
				
				
			}
		
		}
		
		
		
		return ambientComponent.plus(diffuseComponent).plus(specularComponent);
		
	}
	
	/**
	 * Calculates the intersection, of a Ray with a collection of Shapes, closest to the Ray's origin
	 * 
	 * @param ray		Ray the collection of Shapes is being intersected with
	 * @param shapes	ArrayList of Shapes being tested for intersection with Ray
	 * @return			CollisionPackage object containing index of an object in the scene's Shape array, and Intersection object containing if the intersection exists, the position of the intersection, the surfaceNormal of the Shape at the intersection, and the squared distance from the intersection's position to the Ray's origin
	 */
	public CollisionPackage rayCast(Ray ray, ArrayList<Shape> shapes){
		
		CollisionPackage champ = new CollisionPackage();
		
		for(int rep = 0; rep < shapes.size(); rep++){
			
			Shape shape = shapes.get(rep);
			
			if( shape instanceof Plane ){
				
				Intersection intersection = ray.xPlane((Plane) shape);
				
				if(intersection.valid){
					
					if(intersection.distanceSquared < champ.intersection.distanceSquared){
						
						champ = new CollisionPackage(intersection, rep);
						
					}
					
				}
				
			} else if( shape instanceof Sphere ){
				
				Intersection intersection = ray.xSphere((Sphere) shape);
				
				if(intersection.valid){
					
					if(intersection.distanceSquared < champ.intersection.distanceSquared){
						
						champ = new CollisionPackage(intersection, rep);
						
					}
					
				}
				
			}
			
		}
		
		return champ;
		
	}
	
	/**
	 * Calculates the point (In 3D space) on the camera's ProjectionPlane in which a Ray is to be projected/shot through
	 * 
	 * @param x			x-coordinate on image
	 * @param y			y-coordinate on image
	 * @param camera	Camera used to render scene
	 * @param image		Image object containing a width and height
	 * @return			Vector3f object representing the point (In 3D space) on the camera's ProjectionPlane in which a Ray is to be projected/shot through
	 */
	public Vector3f getProjectionPoint(int x, int y, Camera camera, Image image){
		
		float normalizedXScalar = ( (float)(x) / (float)(image.getWidth()) * camera.getProjectionPlane().getWidth()) - camera.getProjectionPlane().getHalfWidth();
		float normalizedYScalar = ( (float)(y) / (float)(image.getHeight()) * camera.getProjectionPlane().getWidth()) - camera.getProjectionPlane().getHalfHeight();
		
		Vector3f xComponent = camera.getRight().scale(normalizedXScalar);
		Vector3f yComponent = camera.getUp().scale(normalizedYScalar);
		Vector3f zComponent = camera.getDirection();
		
		return xComponent.plus(yComponent).plus(zComponent).plus(camera.getPos());
		
	}
	
	/**
	 * Calculates the diffuse component of illumination
	 * 
	 * @param scene					Scene object being rendered containing the Shapes and Lights
	 * @param collisionPackage		CollisionPackage object containing index of object in the scene's shapes array,
	 * 								and the Intersection
	 * @param lightIndex			Index of the Light object in the scene's ArrayList of Shapes
	 * @return						Vector3f containing the diffuse scalars for the red, green, and blue color channels
	 */
	public Vector3f getDiffuse(Scene scene, CollisionPackage collisionPackage, int lightIndex){
		
		Light light = scene.getLights(lightIndex);
		Shape shape = scene.getShapes(collisionPackage.shapeIndex);
		
		
		// Normalized vector direction of light starting from intersection to light source
		Vector3f l = light.getPos().minus(collisionPackage.intersection.pos).normalize();
		
		return light.getDiffuse().scale(shape.getDiffuse() * Math.max(0, collisionPackage.intersection.normal.dot(l)));
		
	}

	/**
	 * Calculates the specular component of illumination
	 * 
	 * @param scene					Scene object being rendered containing the Shapes and Lights
	 * @param collisionPackage		CollisionPackage object containing index of object in the scene's shapes array,
	 * 								and the Intersection
	 * @param lightIndex			Index of the Light object in the scene's ArrayList of Shapes
	 * @param camera				Camera used to render scene
	 * @return						Vector3f containing the specular scalars for the red, green, and blue color channels
	 */
	public Vector3f getSpecular(Scene scene, CollisionPackage collisionPackage, int lightIndex, Camera camera){
		
		Light light = scene.getLights(lightIndex);
		Shape shape = scene.getShapes(collisionPackage.shapeIndex);
		
		// Normalized vector direction of light starting from intersection to light source
		Vector3f l = light.getPos().minus(collisionPackage.intersection.pos).normalize();
		
		// Reflection of l over the surface normal
		Vector3f r = l.reflect(collisionPackage.intersection.normal);
		
		// Direction from camera to the point of intersection
		Vector3f v = collisionPackage.intersection.pos.minus(camera.getPos()).normalize();
		
		
		return light.getSpecular().scale((float) (shape.getSpecular() * Math.pow(Math.max(0, r.dot(v)), shape.getShininess())));
		
	}
	
	
}
