import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;

import animation.AbstractAnimation;

public class Pushpins extends OnscreenObject implements MouseListener{
	
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
	        	img = ImageIO.read(new File("pushypin.png"));
	        	
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
	
    public void pinPressed(MouseEvent e) {
    	int mouseX = e.getX();
        int mouseY = e.getY();
        
        
        if ((mouseX >= x
                && mouseX <= x +pinWidth)
                && (HolyokeMap.State == HolyokeMap.STATE.GAME)) {
            if (mouseY >= y && mouseY <= y + pinHeight) {
                System.out.println("OIJAEFWJSOJADFS");
            }
        }
        System.out.println("MOSE");
       
        
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


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
