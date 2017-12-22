// Author: Sarfaraz Jahangir
/* Description: The main-menu of the Casino which allows the user to play either Poker or Blackjack.
				Also allows adding money to the user's balance through the manage funds screen. */
// Date created: December 29th, 2014
/* -------- Method List -------
 * MenuUI(int balance, String username) - creates a new instance of MenuUI with the specified balance and username
 * void redraw() - redraws the balance displayed on the screen
 * void actionPerformed(ActionEvent e) - handles all button ActionEvents
 */
import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;

public class MenuUI extends JFrame implements ActionListener {
	
	// Picture objects to handle most visual elements
	private Picture logo, bg, bank;
	// JLabels to provide information to the user about various elements (name, balance)
	private JLabel lblBal, lblName;
	// the user's current balance
	private int balance;
	// JButtons to allow the user to interact with the menu (play either game, manage funds, etc.)
	private JButton btnPoker, btnBlackjack, btnManFunds, btnExit, btnWD10, btnWD50, btnWD100, btnWD1000, btnWD5000, btnBack;
	// Dialog object used to show a custom message dialog whenever a certain method in the object is called
	private Dialog dialog = new Dialog();
	// String to store the user's name
	private String name;
        
    public MenuUI(int balance, String username) {
    	super("Triple J Casino - Main Menu"); // call to super JFrame
    	
    	// set user's balance to the balance used to initiate this instance
    	this.balance = balance;
    	
    	if (username.equalsIgnoreCase("")) {
    		try {
    			// if the username is empty, ask for a username
	    		name = JOptionPane.showInputDialog("Please enter your name to begin:");
	    	
		    	if (name.trim().equalsIgnoreCase(""))
		    		// if the user trys to enter an empty username, set their name to Player
		    		this.name = "Player";
	    	} catch (NullPointerException e) {
	    		// if the user pressed cancel or exited the box, set their name to Player
	    		name = "Player";
	    	}
    	} else {
    		// otherwise set their name to whatever was used to initiate this instance
    		name = username;
    	}
    	
    	setLayout(null); // null layout for absolute positioning
    	
    	setGlassPane(dialog); // sets the glass pane to dialog
    	
    	// creates all buttons (used both in the main menu and bank screen)
    	btnPoker = new JButton("Play Poker");
    	btnBlackjack = new JButton("Play Blackjack");
    	btnManFunds = new JButton("Manage Funds");
    	btnExit = new JButton("Exit");
    	btnWD10 = new JButton("Add $10");
    	btnWD50 = new JButton("Add $50");
    	btnWD100 = new JButton("Add $100");
    	btnWD1000 = new JButton("Add $1000");
    	btnWD5000 = new JButton("Add $5000");
    	btnBack = new JButton("Back");
    	
    	// sets the theme for all the buttons
    	// sets a brown-yellow theme
    	GameLibrary.setBtnTheme(btnPoker, new Color(255, 215, 26), new Color(52, 28, 26));
    	GameLibrary.setBtnTheme(btnBlackjack, new Color(255, 215, 26), new Color(52, 28, 26));
    	GameLibrary.setBtnTheme(btnManFunds, new Color(255, 215, 26), new Color(52, 28, 26));
    	GameLibrary.setBtnTheme(btnExit, new Color(255, 215, 26), new Color(52, 28, 26));
    	// sets a black-white theme
    	GameLibrary.setBtnTheme(btnWD10, Color.WHITE, Color.BLACK);
    	GameLibrary.setBtnTheme(btnWD50, Color.WHITE, Color.BLACK);
    	GameLibrary.setBtnTheme(btnWD100, Color.WHITE, Color.BLACK);
    	GameLibrary.setBtnTheme(btnWD1000, Color.WHITE, Color.BLACK);
    	GameLibrary.setBtnTheme(btnWD5000, Color.WHITE, Color.BLACK);
    	GameLibrary.setBtnTheme(btnBack, Color.WHITE, Color.BLACK);
    	
    	// creates all the Picture components through getting the required pictures
    	logo = new Picture(new ImageIcon(getClass().getResource("Misc/casino-logo.gif")));
    	bg = new Picture(new ImageIcon(getClass().getResource("Misc/menu-bg.png")));
    	bank = new Picture(new ImageIcon(getClass().getResource("Misc/bank-bg.png")));
    	
    	// creates all the labels (centered)
    	lblBal = new JLabel("Balance: $" + this.balance, SwingConstants.CENTER);
    	lblName = new JLabel("Welcome, " + name + "!", SwingConstants.CENTER);
    	
    	// sets the theme for the labels (White arial bold 17 pt)
    	lblBal.setFont(new Font ("Arial", Font.BOLD, 17));
    	lblBal.setForeground(Color.WHITE);
    	lblName.setFont(new Font ("Arial", Font.BOLD, 17));
    	lblName.setForeground(Color.WHITE);
    	
    	// adds all the components in proper order
    	add(logo);
    	add(lblBal);
    	add(lblName);
    	add(btnPoker);
    	add(btnBlackjack);
    	add(btnManFunds);
    	add(btnExit);
    	add(btnWD10);
    	add(btnWD50);
    	add(btnWD100);
    	add(btnWD1000);
    	add(btnWD5000);
    	add(btnBack);
    	add(bank);
    	add(bg);
    	
    	// sets all the components' bounds
    	logo.setBounds(201, 76, 392, 179); // x, y, width, height
    	bg.setBounds(0, 0, 794, 597);
    	bank.setBounds(0, 0, 794, 597);
    	lblBal.setBounds(235, 525, 300, 20);
    	lblName.setBounds(235, 330, 300, 20);
    	btnPoker.setBounds(107, 410, 135, 55);
    	btnBlackjack.setBounds(257, 410, 135, 55);
    	btnManFunds.setBounds(407, 410, 135, 55);
    	btnExit.setBounds(557, 410, 135, 55);
    	btnWD10.setBounds(297, 200, 180, 30);
    	btnWD50.setBounds(297, 245, 180, 30);
    	btnWD100.setBounds(297, 290, 180, 30);
    	btnWD1000.setBounds(297, 335, 180, 30);
    	btnWD5000.setBounds(297, 380, 180, 30);
    	btnBack.setBounds(334, 430, 100, 30);
    	
    	// adds actionlisteners to all the buttons
    	btnPoker.addActionListener(this);
    	btnBlackjack.addActionListener(this);
    	btnManFunds.addActionListener(this);
    	btnExit.addActionListener(this);
    	btnWD10.addActionListener(this);
    	btnWD50.addActionListener(this);
    	btnWD100.addActionListener(this);
    	btnWD1000.addActionListener(this);
    	btnWD5000.addActionListener(this);
    	btnBack.addActionListener(this);
    	
    	// sets the bank components to not visible (initially)
    	bank.setVisible(false);
   		btnWD10.setVisible(false);
   		btnWD50.setVisible(false);
   		btnWD100.setVisible(false);
   		btnWD1000.setVisible(false);
   		btnWD5000.setVisible(false);
   		btnBack.setVisible(false);
    	
    	// sets the size of the frame
    	// disallows resizing
    	// sets it to visible
    	// sets it to exit on closing the JFrame
    	setSize(800, 625);
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void redraw() {
    	// sets the balance label's text to the new balance
    	lblBal.setText("Balance: $" + this.balance);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == btnExit) {
    		// exits the program
    		System.exit(0);
    	}
    	if (e.getSource() == btnPoker) {
    		if (balance < 500)
    			// shows an error message if user has below $500
    			dialog.showMessage("You must have a minimum balance of $500 to play Texas Hold Em'.", "insufficient balance");
    		else {
    			// otherwise, closes the main menu and opens up PokerUI with the correct name and balance
    			dispose();
    			new PokerUI(balance, name);
    		}
    	}
    	if (e.getSource() == btnBlackjack) {
    		if (balance < 5)
    			// shows an error message if user has below $5
    			dialog.showMessage("You must have a minimum balance of $5 to play Blackjack.", "insufficient balance");
    		else {
    			// otherwise, closes the main menu and opens up BJackUI with the correct name and balance
    			dispose();
    			new BJackUI(balance, name);
    		}
    	}
    	if (e.getSource() == btnManFunds) {
    		// sets all bank components to visible
    		bank.setVisible(true);
   			btnWD10.setVisible(true);
   			btnWD50.setVisible(true);
   			btnWD100.setVisible(true);
   			btnWD1000.setVisible(true);
   			btnWD5000.setVisible(true);
   			btnBack.setVisible(true);
   			// repositions the balance label to a better area
    		lblBal.setLocation(237, 160);
    		
    		// makes all main menu components invisible
    		lblName.setVisible(false);
    		bg.setVisible(false);
    		logo.setVisible(false);
    		btnPoker.setVisible(false);
    		btnBlackjack.setVisible(false);
    		btnManFunds.setVisible(false);
    		btnExit.setVisible(false);
    	}
    	if (e.getSource() == btnBack) {
    		// makes all main menu components visible
    		lblName.setVisible(true);
    		bg.setVisible(true);
    		logo.setVisible(true);
    		btnPoker.setVisible(true);
    		btnBlackjack.setVisible(true);
    		btnManFunds.setVisible(true);
    		btnExit.setVisible(true);
    		// repositions balance label to where it was originally
    		lblBal.setLocation(235, 525);
    		
    		// sets all bank components invisible
    		bank.setVisible(false);
   			btnWD10.setVisible(false);
   			btnWD50.setVisible(false);
   			btnWD100.setVisible(false);
   			btnWD1000.setVisible(false);
   			btnWD5000.setVisible(false);
   			btnBack.setVisible(false);
    	}
    	if (e.getSource() == btnWD10)
    		// adds 10 to the balance if user clicks add $10
    		balance += 10;
    	if (e.getSource() == btnWD50)
    		// adds 50 to the balance if user clicks add $50
    		balance += 50;
    	if (e.getSource() == btnWD100)
    		// adds 100 to the balance if user clicks add $100
    		balance += 100;
    	if (e.getSource() == btnWD1000)
    		// adds 1000 to the balance if user clicks add $1000
    		balance += 1000;
    	if (e.getSource() == btnWD5000)
    		// adds 5000 to the balance if user clicks add $5000
    		balance += 5000;
    	redraw(); // redraws the balance label to show the new balance
    }
    
    public static void main(String[] args) {
    	// creates a new instance of MenuUI with $500 and a name
    	// needing to be inputted by the user
        new MenuUI(500, "");
    }
}
