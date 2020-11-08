import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;

import animation.AbstractAnimation;

/**
 * Creates an instance of our game.
 @author AmyWang, Gloria, Anna, AJ
 *
 */
public class HolyokeMap extends AbstractAnimation implements KeyListener {
    // an instance of the game
    static HolyokeMap holyokeGame;
    
    //gets mouse input
    private static MouseInput mouseInput = new MouseInput();
    
    private static JFrame f;
    

    //window dimensions
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 900;
  
    
    /**
     * Keeps track of the state of the game
     *
     */
    public static enum STATE {
        MENU, GAME, 
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
    
 // student variable, since there is only one student at a time
    private MHCStudent student = new MHCStudent(this);
    
    private Pushpins pushpinTest = new Pushpins(this, 150, 150);
    
    
 // stores all trail particles
    private ArrayList<TrailParticles> particleList = new ArrayList<>();
    
    
    
    /**
     * Constructs a Holyoke Map and initializes it to be able to accept key
     * input.
     */
    public HolyokeMap() {
        // Allow the game to receive key input
        setFocusable(true);

        addKeyListener(this);


    }

    @Override
    public void keyTyped(KeyEvent event) {
        
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        // if the game is currently being played, the user can interact with
        // these keys
        if (State == STATE.GAME) {
            System.out.println("True");
            switch (key) {
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_UP:
                student.up();
                particleList.add(student.shoot());
                break;
            case KeyEvent.VK_RIGHT:
            	student.right();
                student.rotateClockwise();
                particleList.add(student.shoot());
                break;
            case KeyEvent.VK_LEFT:
            	student.left();
                student.rotateAntiClockwise();
                particleList.add(student.shoot());
                break;
            case KeyEvent.VK_DOWN:
            	student.down();
            	particleList.add(student.shoot());
            default:
            }
        }
        
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
        
        else if (State == STATE.GAME) {
        	student.paint((Graphics2D) g);
        	pushpinTest.paint((Graphics2D) g);
//            //creates a jpanel that the image can be added to
//            JPanel panel=new JPanel(); 
//            panel.setBounds(0, 0, 900, 600);    
//            //panel.setBackground(Color.gray); 
//           ImageIcon img = new ImageIcon("HolyokeMap.jpg");
//           f.setContentPane(new JLabel(img));
//           f.setLayout(new FlowLayout());
//           //JLabel L1 = new JLabel();
//          // f.add(L1);
//           f.setSize(1600,1000);
//           f.setSize(holyokeGame.getWidth(),holyokeGame.getHeight());
//           f.setResizable(false);
        	
        	
        	
        	//f.getContentPane().setBackground(Color.GREEN);
        	
        	/*
        	JPanel panel1 = new JPanel();
        	panel1.setBounds(100, 100, 20, 100);
        	panel1.setBackground(Color.gray);
            ImageIcon img = new ImageIcon("OIP.jfif");
        	f.setContentPane(new JLabel(img));
        	f.setLayout(new FlowLayout());
        	f.setSize(holyokeGame.getWidth(),holyokeGame.getHeight());
        	
        	f.setResizable(false);
           */
        	
            

            
            // paints student
            student.paint((Graphics2D) g);
            
            // paints every particle in the particleList
            for (int i = 0; i < particleList.size(); i++) {
                particleList.get(i).paint((Graphics2D) g);
            }

        }
        
        
     
    }
    
  
    
    @Override
    protected void nextFrame() {
        this.addMouseListener(mouseInput);
        
        if (State == STATE.GAME) {
            student.nextFrame();
            pushpinTest.nextFrame();
        }
     
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
        //changed (just now)
        g.drawString("Plan Your Road Trip", 100, 100);
        g.setFont(buttonFont);

        // may need to delete this line
        //changed (just now)
        g.drawString("Produced by the one and only Space Wonders",
                this.getWidth() / 4, 20);
        g.drawString(
                "Explore new interesting places with Virtual Traveling!",
                40, 150);
        g.drawString(
                "Use our program to plot out your future road trip and use",
                40, 180);
        g.drawString(
                "the links to learn more about intersting places along the way.",
                40, 210);
        g.drawString(
                "You can also use our 'Randomized trip' option to let the program",
                40, 240);
        g.drawString(
                "plot an interesting path between two places for you. Have fun!",
                40, 270);

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
     * a getter for the width of the window
     * 
     * @return window_width 0 the width of the window
     */
    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    /**
     * a getter for the height of the window
     * 
     * @return window_height - the height of the window
     */
    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }
    
    
    /**
     * Main method.
     * 
     * @param args - some arguments
     */
    public static void main(String[] args) {
     // JFrame is the class for a window. Create the window,
        // set the window's title and its size.
        f = new JFrame();
        f.setTitle("Pandemic");
        f.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        Color myGreen = new Color(24, 134, 45);

        f.getContentPane().setBackground(myGreen);
        
        //creates the map
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
