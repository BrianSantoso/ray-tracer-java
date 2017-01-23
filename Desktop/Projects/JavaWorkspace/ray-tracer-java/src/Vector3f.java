
public class Vector3f {
	
	public static final Vector3f zero = new Vector3f(0, 0, 0);
	public static final Vector3f infinity = new Vector3f(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
	public static final Vector3f up = new Vector3f(0, 1, 0);
	public static final Vector3f down = new Vector3f(0, -1, 0);
	public static final Vector3f right = new Vector3f(1, 0, 0);
	public static final Vector3f left = new Vector3f(-1, 0, 0);
	public static final Vector3f forward = new Vector3f(0, 0, 1);
	public static final Vector3f back = new Vector3f(0, 0, -1);
	

	/**
	 * 
	 * Instance Fields
	 * 
	 * Public because they are supposed to be immutable
	 * 
	 */
	public final float x;		//x component of the Vector
	public final float y;		//y component of the Vector
	public final float z;		//z component of the Vector
	
	
	/**
	 * Constructs a Vector with x, y, and z components
	 * 
	 * @param x		x component of the Vector
	 * @param y		y component of the Vector
	 * @param z		z component of the Vector
	 */
	public Vector3f(float x, float y, float z){
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	/**
	 * 
	 * Instance Methods
	 * 
	 */
	
	/**
	 * Returns the sum of 2 Vectors
	 * 
	 * 
	 * @param a		A Vector3f being added
	 * @return		The sum of 2 Vectors
	 */
	public Vector3f plus(Vector3f a){
		
		return new Vector3f(this.x + a.x, this.y + a.y, this.z + a.z);
		
	}
	
	/**
	 * Returns the difference of 2 Vectors
	 * 
	 * @param a		A Vector3f being subtracted
	 * @return		The difference of 2 Vectors
	 */
	public Vector3f minus(Vector3f a){
		
		return new Vector3f(this.x - a.x, this.y - a.y, this.z - a.z);
		
	}
	
	/**
	 * Returns a new Vector whose components are the product of each component
	 * multiplied by a scalar
	 * 
	 * @param scalar	Amount Vector is scaled by
	 * @return			new Vector whose components are the product of each component
	 * 					multiplied by a scalar
	 */
	public Vector3f scale(float scalar){
		
		return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
		
	}
	
	/**
	 * Returns the dot product (scalar product) of 2 Vectors
	 * 
	 * @param a		Vector being a dotted with
	 * @return		The dot product (scalar product) of 2 Vectors
	 */
	public float dot(Vector3f a){
		
		return this.x * a.x + this.y * a.y + this.z * a.z;
		
	}
	
	/**
	 * Returns the cross product (vector product) of 2 Vectors
	 * 
	 * @param a		Vector being crossed with
	 * @return		The cross product of 2 Vectors
	 */
	public Vector3f cross(Vector3f a){
		
		return new Vector3f(
				
				this.y * a.z - this.z * a.y,
				this.z * a.x - this.x * a.z,
				this.x * a.y - this.y * a.x
				
		);
		
	}
	
	/**
	 * Normalizes a Vector
	 * 
	 * @return		Unit Vector (Vector with length 1)
	 */
	public Vector3f normalize(){
		
		float magnitude = this.getMagnitude();
		return this.scale(1/magnitude);	//should use fast inverse square root instead
		
		
		/*
		float invMag = MathUtils.invSqrt(this.getMagnitudeSquared());
		return this.scale(invSqrt);
		*/
	}
	
	/**
	 * Finds the squared distance between 2 (point) Vectors 
	 * 
	 * @param a		Vector being compared
	 * @return		The squared distance between 2 (point) Vectors
	 */
	public float getDistanceSquared(Vector3f a){
		
		return (this.x - a.x) * (this.x - a.x) + (this.y - a.y) * (this.y - a.y) + (this.z - a.z) * (this.z - a.z);
		
	}
	
	/**
	 * Finds the distance between 2 (point) vectors
	 * 
	 * @param a		Vector being compared
	 * @return		The distance between 2 (point) Vectors
	 */
	public float getDistance(Vector3f a){
		
		return (float) Math.sqrt( this.getDistanceSquared(a) );
	}
	
	/**
	 * Gets the squared magnitude (length) of a Vector
	 * 
	 * @return		The squared magnitude of a Vector
	 */
	public float getMagnitudeSquared(){
		
		return this.dot(this);
	}
	
	/**
	 * Gets the magnitude (length) of a Vector
	 * 
	 * @return		The magnitude of a Vector
	 */
	public float getMagnitude(){
		
		return (float) Math.sqrt( this.getMagnitudeSquared() );
	}
	
	/**
	 * Reflects Vector across a surface normal
	 * 
	 * @param normal	Unit Vector (Vector with length 1) indicating a direction
	 * 					which is being reflected about
	 * @return			Reflected Vector accross a normal
	 */
	public Vector3f reflect(Vector3f normal){
		
		return this.minus( normal.scale(2 * this.dot(normal)) );
		
	}
	
	/**
	 * Projects Vector onto another
	 * 
	 * @param a		Vector being projected onto
	 * @return		Vector projection of Vector onto a
	 */
	public Vector3f project(Vector3f a){
		
		return a.scale(this.dot(a) / a.getMagnitudeSquared());
	}
	
	/**
	 * Checks if 2 vectors are equal, with a small margin of error
	 * 
	 * @param a		Vector being compared
	 * @return		true, if Vectors are equal with a small margin of error
	 * 				false, if Vectors are not equal with a small margin of error
	 */
	public boolean equals(Vector3f a){
		
		return
				
			Math.abs( this.x - a.x ) < Renderer.EPSILON &&
			Math.abs( this.y - a.y ) < Renderer.EPSILON &&
			Math.abs( this.z - a.z ) < Renderer.EPSILON;
		
	}
	
	/**
	 * Converts Vector to String containing its x, y, and z components in the format
	 * "<x, y, z>"
	 * 
	 * @return		String containing Vector's x, y, and z components in the format
	 * 				"<x, y, z>"
	 */
	@Override
	public String toString(){
		
		return "<" + x + ", " + y + ", " + z + ">";
		
	}
	
}
