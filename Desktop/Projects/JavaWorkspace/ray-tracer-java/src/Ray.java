
public class Ray {

	/**
	 * Instance fields are public
	 * because they are immutable anyways
	 */
	
	public final Vector3f pos;			// Vector3f representing Ray's origin/position/starting point
	public final Vector3f direction;	// Vector3f of length 1 representing Ray's direction
	
	/**
	 * Constructor
	 * Instantiates a new Ray object given the following parameters:
	 * 
	 * @param pos			Vector3f representing Ray's origin.position/starting point
	 * @param direction		Vector3f of length 1 representing Ray's direction
	 */
	public Ray(Vector3f pos, Vector3f direction){
		
		this.pos = pos;	
		this.direction = direction;
		
	}
	
	/**
	 * Given a ray and plane, finds if the 2 intersect, the point of intersection,
	 * surface normal of the plane, the squared distance between the ray's origin and
	 * the point of intersection
	 * 
	 * 
	 * @param plane		Read above
	 * @return			Intersection object containing the following information:
	 * 						if the intersection exists,
	 * 						the point of intersection,
	 * 						the surface normal of the plane,
	 * 						the squared distance between the ray's origin and the point of intersection
	 * 						
	 */
	public Intersection xPlane(Plane plane){
		
		float denom = this.direction.dot(plane.getNormal());
		
		// If the denominatoar is 0, return an invalid intersection
		if(denom == 0) return new Intersection();
		
		
		float t = plane.getPos().minus(this.pos).dot(plane.getNormal()) / denom;
		
		// If the scalar is negative, return an invalid intersection because
		// a ray only goes one direction.
		if(t < 0) return new Intersection();
		
		Vector3f pos = this.pos.plus( this.direction.scale(t) );
		
		return new Intersection(true, pos, plane.getNormal(), this.pos.getDistanceSquared(pos) );
			
		
		
		
		
	}
	
	/**
	 * Given a ray and a sphere, finds if the 2 intersect, the closest point of 
	 * intersection to the Ray's origin, the surface normal of the sphere at the point of intersection, and the squared
	 * distance between the ray's origin and the point of intersection
	 * 
	 * @param sphere	Read above
	 * @return			Intersection object containing the following information:
	 * 						if the intersection exists,
	 * 						the point of intersection,
	 * 						the surface normal of the sphere at the point of intersection,
	 * 						the squared distance between the ray's origin and the point of intersection
	 */
	public Intersection xSphere(Sphere sphere){
		
		Vector3f l = sphere.getPos().minus(this.pos);
		float tca = l.dot(this.direction);
		
		if(tca < 0) return new Intersection();
		
		float dSquared = l.dot(l) - tca*tca;
		float rSquared = sphere.getRadius() * sphere.getRadius();
		
		if(dSquared > rSquared) return new Intersection();
		
		float thc = (float) Math.sqrt(rSquared - dSquared);
		float t1 = tca + thc;
		float t2 = tca - thc;
		Vector3f pos1 = this.pos.plus(this.direction.scale(t1));
		Vector3f pos2 = this.pos.plus(this.direction.scale(t2));		
		float distanceSquared1 = this.pos.getDistanceSquared(pos1);
		float distanceSquared2 = this.pos.getDistanceSquared(pos2);
		
		if(distanceSquared1 > distanceSquared2){
			
			return new Intersection(
					true,
					pos2,
					pos2.minus(sphere.getPos()).normalize(),
					distanceSquared2);
			
		}
		
		return new Intersection(
				true,
				pos1,
				pos1.minus(sphere.getPos()).normalize(),
				distanceSquared1);
		
		
	}

	@Override
	public String toString() {
		return "Ray [pos=" + pos + ", direction=" + direction + "]";
	}
	
}
