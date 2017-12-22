// Author: Sumeet Jhand
/* Description: Handles the display of the custom message box using a glasspane. */
// Note: A glass pane is essentially a "layer" than can be drawn on that overlays all other components.
// Date created: January 10th, 2014
/* -------- Method List -------
 * Dialog() - creates a new instance of dialog
 * void setComponentsVisible(boolean state) - sets the message, ok button, and title to visible or not visible depending on boolean state
 * void showMessage(String message, String title) - shows a message dialog with the given message and title
 * void actionPerformed(ActionEvent e) - handles all button ActionEvents on the glass pane
 * ...MouseEvent methods - empty so that nothing behind the glass pane is clicked
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class Dialog extends JPanel implements ActionListener, MouseListener {

 // JLabels for the title and message of the dialog box
 private JLabel title, message;
 // Picture dialog for the dialog box itself (it's a PNG image)
 private Picture dialog;
 // JButton for the "OK" button on the dialog box
 private JButton btnOk;

 public Dialog() {
 	 // sets the dialog to transparent
	 setOpaque(false);
	 
	 setLayout(null); // null layout for absolute positioning
	 
	 // creates two centered JLabels (for title and message)
	 // creates Picture dialog
	 // creates JButton ("OK" button)
	 title = new JLabel("", SwingConstants.CENTER);
	 message = new JLabel("", SwingConstants.CENTER);
	 dialog = new Picture(new ImageIcon("Misc/dialog.png"));
	 btnOk = new JButton("OK");
	 
	 // sets the font of the message to non-bold Arial 16 pt
	 // sets the font of the title to bold Arial 14 pt
	 // both set to white text
	 // sets brown-yellow theme to the button
	 message.setFont(new Font ("Arial", Font.PLAIN, 16));
	 message.setForeground(Color.WHITE);
	 title.setFont(new Font ("Arial", Font.BOLD, 14));
	 title.setForeground(Color.WHITE);
	 GameLibrary.setBtnTheme(btnOk, new Color(255, 215, 26), new Color(52, 28, 26));
    
     // adds all components to the glass pane
	 add(title);
	 add(message);
	 add(btnOk);
	 add(dialog);
	 
	 // adds an actionlistener for the OK button as well as a mouselistener for the glass pane
	 btnOk.addActionListener(this);
	 addMouseListener(this);
	 
	 // sets the bounds for all the components
	 title.setBounds(9, 205, 770, 20);
	 dialog.setBounds(0, 0, 800, 600);
	 btnOk.setBounds(342, 345, 100, 33);
	 
	 // sets all components to not visible (initially)
	 setComponentsVisible(false);
 }
 
 public void actionPerformed(ActionEvent e) {
 	if (e.getSource() == btnOk) {
 		// if "OK" button is pressed, make all components not visible and make
 		// the glass pane not visible as well
 		setComponentsVisible(false);
 		getRootPane().getGlassPane().setVisible(false);
 	}
 }
 
 public void setComponentsVisible(boolean state) {
 	// sets the visibility of the ok button, title, and message to state
 	this.title.setVisible(state);
 	this.message.setVisible(state);
 	this.btnOk.setVisible(state);
 }
 
 public void showMessage(String message, String title) {
 	// sets all components to visible
 	setComponentsVisible(true);
 	// sets the text for message and title
 	this.message.setText(message);
 	this.title.setText(title.toUpperCase());
 	// sets the bounds for message
 	this.message.setBounds(10, 280, 770, 20);
 	// sets the glass pane to visible
 	getRootPane().getGlassPane().setVisible(true);
 }

 // empty MouseEvents so that background layers do not respond to mouse clicks
 public void mouseClicked(MouseEvent e) {
 }

 public void mouseEntered(MouseEvent e) {
 }

 public void mouseExited(MouseEvent e) {
 }

 public void mousePressed(MouseEvent e) {
 }

 public void mouseReleased(MouseEvent e) {
 }
 
 public static void main(String[] args) {
 	// no testing main - class is to misc. for a test
 	// is also tested various times throughout the program
 }
}
