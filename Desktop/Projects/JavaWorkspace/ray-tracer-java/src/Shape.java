
public class Shape {
	
	/**
	 * Instance fields
	 */
	
	private Vector3f pos;			//Vector3f containing Shape's position
	private float specular;		//Float [0, infinity] controlling intensity of Shape's highlights
	private float diffuse;			//Float [0, infinity] controlling intensity of Shape's normal shading
	private float shininess;		//Float [0, infinity] controlling size of Shape's highlights
	private float reflectivity;	//Float [0, 1] controlling percentage of color the Shape contributes after each reflection. Reflectivity of Shape
	public Texture texture;			//Instance of Texture interface containing a single user-defined method named function. Function determines color of object based on XYZ coordinates.
	
	/**
	 * Defualt Constructor
	 * Constructs a new Shape object with position at (0, 0, 0),
	 * specular, diffuse, shininess, and reflectivity of 0,
	 * and Texture whose function always returns black
	 */
	public Shape(){
		
		this.pos = Vector3f.zero;
		this.specular = 0;
		this.diffuse = 0;
		this.shininess = 0;
		this.reflectivity = 0;
		this.texture = new Texture(){
			
			@Override
			public Vector3f function(Vector3f coord){
				
				return Vector3f.zero;
			}
			
		};
		
	}
	
	/**
	 * Constructor
	 * Instantiates new Shape object given a position, and sets
	 * specular, diffuse, shininess, reflectivity to 0.5
	 * 
	 * @param pos	Vector3f representing Shape's position
	 */
	public Shape(Vector3f pos){
		
		this.pos = pos;
		this.specular = 0.5f;
		this.diffuse = 0.5f;
		this.shininess = 0.5f;
		this.reflectivity = 0.5f;
		
		
	}
	
	/**
	 * Constructor
	 * Instantiates new Shape oject given a position,
	 * specular constant, diffuse constant, shininess constant,
	 * reflectivity, and user-defined texture function
	 * 
	 * @param pos				Vector3f representing Shape's position
	 * @param specular			Float [0, infinity] controlling intensity of Shape's highlights
	 * @param diffuse			Float [0, infinity] controlling intensity of Shape's normal shading
	 * @param shininess			Float [0, infinity] controlling size of Shape's highlights
	 * @param reflectivity		Float [0, 1] controlling percentage of color the Shape contributes after each reflection. Reflectivity of Shape
	 * @param texture			Instance of Texture interface containing a single user-defined method named function. Function determines color of object based on XYZ coordinates.
	 */
	public Shape(Vector3f pos, float specular, float diffuse, float shininess, float reflectivity, Texture texture){
		
		this.pos = pos;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
		this.reflectivity = reflectivity;
		this.texture = texture;
		
		
	}
	
	/**
	 * Gets the Shape's position
	 * 
	 * @return		Vector3f containing the Shape's position
	 */
	public Vector3f getPos(){
		
		return this.pos;
		
	}
	
	/**
	 * Gets specular constant, diffuse constant, shininess, and reflectivity, respectively
	 * returns specular constant, diffuse constant, shininess, and reflectivity, respectively
	 */
	public float getSpecular(){ return this.specular; }
	public float getDiffuse(){ return this.diffuse; }
	public float getShininess(){ return this.shininess; }
	public float getReflectivity(){ return this.reflectivity; }

	@Override
	public String toString() {
		return "Shape [pos=" + pos + ", specular=" + specular + ", diffuse=" + diffuse + ", shininess=" + shininess
				+ ", reflectivity=" + reflectivity + "]";
	}

	
	
	
}
