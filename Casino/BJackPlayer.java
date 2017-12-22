//The "BJackPlayer" Class
//Authors: Dalip 
//Description: Creates a player that can be used to store data for blackjack
/* Methods:
 
 * public BJackPlayer(int chips) { //basic constructor that takes 
 
 * public boolean makeMove (){ //method for AI that returns whether they should draw a card or not
 
 * public void giveCard (int card){ //method that gives the player a card
 
 * public void resetTotal (){ //method that resets the total
*/

public class BJackPlayer extends Player {
      
    //private data that stores whether the user total and whether they have an ace  
    private int total;
    private boolean ace;
    
    public BJackPlayer(int chips) { //basic constructor that takes 
    	
    	//creating player
    	super (chips);
    }
    
    public boolean makeMove (){ //method for AI that returns whether they should draw a card or not
    	
    	if (this.total >= 17)	
    		return false;
    	else
    		return true;
    }
    
    public void giveCard (int card){ //method that gives the player a card
    	
    	if ((card > 1) && (card < 11))
    		this.total += card;
    	else if (card >= 11)	
    		this.total += 10;
    	else 	
    		this.ace = true;
    }
    
    public int getTotal (){ //method that returns the players total
    	
    	if (this.ace == false) //checks if there is an ance
    		return this.total;
    	else {
    		
    		if (this.total <= 10) //changes total according to what the current total is
    			return (this.total + 11);
    		else{
    			return (this.total + 1);
    		}	
    	}	
    }
    
    public void resetTotal (){ //method that resets the total
    	
    	this.total = 0;
    }

    public static void main(String[] args) {
    	
    	//****************TESTING ONLY*******************
    	
    	BJackPlayer player = new BJackPlayer (500);
    	
    	player.giveCard (7);
    	
    	System.out.println (player.makeMove ());
    	
    	player.giveCard (10);
    	
    	System.out.println (player.makeMove ());
    	
    	System.out.println (player.getTotal ());
    	
    	player.resetTotal ();
    	
    	System.out.println (player.getTotal ());
        
        //****************TESTING ONLY*******************
    }
}
