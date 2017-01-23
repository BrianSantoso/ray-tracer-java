
public class Camera {

	private Vector3f pos;						// Vector3f representing the Camera's position
	private Vector3f direction;				// Vector3f of length 1 representing the camera's viewing direction
	private Vector3f up;						// Vector3f of length 1 representing the camera's up direction
	private Vector3f right;					// Vector3f of length 1 representing the camera's right direction
	private float fov;							// Camera's field of view (degrees) Standard is 90
	private ProjectionPlane projectionPlane;	// Camera's viewing rectangle in which the scene is "projected" onto
	
	/**
	 * Default constructor
	 * Instantiates a new Camera object with
	 * 
	 * pos <0, 0, 0>
	 * direction <0, 0, 1>
	 * fov 90
	 * 
	 * Then creates a new ProjectionPlane
	 * 
	 */
	public Camera(){
		
		this.pos = new Vector3f(0, 0, 0);
		this.direction = Vector3f.forward;
		this.fov = 90;
		this.projectionPlane = new ProjectionPlane();
		
		this.update();
		
	}
	
	/**
	 * Constructor
	 * Instantiates a new Camera object given a position, direction, field of view
	 * 
	 * @param pos			Vector3f object representing the objects position
	 * @param direction		Vector3f object with length 1, indicating the camera's direction
	 * @param fov			Field of View
	 */
	public Camera(Vector3f pos, Vector3f direction, float fov){
		
		this.pos = pos;
		this.direction = direction;
		this.fov = fov;
		this.projectionPlane = new ProjectionPlane();
		
		this.update();
		
	}
	
	/**
	 * Recalculates the camera's up and right direction relative
	 * to the camera's direction
	 * 
	 */
	public void update(){
		
		this.up = Vector3f.up;
		this.right = this.up.cross(this.direction);
		this.up = this.direction.cross(this.right);
		
		this.projectionPlane.update();
		
	}
	
	/**
	 * Gets the camera's ProjectionPlane object
	 * 
	 * @return		Camera's ProjectionPlane object
	 */
	public ProjectionPlane getProjectionPlane(){
		
		return this.projectionPlane;
		
	}
	
	/**
	 * Gets the camera's right direction
	 * 
	 * @return		Vector3f of length 1 representing camera's right direction
	 */
	public Vector3f getRight(){
		
		return this.right;
		
	}
	
	/**
	 * Gets the camera's up direction
	 * 
	 * @return		Vector3f of length 1 representing camera's up direction
	 */
	public Vector3f getUp(){
		
		return this.up; 
		
	}
	
	/**
	 * Gets the camera's direction
	 * 
	 * @return		Vector3f of length 1 representing camera's viewing direction
	 */
	public Vector3f getDirection(){ 
		
		return this.direction;
		
	}
	
	/**
	 * Gets the camera's position
	 * 
	 * @return		Vector3f representing camera's position
	 */
	public Vector3f getPos(){
		
		return this.pos; 
		
	}
	
	
	class ProjectionPlane {
		
		private float halfWidth;	// Half of the viewing rectangle's width
		private float halfHeight;	// Half of the viewing rectangle's height
		private float width;		// Viewing rectangle's width
		private float height;		// Viewing rectangle's height
		
		public ProjectionPlane(){
			
			this.update();
			
		}
		
		/**
		 * Updates the viewing rectangle's dimensions depending on the camera's
		 * viewing direction and field of view
		 * 
		 * 
		 */
		public void update(){
			
			//
			// Camera's direction should be a unit vector, so calculating its length is redundant
			//
			//float directionMagnitude = direction.getMagnitude();
			//this.halfWidth = (float) (Math.tan( MathUtils.d2r(fov/2) ) * directionMagnitude);
			//this.halfHeight = (float) (Math.tan( MathUtils.d2r(fov/2) ) * directionMagnitude);
			
			this.halfWidth = (float) Math.tan( MathUtils.d2r(fov/2) );
			this.halfHeight = (float) Math.tan( MathUtils.d2r(fov/2) );
			
			this.width = halfWidth * 2;
			this.height = halfHeight * 2;
			
		}
		
		/**
		 * I'm too lazy to comment all these getters but
		 * They all do pretty much the same thing:
		 * Returns the camera's width, height, halfWidth, or halfHeight,
		 * respectively
		 * 
		 */
		public float getWidth(){ return this.width; }
		public float getHeight(){ return this.height; }
		public float getHalfWidth(){ return this.halfWidth; }
		public float getHalfHeight(){ return this.halfHeight; }
		
		
		@Override
		public String toString() {
			return "ProjectionPlane [halfWidth=" + halfWidth + ", halfHeight=" + halfHeight + ", width=" + width
					+ ", height=" + height + "]";
		}
		
		
		
	}


	@Override
	public String toString() {
		return "Camera [pos=" + pos + ", direction=" + direction + ", up=" + up + ", right=" + right + ", fov=" + fov
				+ ", projectionPlane=" + projectionPlane + "]";
	}
	
	
}
