import java.util.ArrayList;

public class Scene {

	/**
	 * Instance fields
	 */
	private ArrayList<Shape> shapes;		//ArrayList containing the scene's Shapes
	private ArrayList<Light> lights;		//ArrayList containing the scene's Lights
	private Vector3f ambient;				//Vector3f containing the ambient lighting for the red, green, and blue color channels. Ambient lighting is the natural light that permeates the entire scene
	
	
	/**
	 * (Default) Constructor
	 * Instatiates a new Scene object with empty Shape and Light ArrayLists
	 * and no ambient lighting
	 */
	public Scene(){
		
		this.shapes = new ArrayList<Shape>();
		this.lights = new ArrayList<Light>();
		this.ambient = Vector3f.zero;
		
	}
	
	public ArrayList<Shape> getShapes(){
		
		return this.shapes;
		
	}
	
	public Shape getShapes(int index){
		
		return index < 0 ? new Shape() : this.shapes.get(index);
		
	}
	
	public ArrayList<Light> getLights(){
		
		return this.lights;
		
	}
	
	public Light getLights(int index){
		
		return this.lights.get(index);
		
	}
	
	public void addShape(Shape shape){
		
		this.shapes.add( shape );
		
	}
	
	public void addLight(Light light){
		
		this.lights.add( light );
		
	}
	
	public Vector3f getAmbient(){
		
		return this.ambient;
		
	}
	
	public void setAmbient(Vector3f ambient){
		
		this.ambient = ambient;
		
	}
	
}
