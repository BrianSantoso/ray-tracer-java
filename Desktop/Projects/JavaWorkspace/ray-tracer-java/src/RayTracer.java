import javax.swing.JFrame;

/*
 * 
 * Ray Tracer with ambient, diffuse, and specular lighting (Phong reflection model), light occlusion,
 *  and variable degrees of reflection. Written by Brian Santoso.
 *  
 */

public class RayTracer {

	public static void main(String[] args){
		
		Scene scene = new Scene();
		scene.setAmbient( new Vector3f(0.05f, 0.05f, 0.05f) );
		Camera camera = new Camera(new Vector3f(0, 2, 0), new Vector3f(0, 0, 1).normalize(), 90);
		Renderer renderer = new Renderer(10, true);
		Image image = new Image(800, 800);
		
		
		scene.addShape( new Plane(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0), 0.5f, 0.5f, 1.0f, 0.5f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				int size = 1;
				int xSign = Math.floor(coord.x / size) % 2 == 0 ? 1 : -1;
				int zSign = Math.floor(coord.z / size) % 2 == 0 ? 1 : -1;
				
				
				return xSign * zSign > 0 ? new Vector3f(0, 0, 0) : new Vector3f(255, 255, 255);		
			}
			
		}) );
		
		scene.addShape(new Sphere(new Vector3f(0, 1, 5), 1, 1.0f, 1f, 4f, 0.4f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return new Vector3f(255, 215, 0);
				
			}
			
		}));
		
		scene.addShape( new Plane(new Vector3f(0, 105, 0), new Vector3f(0, -1, 0), 0.5f, 0.5f, 1.0f, 0.0f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return new Vector3f(255, 255, 255);
				
			}
			
		}) );
		
		scene.addShape(new Sphere(new Vector3f(-2.5f, 5, 9), 2, 1.0f, 1.0f, 8f, 0.4f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return new Vector3f(0, 255, 255);
				
			}
			
		}));
		
		scene.addShape(new Sphere(new Vector3f(7.5f, 6, 20), 6, 0.8f, 1.0f, 8f, 0.5f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return new Vector3f(100, 255, 127);
				
			}
			
		}));
		
		scene.addShape(new Sphere(new Vector3f(-7.5f, 9, 12f), 1.5f, 0.8f, 0.8f, 5f, 0.2f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return new Vector3f(123, 50, 213);
				
			}
			
		}));
		
		scene.addShape(new Sphere(new Vector3f(6f, 2, 6.5f), 1, 0.8f, 0.8f, 3f, 0.5f, new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return new Vector3f(255, 127, 100);
				
			}
			
		}));
		
		
		
		

		/*
		
		for(int abc = 0; abc < 50; abc++){
			
			scene.addShape( new Sphere(new Vector3f(MathUtils.getRan(-20, 20), MathUtils.getRan(1, 10), MathUtils.getRan(10, 30)), 
										MathUtils.getRan(0.5f, 2f), 
										MathUtils.getRan(0, 1), 
										MathUtils.getRan(0, 1),
										MathUtils.getRan(1, 8),
										MathUtils.getRan(0.15f, 0.85f), new Texture(){
											
											private Vector3f color = new Vector3f(MathUtils.getRan(0, 255), MathUtils.getRan(0, 255), MathUtils.getRan(0, 255));
				
											@Override
											public Vector3f function(Vector3f coord){
												
												return this.color;
												//return new Vector3f(255, 215, 0);
												//return new Vector3f(MathUtils.getRan(0, 255), MathUtils.getRan(0, 255), MathUtils.getRan(0, 255));
												
											}
				
										}) );
			
			System.out.println(scene.getShapes(3 + abc));
			
		}
		*/
		
		scene.addLight( new Light(new Vector3f(-75f, 100, 5f), new Vector3f(1.05f, 1.05f, 0.6f), new Vector3f(1.0f, 1.0f, 1.0f)) );
		//scene.addLight( new Light(new Vector3f(75f, 100, 5f), new Vector3f(1.3f, 1.3f, 1.3f), new Vector3f(1.0f, 1.0f, 1.0f)) );
		
		image = renderer.render(scene, camera, image);
		
		JFrame frame = new JFrame("Ray Tracer");
		Panel panel = new Panel(image);
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
