// Author: Sumeet Jhand
/* Description: Handles animations of any given picture from one point to another.
 				Supports delay the animation and setting it to visible/not visible after animation is done. */
// Date created: December 20th, 2014
/* -------- Method List -------
 * Animation(Picture pic, Point2D.Double target, Dimension dim, boolean vis) - animates pic from its current position to target, making it visible based on boolean vis after the animation
 * Animation(Picture pic, Point2D.Double target, Dimension dim, int delay, boolean vis) - delays for given ms, animates pic from its current position to target, making it visible based on boolean vis after the animation
 * void run() - executes animation, called by constructors (not user)
 */
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Animation extends Thread {
	
	Picture pic; // declares Picture pic for the pic to be animated
	Point2D.Double target; // declares Point2D.Double target for the (x, y) coords of the target
	Dimension dim; // declares Dimension dim for dimensions of the pic
	boolean vis; // declares boolean vis to set the pic visible after the animation or not
	int initialX, initialY; // declares int initialX and initialY for the starting (x, y) coords
	int tick; // declares tick for each frame drawn of the animation
	int delay; // declares delay for the time to delay in ms before the animation starts
	
	public Animation(Picture pic, Point2D.Double target, Dimension dim, boolean vis) {
		super(); // call to super Thread
		
		if (vis)
			pic.setVisible(vis); // sets pic to visible if user would like to make pic visible
		
		this.pic = pic; // sets the pic to the pic the user inputted
		this.initialX = pic.getX(); // sets initialX to the x-coord of the pic
    	this.initialY = pic.getY(); // sets initialY to the y-coord of the pic
		this.target = target; // sets target to the user's inputted target
		this.dim = dim; // sets the pic's dimension to the inputted dimension
		this.vis = vis; // sets the visible boolean to the user's inputted vis boolean
		this.delay = 0; // sets delay to 0 (this constructor has no delay argument)
		start(); // executes the animation (i.e. the run() method)
	}
	
	public Animation(Picture pic, Point2D.Double target, Dimension dim, int delay, boolean vis) {
		super(); // call to super Thread
		
		if (vis)
			pic.setVisible(vis); // sets pic to visible if user would like to make pic visible
			
		this.pic = pic; // sets the pic to the pic the user inputted
		this.initialX = pic.getX(); // sets initialX to the x-coord of the pic
    	this.initialY = pic.getY(); // sets initialY to the y-coord of the pic
		this.target = target; // sets target to the user's inputted target
		this.dim = dim; // sets the pic's dimension to the inputted dimension
		this.vis = vis; // sets the visible boolean to the user's inputted vis boolean
		this.delay = delay; // sets delay to the user's inputted delay
		start(); // executes the animation (i.e. the run() method)
	}
	
	public void run() {
		if (delay != 0) {
			try {
				// if a delay has been set (i.e. not 0), sleep for 'delay' ms
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// catch InterruptedException and print error
				e.printStackTrace();
			}
		}
		while (tick < 10) { // while tick is less than 10
		  	tick++; // add 1 to tick
			double alpha = (double) tick / 10; // step to move animation by
			double dx = target.getX() - initialX; // X displacement from initial to target
			double dy = target.getY() - initialY; // Y displacement from initial to target
			// sets bounds of pic to initial + step * displacement
			pic.setBounds((int)(initialX + alpha * dx), (int)(initialY + alpha * dy), (int)dim.getWidth(), (int)dim.getHeight());
			try {
				// sleep for 35 ms between each step
				sleep(35);
			} catch(InterruptedException e) {
				// print error if InterruptedException encountered
				e.printStackTrace();
			}
		}
		if (!vis)
			// sets pic to not visible if user wants to set it to not visible after animation (i.e. inputted vis = false)
			pic.setVisible(false);
	}
	
	public static void main(String[] args) {
		// too misc. to be tested
		// tested MANY times in the main program anyway
	}
}

