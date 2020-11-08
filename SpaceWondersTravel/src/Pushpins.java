import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import animation.AbstractAnimation;

public class Pushpins extends OnscreenObject {
	
    // The animation that this object is part of.
    private AbstractAnimation animation;
    
    BufferedImage img = null;
    Graphics g;
    
   int pinWidth;
   int pinHeight;
   
   int x;
   int y;
	
	public Pushpins(AbstractAnimation animation, int x, int y) {
		this.animation = animation;
		this.x = x;
		this.y =y;
		
		  try {
	        	img = ImageIO.read(new File("red-pegasus-with-circle-620x349.png"));
	        	
	        } 
	        catch (IOException e) {
	        }
	        
	        pinWidth = img.getWidth();
	        pinHeight = img.getHeight();
	        g = img.getGraphics();
	}


	@Override
	public void paint(Graphics2D g) {
		 g.drawImage(img, x, y, (int)(pinWidth *0.2), (int)(pinHeight *0.2), null);
		
	}

	@Override
	protected void nextFrame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
