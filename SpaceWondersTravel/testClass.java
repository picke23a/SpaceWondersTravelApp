package animation;
// Java Program to create a popup (add a panel) and 
// display it on a parent frame and also 
// add action listener to the popup 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.awt.geom.Ellipse2D; 


class pop1 extends JFrame implements ActionListener { 
    // popup 
    Popup po; 
  
    // frame 
    JFrame f; 
  
    // panel 
    JPanel p; 
  
    // popupfactory 
    PopupFactory pf; 
  
    // constructor 
    pop1() 
    { 
        // create a frame 
        f = new JFrame("pop"); 
  
        f.setSize(400, 400); 
  
        pf = new PopupFactory(); 
  
        // create a label - the html tags make it so the text wraps, for whatever reason
        JLabel l = new JLabel("<html>Blanchard. INSERT DESCRIPTION HERE</html"); 
        l.setPreferredSize(new Dimension(250, 100));
  
        // create a new button 
        JButton b19 = new JButton("OK");
        b19.setPreferredSize(new Dimension(20, 20));
  
        // add action listener 
        b19.addActionListener(this); 
  
        try { 
            // set windows look and feel 
            UIManager.setLookAndFeel(UIManager. 
                  getSystemLookAndFeelClassName()); 
        } 
        catch (Exception e) { 
        } 
  
        // create a panel 
        p = new JPanel(); 
  
        p.setBackground(Color.red); 
  
        // create a font 
        Font fo = new Font("BOLD", 1, 14); 
  
        l.setFont(fo); 
  
        // add contents to panel 
        p.add(l); 
        p.add(b19); 
  
        p.setLayout(new GridLayout(2, 1)); 
  
        // create a popup 
        po = pf.getPopup(f, p, 280, 100); 
        
    
        // create a button 
        JButton b = new JButton(); 
        b.setPreferredSize(new Dimension(20, 20));
        b.setBackground(Color.RED);
        b.setBorderPainted(false);
        b.setOpaque(true);
  
        // add action listener 
        b.addActionListener(this); 
  
        // create a panel 
        JPanel p1 = new JPanel(); 
  
        p1.add(b); 
        f.add(p1); 
        f.show(); 
    } 
  
    // if the button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String d = e.getActionCommand(); 
        // if ok buton is pressed hide the popup 
        if (d.equals("OK")) { 
            po.hide(); 
  
            // create a popup 
            po = pf.getPopup(f, p, 180, 100); 
        } 
        else
            po.show(); 
    } 
    // main class 
    public static void main(String args[]) 
    { 
        pop1 p = new pop1(); 
    } 
}
