
public class Sphere extends Shape{
	
	/**
	 * Instance fields
	 */
	private float radius;		//Radius of Sphere object

	/**
	 * Constructor
	 * Instantiates a Sphere object given only a position and radius
	 * 
	 * @param pos
	 * @param radius
	 */
	public Sphere(Vector3f pos, float radius){
		
		super(pos);
		this.radius = radius;
		
	}
	
	/**
	 * Constructor
	 * Instantiates a Sphere object given a position, radius, specular constant,
	 * diffuse, constant, shininess, reflectivity, and user-defined texture function.
	 * 
	 * See Shape class for more information
	 * 
	 * @param pos				Vector3f containing Sphere's position
	 * @param radius			Float [0, infinity] representing Sphere's radius
	 * @param specular			Float [0, infinity] controlling intensity of Shape's highlights
	 * @param diffuse			Float [0, infinity] controlling intensity of Shape's normal shading
	 * @param shininess			Float [0, infinity] controlling size of Shape's highlights
	 * @param reflectivity		Float [0, 1] controlling percentage of color the Shape contributes after each reflection. Reflectivity of Shape
	 * @param texture			Instance of Texture interface containing a single user-defined method named function. Function determines color of object based on XYZ coordinates.
	 */
	public Sphere(Vector3f pos, float radius, float specular, float diffuse, float shininess, float reflectivity, Texture texture){
		
		super(pos, specular, diffuse, shininess, reflectivity, texture);
		this.radius = radius;
		
	}
	
	public float getRadius(){
		
		return this.radius;
		
	}
}
