
public class Pixel {

	private int red;
	private int green;
	private int blue;
	
	public Pixel(){
		
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		
	}
	
	public Pixel(int red, int green, int blue){
		
		this.red = red;
		this.green = green;
		this.blue = blue;	
		
	}
	
	public Pixel(Vector3f RGB){
		
		//
		// Loses precision, not recommended
		//
		this.setRGB(RGB);
		
	}
	
	public Vector3f getRGBVector(){
		
		return new Vector3f(this.red, this.green, this.blue);
		
	}
	
	public void setRGB(Vector3f RGB){
		
		//
		// Loses precision, not recommended
		//
		
		this.red = (int) RGB.x;
		this.green = (int) RGB.y;
		this.blue = (int) RGB.z;
		
	}
	
	public int getRed(){
		
		return this.red;
		
	}
	
	public int getGreen(){
		
		return this.green;
		
	}
	
	public int getBlue(){
		
		return this.blue;
		
	}

	public boolean equals(Pixel a){
		
		return this.red == a.getRed() &&
				this.green == a.getGreen() &&
				this.blue == a.getBlue();
		
	}
	
	@Override
	public String toString() {
		return "Pixel [red=" + red + ", green=" + green + ", blue=" + blue + "]";
	}
	
}
