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
        //g.drawImage(img, 0, 0, null);
    }
    
    @Override
    protected void nextFrame() {
        // TODO Auto-generated method stub
        
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
