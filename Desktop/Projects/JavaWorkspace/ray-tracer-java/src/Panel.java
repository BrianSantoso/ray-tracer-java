import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel{

	private Image image;
	
	public void draw(Image image){
		this.image = image;
		this.repaint();
		
	}

	public void paintComponent(Graphics g){
		
		for(int x = 0; x < image.getWidth(); x++){
			for(int y = 0; y < image.getHeight(); y++){
				
				Pixel pixel = image.getPixel(x, y);
				g.setColor(new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
				g.fillRect(x, image.getHeight() - y - 1, 1, 1);
				
			}
		}
		
		g.fillRect(0, 0, 100, 100);
			
		
	}
	
	public Panel(Image image){
		
		this.image = image;
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		
	}
	
}
