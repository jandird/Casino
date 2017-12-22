//The "BJackUI" Class
//Authors: Dalip 
//Description: Creates a user interface that allows for a player to play black jack against an AI
/* Methods:
 
 * public BJackUI (int balance) { //basic constructor to make frame
 
 * public void actionPerformed (ActionEvent e){ //Runs everytime a button is pressed
 
 * private void draw (){ //method that runs when the round begins
 
 * private void hit (boolean playerHit, int delay){ //runs this if player or dealer wants one more card
 
 * private void stay (){ //calls this method when players turn is over
 
 * private void displayDCard (Picture card, int delay){ //animated dealers cards
 
 * private void displayPCard (Picture card, int delay){ //animates players cards
 
 * private int chooseDeck () { //method that chooses a deck using a rng
 
 * private void bet (int amount){ //method that is called everytime the user increases their bet
 
 * private void checkWinner (boolean blackJackP, boolean blackJackD){ //method that checks who won, and resets the game for the next round 
*/

//**NOTE**: Everytime the "new Thread()" code is seen, it is just being used to slow animations down

//importing all classes needed for program
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class BJackUI extends JComponent implements ActionListener {
    
    //creating card pictures and a panel that will hold the cards
    private JPanel cardPanel; 
    private Picture cards [][] = new Picture [8][53]; 
	
	//creating chip buttons and panel that will hold the buttons
	private JPanel chipsPanel;
	private JButton chipButtons [] = new JButton [5];
	
	//creating major button needed for gameplay
	private JButton completeBet;
	private JButton hitBtn;
	private JButton stayBtn;
	private JButton doubleDown;
	private JButton leaveTable;
	
	//creating array that will hold the cards that are currently being displayed
	private Picture currentCards [] = new Picture [0];
	
	//creating frame that will hold all components
	private JFrame frame;
	
	//creating picture that will hold the background
	private Picture background;
	
	//creating labels to display to the user what they and the dealer currently have
	private JLabel displayBalance;
	private JLabel displayBet;
	private JLabel displayTotalP;
	private JLabel displayTotalD;
	
	//creating player and dealer
	private BJackPlayer player;
	private BJackPlayer dealer;
	
	//creating 8 decks
	private Deck decks [] = new Deck [8];
	
	//creating number format for balance
	private NumberFormat curr = NumberFormat.getCurrencyInstance();
	
	//creating integers that will hold how many cards the player and dealer have
	private int amountDCards = 0;
	private int amountPCards = 0;
	
	//creating integers to hold what the current and max bet is
	private int currentBet = 0;
	private final int maxBet = 5000;
	
	//creating integer holds how far cards are from one another
	private final int moveCard = 20;
	
	//creating integers to hold hidden card
	private int hiddenDeck;
	private int hiddenCard;
	
	//creating colors for buttons
	private final Color btnBgColor;
	private final Color btnFgColor;
	
	//creating dialog box
	private Dialog dialog = new Dialog();
	
	// creates variable for storing the user's name
	private String name;

    public BJackUI (int balance, String name) { //basic constructor to make frame
    	
    	// stores the user's name
    	this.name = name;
    	
    	//intialzing player and dealer
    	player = new BJackPlayer (balance);
    	dealer = new BJackPlayer (0);
    	
    	for (int i = 0; i < 8; i++){
    		
    		//setting images in all 8 decks and making them invisible
    		GameLibrary.setDeckImages (cards [i]);
    		GameLibrary.setDeckInvisible (cards [i]);
    		
    		//intialzing decks
    		decks [i] = new Deck ();
    	}
    	
    	//creating frame
    	frame = new JFrame ("Black Jack");
    	frame.setLayout (null);
    	
    	//setting glass pane for dialog box
    	frame.setGlassPane(dialog);
    	
    	//setting images to background and setting background size
    	background = new Picture (new ImageIcon (getClass().getResource ("Boards/BJackBoard.png")));
    	background.setBounds (0, 0, 800, 600);
    	
    	//setting label that will display the users balance
    	displayBalance = new JLabel ((curr.format(balance)).replaceAll("\\.00", ""), SwingConstants.CENTER);
    	displayBalance.setFont(new Font ("Arial", Font.BOLD, 25));
    	displayBalance.setForeground(new Color(255, 215, 26));
    	displayBalance.setBounds (32, 38, 150, 100);
    	
    	//setting label that will display the users current bet
    	displayBet = new JLabel (Integer.toString (currentBet), SwingConstants.CENTER);
    	displayBet.setFont(new Font ("Arial", Font.BOLD, 25));
    	displayBet.setForeground(new Color(255, 215, 26));
    	displayBet.setBounds (615, 480, 150, 100);
    	
    	//setting label that will display the users card total
    	displayTotalP = new JLabel ("", SwingConstants.CENTER);
    	displayTotalP.setFont(new Font ("Arial", Font.BOLD, 12));
    	displayTotalP.setForeground(Color.BLACK);
    	displayTotalP.setBounds (325, 490, 154, 26);
    	
    	//setting label that will display the dealers card total
    	displayTotalD = new JLabel ("", SwingConstants.CENTER);
    	displayTotalD.setFont(new Font ("Arial", Font.BOLD, 12));
    	displayTotalD.setForeground(Color.BLACK);
    	displayTotalD.setBounds (325, 28, 154, 26);
    	displayTotalD.setVisible (false);
    	
    	//*******************CARDPANEL JPANEL**********************
    	
    	//setting panel that will hold cards
    	cardPanel = new JPanel ();
   		cardPanel.setBounds (0, 0, 800, 600);
   		cardPanel.setOpaque (false);
   		cardPanel.setLayout (null);
    		
    	//*******************CARDPANEL JPANEL**********************
    	
    	//*******************CHIPSPANEL JPANEL**********************
    	
    	//setting panel that will hold chip buttons
    	chipsPanel = new JPanel ();
   		chipsPanel.setBounds (0, 0, 800, 600);
   		chipsPanel.setOpaque (false);
   		chipsPanel.setLayout (null);
   		
   		int x = 260;
    	
    	for (int i = 0; i < chipButtons.length; i++){
    		
    		//setting chip buttons and adding them to chips panel
    		chipButtons [i] = new JButton (new ImageIcon (getClass().getResource("Chips/Chip" + (i+1) + ".png")));
	  		chipButtons [i].setBounds (x, 520, 50, 50);
	  		chipButtons [i].setOpaque (false);
	  		chipButtons [i].setBackground (new Color (0, 0, 0)); 
	  		chipButtons [i].setBorderPainted(false);
	  		chipButtons [i].addActionListener (this);
   			chipsPanel.add (chipButtons [i]);
	  			
	  		x += 60;
    	}

		//*******************CHIPSPANEL JPANEL**********************
		
		//*******************MAJOR BUTTONS************************
		
		//setting colors that will be used for buttons
		btnBgColor = new Color (255, 215, 26);
		btnFgColor = new Color (52, 28, 26);
		
		//setting button that will allow that user to start the round
		completeBet = new JButton ("Start Round");
		completeBet.setBounds (10, 365, 120, 30);
		
		//setting button that will allow user to hit
		hitBtn = new JButton ("Hit");
		hitBtn.setBounds (10, 405, 120, 30);
		hitBtn.setVisible (false);
		
		//setting button that allow user to stay
		stayBtn = new JButton ("Stay");
		stayBtn.setBounds (10, 445, 120, 30);
		stayBtn.setVisible (false);
		
		//setting button that will allow user to double down
		doubleDown = new JButton ("Double Down");
		doubleDown.setBounds (10, 485, 120, 30);
		doubleDown.setVisible (false);
		
		//setting button that will allow user to leave table
		leaveTable = new JButton ("Leave Table");
		leaveTable.setBounds (10, 405, 120, 30);
		
		//adding action listener to all buttons 
		completeBet.addActionListener (this);
		hitBtn.addActionListener (this);
		stayBtn.addActionListener (this);
		doubleDown.addActionListener (this);
		leaveTable.addActionListener (this);
		
		//setting colors of all buttons 
		GameLibrary.setBtnTheme (completeBet, btnBgColor, btnFgColor);
		GameLibrary.setBtnTheme (hitBtn, btnBgColor, btnFgColor);
		GameLibrary.setBtnTheme (stayBtn, btnBgColor, btnFgColor);
		GameLibrary.setBtnTheme (doubleDown, btnBgColor, btnFgColor);
		GameLibrary.setBtnTheme (leaveTable, btnBgColor, btnFgColor);
		
		//*******************MAJOR BUTTONS************************
    	
    	//adding all components to frames
    	frame.add (leaveTable);
    	frame.add (doubleDown);
    	frame.add (stayBtn);
    	frame.add (hitBtn);
    	frame.add (completeBet);
    	frame.add (chipsPanel);
    	frame.add (cardPanel);
    	frame.add (displayTotalP);
    	frame.add (displayTotalD);
    	frame.add (displayBet);
    	frame.add (displayBalance);
    	frame.add (background);
    	
    	//setting frame and making it visible
    	frame.setSize (800, 600);
    	frame.setResizable (false);
    	frame.setVisible (true);
    	
    	//displaying welcome message
    	dialog.showMessage("Have fun", "Welcome to Black Jack");
    }
    
    public void actionPerformed (ActionEvent e){ //Runs everytime a button is pressed
    	
    	if (e.getSource () == chipButtons [0]){ //adds 1 to bet
    		
    		bet (1);
    	}
    	if (e.getSource () == chipButtons [1]){ //adds 5 to bet
    		
    		bet (5);
    	}
    	if (e.getSource () == chipButtons [2]){ //adds 25 to bet
    		
    		bet (25);
    	}
    	if (e.getSource () == chipButtons [3]){ //adds 100 to bet
    		
    		bet (100);
    	}
    	if (e.getSource () == chipButtons [4]){ //adds 500 to bet
    		
    		bet (500);
    	}
    	if (e.getSource () == completeBet){ //starts the round
    		
    		if (currentBet >= 5){ //runs if bet is above 5
    		
    			//calls draw method
	    		draw ();
	    		
	    		//setting menu buttons invisible
	    		chipsPanel.setVisible (false);
	    		completeBet.setVisible (false);
	    		leaveTable.setVisible (false);
	    		
	    		//sets gameplay buttons visible 
	    		hitBtn.setVisible (true);
	    		stayBtn.setVisible (true);
	    		doubleDown.setVisible (true);
    		}
    		else { //tells user they must make a bigger bet
    			
    			JOptionPane.showMessageDialog (null, "You must make a minimum bet of 5");	
    		}
    	}
    	if (e.getSource () == hitBtn){ //calls hit method
    		
    		hit (true, 0);
    	}
    	if (e.getSource () == stayBtn){ //calls stay method
    		
    		displayTotalD.setVisible (true);
    		stay ();
    	}
    	if (e.getSource () == doubleDown){ //doubles the users bet, gives them 1 card, and calls stay method
    		
    		if (player.getChips () - currentBet < 0){
    			
    			dialog.showMessage("You dont have enough chips to make this bet", "Invalid move");
    		}
    		else{
    		
	    		displayTotalD.setVisible (true);
	    		
	    		bet (currentBet);
	    		hit (true, 0);
	    		stay ();
    		}	
    	}
    	if (e.getSource () == leaveTable){ //disposes frame and goes back to menu ui
    		
    		frame.dispose ();
    		new MenuUI(player.getChips(), name);
    	}
    }

    private void draw (){ //method that runs when the round begins
    	
    	//delay for animations
    	int delay = 0;
    	
    	//ints to store current card drawn
    	int cardDrawn;
    	int deckChosen;
    	
    	//booleans to check whether the player has a blackjack
    	boolean bJackJackP = false;
    	boolean bJackAceP = false;
    	
    	//booleans to check whether the dealer has a blackjack
    	boolean bJackJackD = false;
    	boolean bJackAceD = false;
    	
    	for (int i = 0; i < 4; i++){
    	
    		//adds on to animation delay
    		delay += 300;
    		
    		//getting card
    		deckChosen = chooseDeck ();
    		cardDrawn = decks [deckChosen].getCard ();
   			
   			if (i % 2 == 0){
   				
   				//giving card to play and animating it
   				player.giveCard ((cardDrawn / 4) + 1);
   				displayPCard (cards [deckChosen][cardDrawn], delay);
   			
   				//checking for blackjack
   				if (cardDrawn <= 3)
   					bJackAceP = true;
   				
   				if((cardDrawn >= 40) && (cardDrawn <= 43))
   					bJackJackP = true;
   			}	
   			else{
   			 
   			 	//giving card to dealer
   			 	dealer.giveCard ((cardDrawn / 4) + 1);
   			 
   			 	if (i == 3){
   			 	
   			 		//animating card
   			 		displayDCard (cards [0][52], delay);
   			 		
   			 		//storing hidden card value
   			 		hiddenDeck = deckChosen;
   			 		hiddenCard = cardDrawn;
   			 	}	
   			 	else{
   			 	
   			 		//animating card
   					displayDCard (cards [deckChosen][cardDrawn], delay);
   				}	
   				
   				//checking for dealer blackjack
   				if (cardDrawn <= 3)	
   					bJackAceD = true;
   				
   				if ((cardDrawn >= 40) && (cardDrawn <= 43))
   					bJackJackD = true;
   			}
    	}
    	
    	if ((bJackJackP == true && bJackAceP == true) && (bJackJackD == true && bJackAceD == true)){ //going straight to check winner stating that both player and dealer have blackjack
    		new Thread() {
		       	public void run() {
		           try {
			            
			            Thread.sleep(1000);
			            checkWinner (true, true);
		           } catch(InterruptedException e) {
		               
		               e.printStackTrace();
		           }
		      	}  
   			}.start();
    	}
    	else if (bJackJackP == true && bJackAceP == true){ //going straight to checkwinner stating that player has blackjack
    		new Thread() {
		       	public void run() {
		           try {
			            
			            Thread.sleep(1000);
			            checkWinner (true, false);
		           } catch(InterruptedException e) {
		               
		               e.printStackTrace();
		           }
		      	}  
   			}.start();
    	}	
    	else if (bJackJackD == true && bJackAceD == true){ //going straight to checkwinner statin that dealer has blackjack
    		new Thread() {
		       	public void run() {
		           try {
			            
			            Thread.sleep(1000);
			            checkWinner (false, true);
		           } catch(InterruptedException e) {
		               
		               e.printStackTrace();
		           }
		      	}  
   			}.start();
    	}					
    }
    
    private void hit (boolean playerHit, int delay){ //runs this if player or dealer wants one more card
    	
    	//getting card
    	int deckChosen = chooseDeck ();
		int cardDrawn = decks [deckChosen].getCard ();
		
		if (playerHit == true){ //checks whether dealer wants card or the player
			
			//giving card to player and animating
			player.giveCard ((cardDrawn / 4) + 1);
			displayPCard (cards [deckChosen][cardDrawn], delay);	
		}	
		else{
		 	
		 	//giving card to dealer and animating
		 	dealer.giveCard ((cardDrawn / 4) + 1);	
			displayDCard (cards [deckChosen][cardDrawn], delay);	
		}
		
		if (player.getTotal () > 21){ //goes straight to check winner if player goes bust
		
			new Thread() {
		       	public void run() {
		           try {
			            
			            Thread.sleep(1000);
			            checkWinner (false, false);
		           } catch(InterruptedException e) {
		               
		               e.printStackTrace();
		           }
		      	}  
   			}.start();
		}				
    }
    
    private void stay (){ //calls this method when players turn is over

		//sets gameplay buttons invisible
    	hitBtn.setVisible (false);
		stayBtn.setVisible (false);
		doubleDown.setVisible (false);
		
		//moving back card
		new Animation (cards [0][52], new Point2D.Double(320, 100), new Dimension (53, 72), 0, true);

		new Thread() {
	       	public void run() {
	           try {
		            Thread.sleep(1000);
		            
		            //changing back card to what was the hidden card
		            cards [hiddenDeck][hiddenCard].setBounds (320, 100, 53, 72);
					cardPanel.add (cards [hiddenDeck][hiddenCard]);
					cards [hiddenDeck][hiddenCard].setVisible (true);
					cards [0][52].setVisible (false);
					new Animation (cards [hiddenDeck][hiddenCard], new Point2D.Double(360, 100), new Dimension (53, 72), 0, true);
				
					new Thread() {
				       	public void run() {
				           try {
					            int delay = 0;
					            
					            Thread.sleep(1000);
					            
					            while (dealer.makeMove () == true){ //dealer draws cards until it gets value above 16
						
									delay += 500;
									hit (false, delay);
								}
								
								new Thread() {
							       	public void run() {
							           try {
								            
								            Thread.sleep(2000);
								            checkWinner (false, false);
							           } catch(InterruptedException e) {
							               
							               e.printStackTrace();
							           }
							      	}  
						   		}.start();
				           } catch(InterruptedException e) {
				               
				               e.printStackTrace();
				           }
				      	}  
			   		}.start();
			
	           } catch(InterruptedException e) {
	               
	               e.printStackTrace();
	           }
	      	}  
   		}.start();
    }
    
    private void displayDCard (Picture card, int delay){ //animates dealers cards
    	
    	//setting starting position for card
    	card.setBounds (670, 60, 53, 72);
    	
    	//adding onto dealer total
    	displayTotalD.setText (Integer.toString (dealer.getTotal ()));
    	
    	//adding card to card planel
    	cardPanel.add (card);
    	
    	//adding card to current card array
    	currentCards = GameLibrary.increaseArray (currentCards, card);
    	
    	//animating card
    	new Animation (card, new Point2D.Double(380 - moveCard * amountDCards, 100), new Dimension (53, 72), delay, true);	
    	amountDCards++;
    }
    
    private void displayPCard (Picture card, int delay){ //animates players cards
    
    	//setting starting position for card
    	card.setBounds (670, 60, 53, 72);
    	
    	//adding onto player total
    	displayTotalP.setText (Integer.toString (player.getTotal ()));
    	displayTotalP.setVisible (true);
    	
    	//adding card to card panel
    	cardPanel.add (card);
    	
    	//adding card to current card arra
    	currentCards = GameLibrary.increaseArray (currentCards, card);
    	
    	//animating card
    	new Animation (card, new Point2D.Double(380 - moveCard * amountPCards, 410), new Dimension (53, 72), delay, true);
    	amountPCards++;
    }
    
    private int chooseDeck () { //method that chooses a deck using a rng
    	
    	int deck = (int) (Math.random () * 7);
    	return deck;
    }
    
    private void bet (int amount){ //method that is called everytime the user increases their bet
    	
    	if ((currentBet + amount) > maxBet){ //makes sure bet is not goingabove 5000
    	    		
    		dialog.showMessage ("The max bet is 5000", "Invalid move");
    	}
    	else if (player.removeChips (amount) == true){ //adds on to current bet
    		
    		displayBalance.setText (curr.format((player.getChips ())).replaceAll("\\.00", ""));
    		currentBet+= amount;
    	}
    	else{ //tells user they dont have enough money to make the bet
    		
    		dialog.showMessage("You dont have enough chips to make this bet", "Invalid move");
    	}
    	
    	//displaying current bet
    	displayBet.setText (Integer.toString (currentBet)); 
    }
    
    private void checkWinner (boolean blackJackP, boolean blackJackD){ //method that checks who won, and resets the game for the next round
    	
    	if ((blackJackP == true) && (blackJackD == true)){ //checks if both player and user have black jacks
    		
    		dialog.showMessage ("You and dealer both had black jacks", "PUSH");
    		player.addChips (currentBet);
    	}
    	else if (blackJackP == true){ //checks if player has a blackjack
    		
    		dialog.showMessage ("You won with a black jack", "WIN");
    		double win = currentBet * 2.5;
    		int winInt = (int) win;
    		player.addChips (winInt);
    	}
    	else if (blackJackD == true){ //checks if dealer has a blackjack
    		
    		dialog.showMessage ("The dealer won with a Black Jack", "LOSS");
    	}
    	else if (player.getTotal () > 21){ //checks if player went bust
    		
    		dialog.showMessage ("You went bust", "LOSS");
    	}
    	else if (dealer.getTotal () > 21){ //checks if dealer went bust
    		
    		dialog.showMessage ("Dealer went bust", "WIN");
    		player.addChips (currentBet * 2);
    	}
    	else if (player.getTotal () == dealer.getTotal ()){
    		
    		dialog.showMessage ("You and dealer both had the same total", "PUSH");
    		player.addChips (currentBet);
    	}
    	else if (player.getTotal () > dealer.getTotal ()){ //checks if player had a higher total than dealer
    		
    		dialog.showMessage ("You won with a higher total", "WIN");
    		player.addChips (currentBet * 2);
    	}
    	else if (dealer.getTotal () > player.getTotal ()){ //checks if dealer had a higher total than player
    		
    		dialog.showMessage ("The dealer won with a higher total", "LOSS");
    	}
    	
    	//removes all components from cardPanel
		cardPanel.removeAll ();
		cardPanel.revalidate ();
    
    	//displays user current balance
    	displayBalance.setText (curr.format((player.getChips ())).replaceAll("\\.00", ""));

		//makes current balance equal to 0
		currentBet = 0;
		
		//makes player and dealer lose their card
		player.resetTotal ();
		dealer.resetTotal ();	
    	
    	//sets menu buttons to visible	
    	chipsPanel.setVisible (true);
    	completeBet.setVisible (true);
    	leaveTable.setVisible (true);
    	
    	//sets gameplay buttons to invisible
    	stayBtn.setVisible (false);
    	hitBtn.setVisible (false);
    	doubleDown.setVisible (false);	
    	
    	//sets amount of cards players have to zero
    	amountDCards = 0;
    	amountPCards = 0;
    	
    	for (int i = 0; i < 8; i++){
    		
    		//makes decks invisible and resets decks
    		GameLibrary.setDeckInvisible (cards [i]);
    		decks [i].resetDeck ();
    	}
    	
    	//resets displays
    	displayBet.setText ("0");
    	displayTotalP.setVisible (false);
    	displayTotalD.setVisible (false);
    }

    public static void main(String[] args) {
        
        //Uses for testing only
        new BJackUI (5000, "Test");
    }
}
