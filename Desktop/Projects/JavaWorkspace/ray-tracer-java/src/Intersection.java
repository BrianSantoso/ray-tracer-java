
public class Intersection {
	
	/*
	 * Instance fields are public because they are
	 * immutable anyways.
	 * 
	 */

	public final boolean valid;			// true, if intersection is valid, false if intersection is invalid
	public final Vector3f pos;				// Vector3f representing the position of the intersection
	public final Vector3f normal;			// Vector3f of length 1 representing the surface normal of the shape at the intersections position
	public final float distanceSquared;	// the squared distance from the intersection's position to some other point
	
	/**
	 * Default Constructor
	 * Instantiates an invalid intersection
	 */
	public Intersection(){
		
		this.valid = false;
		this.pos = Vector3f.infinity;
		this.normal = Vector3f.zero;
		this.distanceSquared = Float.POSITIVE_INFINITY;
		
	}
	
	/**
	 * Constructor
	 * Instantiates an intersection given the following parameters:
	 * 
	 * @param valid				true, if intersection is valid, false if the intersection is invalid
	 * @param pos				Vector3f representing the position of the intersection
	 * @param normal			Vector3f of length 1 representing the surface normal of the shape at the intersections position
	 * @param distanceSquared	the squared distance from the intersection's position to some other point
	 */
	public Intersection(boolean valid, Vector3f pos, Vector3f normal, float distanceSquared){
		
		this.valid = valid;
		this.pos = pos;
		this.normal = normal;
		this.distanceSquared = distanceSquared;
		
	}

	@Override
	public String toString() {
		return "Intersection [valid=" + valid + 
							 ", pos=" + pos +
							 ", normal=" + normal +
							 ", distanceSquared=" + distanceSquared + "]";
	}
	
	
	
}
