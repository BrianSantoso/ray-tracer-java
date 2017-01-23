
public class CollisionPackage {

	/**
	 * Instance fields are public
	 * because they are immutable anyways
	 */
	public final Intersection intersection;		//Intersection object containing if the intersection exists, the position of the intersection, the surfaceNormal of the Shape at the intersection, and the squared distance from the intersection's position to some point
	public final int shapeIndex;					//index of an object in a scene's Shape array
	
	/**
	 * Default Constructor
	 * Instantiates a CollisionPackage object with an invalid intersection
	 */
	public CollisionPackage(){
		
		this.intersection = new Intersection();
		this.shapeIndex = -1;
		
	}
	
	/**
	 * Constructor
	 * Instantiates a CollisionPackage given an intersection and shapeIndex
	 * 
	 * @param intersection
	 * @param shapeIndex
	 */
	public CollisionPackage(Intersection intersection, int shapeIndex){
		
		this.intersection = intersection;		//Intersection object containing if the intersection exists, the position of the intersection, the surfaceNormal of the Shape at the intersection, and the squared distance from the intersection's position to some point
		this.shapeIndex = shapeIndex;			//index of an object in a scene's Shape array
		
	}
	
	@Override
	public String toString() {
		
		return "CollisionPackage [intersection=" + intersection + ", shapeIndex=" + shapeIndex + "]";
		
	}
	
	/*
	public Intersection getIntersection(){
		
		return this.intersection;
		
	}
	
	
	public int getShapeIndex(){
		
		return this.shapeIndex;
		
	}*/
}
