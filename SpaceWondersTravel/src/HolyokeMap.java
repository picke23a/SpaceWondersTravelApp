import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Toolkit;

import animation.AbstractAnimation;


public class HolyokeMap extends AbstractAnimation implements KeyListener {
    // an instance of the game
    static HolyokeMap holyokeGame;
    
    //gets mouse input
    private static MouseInput mouseInput = new MouseInput();
    

    //window dimensions
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 900;
    
    
    /**
     * Keeps track of the state of the game
     *
     */
    public static enum STATE {
        MENU, GAME, ABOUT, LEVEL, GAMEOVER, ENTER_INIT, EXIT_SCREEN
    }
    
    public static STATE State = STATE.MENU;
    
 // all of these variables are used for different screens
    private Rectangle quitButton = new Rectangle(WINDOW_WIDTH / 2 + 200, 500,
            150, 50);

    private Rectangle aboutButton = new Rectangle(WINDOW_WIDTH / 2 - 70, 500,
            150, 50);

    private Rectangle playButton = new Rectangle(WINDOW_WIDTH / 2 - 330, 500,
            150, 50);

    private Rectangle playAgainButton = new Rectangle(WINDOW_WIDTH / 2 - 330,
            500, 150, 50);
    
    /**
     * Constructs a pandemicGame and initializes it to be able to accept key
     * input.
     */
    public HolyokeMap() {
        // Allow the game to receive key input
        setFocusable(true);

        addKeyListener(this);


    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    /**
     * Draws all of the objects in the game.
     * 
     * @param g the graphics context to draw on
     */
    public void paintComponent(Graphics g) {
        if (State == STATE.MENU) {
            menuRender(g);
        }

        // handles about screen
        else if (State == STATE.ABOUT) {
            //aboutRender(g);
        }

        // handles new level screen
        else if (State == STATE.LEVEL) {
            //levelRender(g);
        }

        // handles gameover screen
        else if (State == STATE.GAMEOVER) {
            //gameoverRender(g);
        }
    }
    
    @Override
    protected void nextFrame() {
        this.addMouseListener(mouseInput);
        
        repaint();
    }
    
    
    /**
     * Creates the game's intro screen.
     * 
     * @param g - the graphics context to draw on
     */
    public void menuRender(Graphics g) {
        Font fnt = new Font("arial", Font.BOLD, 60);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Welcome To PANDEMIC!", 100, 100);
        g.setFont(buttonFont);

        // may need to delete this line
        g.drawString("Produced by the one and only Space Wonders",
                this.getWidth() / 4, 20);
        g.drawString(
                "The year is 2020, and there is a deadly pandemic sweeping the globe.",
                40, 150);
        g.drawString(
                "Your job is to eliminate it with the power of vaccines! Viruses will appear in random places ",
                40, 180);
        g.drawString(
                "around the screen, going at random speeds, and you have to try to inject them with vaccine  ",
                40, 210);
        g.drawString(
                "particles. You get 5 vaccine needles in total, and you can keep going until you have eliminated ",
                40, 240);
        g.drawString(
                "all of the viruses or until they have eliminated you, at which point you will respawn in a random, ",
                40, 270);
        g.drawString(
                "location. Be careful, though! Each virus will split into  ",
                40, 300);

        g.drawString(
                "two after it has been injected, until you reach the smallest size. ",
                40, 330);
        g.drawString(
                "When you have successfully eliminated one round of viruses, the game will switch to the next",
                40, 360);
        g.drawString(
                "level. Each level will contain one more virus than the previous one.",
                40, 390);
        g.drawString(
                "You will also have to face stupid people who have forgotten to wear their masks.",
                40, 420);
        g.drawString(
                "They will kill you if you touch them and will also cough deadly germs out in ",
                40, 450);
        g.drawString(
                "all directions at once, so try to inject or avoid them before you get hit!",
                40, 480);

        // Creates and draws buttons
        g.drawString("PLAY", playButton.x + 43, playButton.y + 34);
        g.drawString("ABOUT", aboutButton.x + 40, aboutButton.y + 34);
        g.drawString("QUIT", quitButton.x + 41, quitButton.y + 34);
        Graphics2D g2D = (Graphics2D) g;
        g2D.draw(playButton);
        g2D.draw(aboutButton);
        g2D.draw(quitButton);

    }
    
    
    
    /**
     * Main method.
     * 
     * @param args - some arguments
     */
    public static void main(String[] args) {
        // JFrame is the class for a window. Create the window,
        // set the window's title and its size.
        JFrame f = new JFrame();
        f.setTitle("Pandemic");
        f.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        f.getContentPane().setBackground(Color.BLACK);
        
        //creates the map
        holyokeGame = new HolyokeMap();
        
        //creates a jpanel that the image can be added to
        JPanel panel=new JPanel(); 
        panel.setBounds(0, 0, 900, 600);    
        panel.setBackground(Color.gray); 
        
        //RESIZES IMAGE FOR FRAME
        ImageIcon img = new javax.swing.ImageIcon("HolyokeMap.jpg");
        Image image = img.getImage(); // transform it 
        Image newimg = image.getScaledInstance(900, 600,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newimg);  // transform it back
        
        //create a jlabel and adds the image to it
        JLabel jl=new JLabel();
        jl.setIcon(img);
        panel.add(jl);
        
        f.add(panel);  

        holyokeGame.addMouseListener(mouseInput);

        // Add the animation to the window
        Container contentPane = f.getContentPane();
        contentPane.add(holyokeGame, BorderLayout.CENTER);

        // Display the window.
        f.setVisible(true);

        // This says that when the user closes the window, the
        // entire program should exit.
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        holyokeGame.start();
    }
 
}
