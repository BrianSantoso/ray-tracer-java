
public class Image {
	
	private final int width;
	private final int height;
	public Pixel[][] pixels;
	
	
	public Image(int width, int height){
		
		this.width = width;
		this.height = height;
		this.pixels = new Pixel[width][height];
		
		for(int x = 0; x < this.width; x++){
			for(int y = 0; y < this.height; y++){
			
				this.pixels[x][y] = new Pixel();
				
			}
		}
		
	}
	
	public Pixel getPixel(int x, int y){
		
		return this.pixels[x][y];
		
	}
	
	public void setPixel(int x, int y, Pixel pixel){
		
		this.pixels[x][y] = pixel;
		
	}
	
	public int getWidth(){ return this.width; }
	public int getHeight(){ return this.height; }

}
