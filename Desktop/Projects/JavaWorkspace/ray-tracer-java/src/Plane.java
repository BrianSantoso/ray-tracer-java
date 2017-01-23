
public class Plane extends Shape{

	/**
	 * Instance fields
	 */
	private Vector3f normal;	//Vector3f of length 1 representing the plane's orientation

	/**
	 * Constructor
	 * Instantiates a new Plane object given only a position and normal
	 * 
	 * @param pos		Vector3f containing position of Plane object
	 * @param normal	Vector3f of length 1 representing the plane's orientation/surface normal/normal/direction
	 */
	public Plane(Vector3f pos, Vector3f normal){
		
		super(pos);
		this.normal = normal;
		
	}
	
	
	/**
	 * Constructor
	 * Instantiates a new Plane object given a position, normal, specular constant
	 * diffuse constant, shininess, reflectivity, and user-defined texture function
	 * 
	 * @param pos				Vector3f containing position of Plane objects
	 * @param normal			Vector3f of length 1 representing the plane's orientation/surface normal/normal/direction
	 * @param specular			Float [0, infinity] controlling intensity of Shape's highlights
	 * @param diffuse			Float [0, infinity] controlling intensity of Shape's normal shading
	 * @param shininess			Float [0, infinity] controlling size of Shape's highlights
	 * @param reflectivity		Float [0, 1] controlling percentage of color the Shape contributes after each reflection. Reflectivity of Shape
	 * @param texture			Instance of Texture interface containing a single user-defined method named function. Function determines color of object based on XYZ coordinates.
	 */
	public Plane(Vector3f pos, Vector3f normal, float specular, float diffuse, float shininess, float reflectivity, Texture texture){
		
		super(pos, specular, diffuse, shininess, reflectivity, texture);
		this.normal = normal;
		
	}
	
	/**
	 * Gets the Plane's orientation/surface normal/normal/direction
	 * 
	 * @return		Vector3f of length 1 representing the plane's orientation/surface normal/normal/direction
	 */
	public Vector3f getNormal(){
		
		return this.normal;
		
		
	}
	
}
