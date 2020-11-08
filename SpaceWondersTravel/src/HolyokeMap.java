import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import animation.AbstractAnimation;


public class HolyokeMap extends AbstractAnimation implements KeyListener {
    // an instance of the game
    static HolyokeMap holyokeGame;
    
    //gets mouse input
    private static MouseInput mouseInput = new MouseInput();
    

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

        // Create the animation.
        holyokeGame = new HolyokeMap();

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
