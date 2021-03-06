import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import animation.AbstractAnimation;

/**
 * 
 * MHCStudent class would create a student object with mascots based on class year that will be controlled by the
 * player. The player would be able to rotate clockwise or anti-clockwise,
 * move back, move forward, and leave trails as the player moves around campus
 * 
 * @author AmyWang, Gloria, Anna, AJ
 *
 */
public class MHCStudent extends OnscreenObject {


    // The number of pixels to move on each frame of the animation
    private static final int moveAmount = 10;

    // The rotation angle for each rotation
    private static final double rotationAngle = Math.PI / 18;

    // The animation that this object is part of.
    private AbstractAnimation animation;

    // The class mascot of the student
    private Polygon studentMascot;

    // The starting left edge of the polygon
    private int x = 100;

    // The starting top of the polygon
    private int y = 100;

    // Initializes status of rotateClockwise to false
    private boolean rotateClockwise = false;

    // Initializes status of rotateAntiClockwise to false
    private boolean rotateAntiClockwise = false;

    // Initializes value of degree to 0
    private double degree = 0;
    
    BufferedImage img = null;
    Graphics g;
    
    int studentWidth;
    int studentHeight;

    /**
     * Creates the animated Student object
     *
     * @param animation - the animation this object is part of
     */
    public MHCStudent(AbstractAnimation animation) {
        this.animation = animation;
        
        try {
        	img = ImageIO.read(new File("red-pegasus-with-circle-620x349.png"));
        } 
        catch (IOException e) {
        }
        
        studentWidth = img.getWidth();
        studentHeight = img.getHeight();
        g = img.getGraphics();

      
    }

    /**
     * Draws a student mascot at its current location.
     *
     * @param g - the graphics context to draw on.
     */
    public void paint(Graphics2D g) {
    	g.drawImage(img, x, y, (int)(studentWidth *0.2), (int)(studentHeight *0.2), null);
    }

    /**
     * Makes sure the student is within the window of animation every frame
     */
    public void nextFrame() {
        wrapAround();
    }

    /**
     * Returns the shape after applying the current translation and rotation
     *
     * @return the shape located as we want it to appear
     */
    public Shape getShape() {

        // AffineTransform captures the movement and rotation we
        // want the shape to have
        AffineTransform oldPic = new AffineTransform();

        // x, y are where the origin of the shape will be. In this
        // case, this is the center of the triangle. See the constructor
        // to see where the points are.
        oldPic.translate(x, y);

        if (rotateClockwise) {
            degree += rotationAngle;
            rotateClockwise = false;
        } else if (rotateAntiClockwise) {
            degree -= rotationAngle;
            rotateAntiClockwise = false;
        }

        oldPic.rotate(degree);
        AffineTransform newPic = oldPic;
        return newPic.createTransformedShape(studentMascot);
    }

    /**
     * Set rotateClockwise to true
     */
    public void rotateClockwise() {
        rotateClockwise = true;
    }

    /**
     * Set rotateAntiClockwise to true
     */
    public void rotateAntiClockwise() {
        rotateAntiClockwise = true;
    }

    /**
     * Returns the degree of rotation of student
     * 
     * @return degree - the rotation degree from the original degree
     */
    public double getShootDirection() {
        while (degree >= Math.PI) {
            degree -= Math.PI;
        }
        return degree;
    }

    /**
     * Makes the student disappear from the screen
     */
    public void disappear() {
        final int shift = 100;
        x = HolyokeMap.getWindowWidth() + shift;
        y = HolyokeMap.getWindowHeight() + shift;
    }

    /**
     * Makes the student vanish and reappear in a new random location
     */
    public void hyperspace() {
        int WINDOW_WIDTH = HolyokeMap.getWindowWidth();
        int WINDOW_HEIGHT = HolyokeMap.getWindowHeight();

        // FOR TESTING PURPOSES ONLY.
        if (WINDOW_WIDTH <= 0) {
            WINDOW_WIDTH = HolyokeMap.getWindowWidth();
        }
        // FOR TESTING PURPOSES ONLY.
        if (WINDOW_HEIGHT <= 0) {
            WINDOW_HEIGHT = HolyokeMap.getWindowHeight();
        }

        Random rand = new Random();
        x = rand.nextInt(WINDOW_WIDTH);
        y = rand.nextInt(WINDOW_HEIGHT);
    }

    /**
     * Returns a particle from the tip of the polygon shape
     * 
     * @return particle - TrailParticles class
     */
    
    public TrailParticles shoot() {
        TrailParticles particle = new TrailParticles(animation, x, y);
        return particle;
    }

    /**
     * Moves the student is a straight line with the according degree, updating
     * x and y coordinates
     */
    public void up() {
        y -= 5;
    }
    
    /**
     * Moves the student is a straight line with the according degree, updating
     * x and y coordinates
     */
    public void down() {
        y += 5;
    }
    
    /**
     * Moves the student in a straight line with the according degree, updating
     * x and y coordinates
     */
    public void right() {
        x += 5;
    }
    
    /**
     * Moves the student in a straight line with the according degree, updating
     * x and y coordinates
     */
    public void left() {
        x -= 5;
    }
    
    
    
    

    /**
     * Keeps the student from disappearing off the map. When the student hits
     * the edge of the screen it appears on the opposite edge, somewhat to the
     * left of where it went off the screen on the other end.
     */
    public void wrapAround() {
        // The width of the window, in pixels.
        int WINDOW_WIDTH = HolyokeMap.getWindowWidth();
        // The height of the window, in pixels.
        int WINDOW_HEIGHT = HolyokeMap.getWindowHeight();

        int offset = 1;

        // FOR TESTING PURPOSES ONLY
        if (WINDOW_WIDTH <= 0) {
            WINDOW_WIDTH = HolyokeMap.getWindowWidth();
        }
        // FOR TESTING PURPOSES ONLY
        if (WINDOW_HEIGHT <= 0) {
            WINDOW_HEIGHT = HolyokeMap.getWindowHeight();
        }

        if (x > WINDOW_WIDTH && WINDOW_WIDTH > 0) {
            x = x % WINDOW_WIDTH + offset;
        } else if (x < 0) {
            x = WINDOW_WIDTH + x + offset;
        }

        if (y > WINDOW_HEIGHT && WINDOW_HEIGHT > 0) {
            y = y % WINDOW_HEIGHT + offset;
        } else if (y < 0) {
            y = WINDOW_HEIGHT + y + offset;
        }
    }


}