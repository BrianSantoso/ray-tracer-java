
public class MathUtils {
	
	// Converts degrees to radians
	public static float d2r(float degrees){
		
		return degrees * (float) Math.PI / 180;
		
	}
	
	// Converts radians to degrees
	public static float r2d(float radians){
		
		return radians * 180 / (float) Math.PI;
		
	}
	
	public static float getRan(float lowerBound, float upperBound){
		
		return (float) (Math.random() * (upperBound - lowerBound) + lowerBound);
		
	}
	
	public static float invSqrt(float x) {
		
		// Fast inverse square root
		
	    float xhalf = 0.5f * x;
	    int i = Float.floatToIntBits(x);
	    i = 0x5f3759df - (i >> 1);
	    x = Float.intBitsToFloat(i);
	    x *= (1.5f - xhalf * x * x);
	    return x;
	    
	}
	
}
