// Author: Sumeet Jhand, Campos
// Description: Displays a picture, an eye symbol, and a title. Colours and fonts (for the title) can be changed.
// Date created: November 7th, 2014
/* -------- Method List --------
 * Picture() - sets to paint a red (default) eye symbol
 * Picture(ImageIcon img) - sets to paint a picture
 * Picture(String title) - sets to draw a String given the input argument of a bold, 20 pt, Arial font (default)
 * void setTitle(String title, Font font) - sets the title and font to the given input arguments
 * void resetColor(Color c) - sets the colour to paint with based on the Color argument
 * void resetColor(int r, int g, int b) - sets the colour to paint with based on the integer R, G, and B arguments
 * void paint(Graphics g) - paints graphics
 * void main - creates a frame to paint graphics on (used for testing purposes)
 */
import javax.swing.*;
import java.awt.*;

public class Picture extends JComponent {
    
    // declares private variables needed to draw graphics
    // this includes the colour to draw graphics with, the picture to draw, booleans to tell whether the
    // picture or title is currently showing, the string to show as the title, and the font to set that
    // title to be    
    private Color c;
    private ImageIcon pic;
    private boolean picOn, titleOn, rect;
    private String title;
    private Font font;
    private int x, y, w, h;
        
    public Picture() {
    	// draws a red eye symbol
    	// sets the picture and font to null (not using those) and booleans of titleOn and picOn to false,
    	// as titles and pictures are now being drawn
    	this.c = Color.RED;
    	this.pic = null;
    	this.picOn = false;
    	this.titleOn = false;
    	this.font = null;
    	this.rect = false;
    	// each constructor repaints so that it draws what is wanted correctly, according to the variables
    	repaint();
    }
    
    public Picture(int x, int y, int w, int h, Color c) {
    	this.c = c;
    	this.pic = null;
    	this.picOn = false;
    	this.titleOn = false;
    	this.font = null;
    	this.rect = true;
    	this.x = x;
    	this.y = y;
    	this.w = w;
    	this.h = h;
    	repaint();
    }
    
    public Picture(ImageIcon img) {
    	// shows a picture
    	// sets the picture to the img argument. Font and Color set to null (not using fonts or colours)
    	// boolean picOn is set to true (as a picture is being drawn) and titleOn is set to false (not drawing titles)
    	this.c = null;
    	this.pic = img;
    	this.picOn = true;
    	this.titleOn = false;
    	this.font = null;
    	this.rect = false;
    	repaint();
    }
    
    public Picture(String title) {
    	// draws a title
    	// sets the picture and Color to null (not using those)
    	// sets the title to the title argument, sets titleOn to true (as a title is being drawn),
    	// sets font to arial, bold, size pt 20 by default
    	// picOn is set to false because a picture is not being drawn
    	this.title = title;
    	this.c = null;
    	this.pic = null;
    	this.picOn = false;
    	this.titleOn = true;
    	this.rect = false;
    	this.font = new Font ("Arial", Font.BOLD, 20);
    	this.x = 0;
    	this.y = 0;
    	repaint();
    }
    
    public Picture(String title, int x, int y) {
    	// draws a title
    	// sets the picture and Color to null (not using those)
    	// sets the title to the title argument, sets titleOn to true (as a title is being drawn),
    	// sets font to arial, bold, size pt 20 by default
    	// picOn is set to false because a picture is not being drawn
    	// includes x and y coords of the title
    	this.title = title;
    	this.c = null;
    	this.pic = null;
    	this.picOn = false;
    	this.titleOn = true;
    	this.rect = false;
    	this.font = new Font ("Arial", Font.BOLD, 20);
    	this.x = x;
    	this.y = y;
    	repaint();
    }
    
    public void setTitle(String title, Font font) {
    	// sets the title to the title argument and font to the font argument
    	// and repaints
    	this.title = title;
    	this.font = font;
    	this.x = 0;
    	this.y = 0;
    	repaint();
    }
    
    public void setTitle(String title, Font font, int x, int y) {
    	// sets the title to the title argument and font to the font argument
    	// and repaints
    	// includes x and y coords of the title
    	this.title = title;
    	this.font = font;
    	this.x = x;
    	this.y = y;
    	repaint();
    }
    
    public void resetColor(Color c) {
    	// sets the colour to draw graphics with to the c argument and repaints
    	this.c = c;
    	repaint();
    }
    
    public void resetColor(int r, int g, int b) {
    	// sets the RGB colour to draw graphics with to the r, g, b arguments and repaints
    	this.c = new Color(r, g, b);
    	repaint();
    }
    
    public void paint(Graphics g) {
    	if (this.picOn) {
    		// draws a picture of pic at (0, 0) if picOn is true
    		this.pic.paintIcon(this, g, 0, 0);
    	} else {
    		if (this.rect) {
    			g.setColor(this.c);
    			g.drawRect(this.x, this.y, this.w, this.h);
    		} else {
    			if (this.titleOn) {
    				// draws a title at (x, y) if picOn is false and titleOn is true
    				g.setFont(this.font); 
    				g.drawString(this.title, this.x, this.y);
    			} else {
    				// setsColour to whatever Color c is and draws a rectangle, an oval, and a filled oval at certain points and sizes to
    				// resemble an eye-like symbol
    				g.setColor(this.c);
    				g.drawRect(100, 10, 200, 50); // format: (x, y, width, length)
    				g.drawOval(100, 10, 200, 50);
    				g.fillOval(175, 10, 50, 50);
    			}
    		}	
    	}
    }
    
    // testing main
    public static void main(String[] args) {
        JFrame f = new JFrame("Test"); // sets frame's name to 'Test'
        
        // creates a frame with a red (default) eye symbol
        Picture p = new Picture();
        f.setSize(400, 150);
        f.add(p);
        f.setVisible(true);
        
        JOptionPane.showMessageDialog(null, "wait"); // prompts 'wait' dialog to delay changes
        
        // redraws same eye symbol but in blue
        p.resetColor(Color.BLUE);
        
        JOptionPane.showMessageDialog(null, "wait"); // prompts 'wait' dialog to delay changes
        
        // redraws same eye symbol but in midnight blue with RGB values
        p.resetColor(50, 50, 120);
        
        JOptionPane.showMessageDialog(null, "wait"); // prompts 'wait' dialog to delay changes
        
        // removes eye symbol from frame and draws a picture from file 'test.gif'
        f.remove(p);
        Picture icon = new Picture(new ImageIcon("test.gif"));
        f.setSize(400, 400);
        f.add(icon);
        f.setVisible(true);
        
        JOptionPane.showMessageDialog(null, "wait"); // prompts 'wait' dialog to delay changes
        
        // removes picture and draws a title string with text 'Testing' (default: arial, bold, 20 pt)
        f.remove(icon);
        Picture title = new Picture("Testing", 150, 25);
        f.setSize(400, 150);
        f.add(title);
        f.setVisible(true);
        
        JOptionPane.showMessageDialog(null, "wait"); // prompts 'wait' dialog to delay changes
        
        // changes title's font to Verdana (still bold and 20 pt)
        title.setTitle("Testing 2", new Font ("Verdana", Font.BOLD, 20), 150, 50);
    }
}
