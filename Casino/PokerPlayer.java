// Author: Sumeet Jhand and Dalip Jandir
/* Description: Handles everything relating to a player playing poker.
				Includes a player's hand, bet, AI logic, and more.
				Also includes finding suits and getting the player's hand value.*/
// Note: Methods labelled "- DALIP" created by Dalip, everything else by Sumeet
// Date created: December 22nd, 2014
/* -------- Method List -------
 * PokerPlayer(int balance) - creates a new PokerPlayer with the specified balance
 * int makeMove(int amountRisked) - makes a move based on the risk amount inputted (return meanings: 0 = fold, 1 = call, 2 = all-in, anything else = desired raise)
 * int getHandValue() - returns an integer value rank based on the value of the cards the player has
 * int findSuit(int card, int cardNumber) - returns an integer value for a given card and cardNumber's suit
 * void setBet(int bet) - sets the bets for the player to bet
 * int getBet() - gets this player's current bet
 * void toPokerHand(int card) - places the specified card into the player's pokerHand
 * void resetPokerHand() - resets the player's hand to have no cards
 * int[] getPokerHand() - returns an array of every card in the player's hand
 */
import java.text.*;

public class PokerPlayer extends Player {
	
	//int array needed to store players hand	
	private int pokerHand[];
	
	//int needed to store players current bet
	private int bet;
	
	//die needed for AI moves
	private Die rng;
	private Die raise;
	private Die raiseMore;
        
    public PokerPlayer(int balance) {
    	
    	//creating player
    	super(balance);
    	
    	//intialzing private data
    	this.pokerHand = new int[7];
    	this.bet = 0;
    	resetPokerHand();
    	rng = new Die (100);
    	raise = new Die (50);	
    	raiseMore = new Die (100);
    }
    
    public int makeMove(int amountRisked) { //Method that returns an int according to what the AI has decided to do - DALIP
    	
    	//generates a random number that will randomly decide what the AI does according to percentages
    	rng.rollDie ();
    	
    	//generates a random number that will decide what the AI raises by
    	raise.rollDie ();
    	raiseMore.rollDie ();
    	
    	//gets the number of cards the player currently has
    	int length = GameLibrary.validLength(pokerHand);
    	
    	//making array to hold the numbers and suits of the card the AI currently has
    	int numbers [] = new int [length];
    	int suit [] = new int [length];
    	
    	for (int i = 0; i < length; i++){ //finds the numbers and suits of each card the AI has
    		
    		numbers [i] = pokerHand [i] / 4 + 1;
    		suit [i] = findSuit (pokerHand [i], numbers [i]);
    	}
    	
    	//calls on method to get hand value
    	int value = getHandValue ();
    	
    	if (length == 2){ //runs this if the AI only has two card
    		
    		if (numbers [0] == numbers [1]){ //runs if player has a pair
    			
    			//80% chance the AI will raise and 20% chance theyll call
    			if (rng.getValue () > 20 && amountRisked < 25) return (raise.getValue ());
    			else return 1;
    		}
    		else if (suit [0] == suit [1]){ //runs if the AIs two cards are have a matching suit
    			
    			//50% chance for call - 15% chance for raise - 35% chance for fold
    			if (rng.getValue () > 50) return 1;
    			else if (rng.getValue () <= 50 && rng.getValue () >= 35) return (raise.getValue ());
    			else return 0;
    		}
    		else if ((Math.abs (numbers [0] - numbers [1])) == 3){ //runs if AI has cards that are close to each other
    			
    			//40% chance for call - 10% chance for raise - 50% chance for fold
    			if (rng.getValue () > 60) return 1;
    			else if (rng.getValue () <= 60 && rng.getValue () >= 50) return (raise.getValue ());
    			else return 0;
    		}
    		else { //runs if AI has nothing
    			
    			//40% chance for call - 5% chance for raise - 55% chance for fold
				if (rng.getValue () > 60) return 1;
    			else if (rng.getValue () <= 60 && rng.getValue () >= 55) return (raise.getValue ());
    			else return 0;
    		}
    	}
    	else{ //runs if AI has 5 or more card
    		
    		if (value >= 1000){ //runs if AI has Royal Flush
    			
    			//100% chance for all in
    			return 2;
    		}
    		else if (value >= 950){//runs if AI has straight flush
    			
    			//80% chance for all in - 10% chance for all in - 10% chance for call
    			if (rng.getValue () >= 20) return 2;
    			else if (rng.getValue () >= 10) return (raiseMore.getValue ());
    			else return 1;
    		}
    		else if (value >= 900){//runs if AI has four of a kind
    			
    			//40% chance for all in - 35% chance for raise - 25% chance for call
    			if (rng.getValue () >= 60) return 2;
    			else if (rng.getValue () >= 25) return (raiseMore.getValue ());
    			else return 1;
    		}
    		else if (value >= 850){ //runs if AI has a full house
    			
    			//20% chance for all in - 45% chance for raise - 35% chance for call
    			if (rng.getValue () >= 80) return 2;
    			else if (rng.getValue () >= 35) return (raiseMore.getValue ());
    			else return 1;
    		}
    		else if (value >= 800){//runs if AI has flush
    			
    			//40% chance for all in - 35% chance for raise - 25% chance for call
    			if (rng.getValue () >= 90) return 2;
    			else if (rng.getValue () >= 20) return (raise.getValue ());
    			else return 1;
    		}
    		else if (value >= 500){ //runs if AI has straight
    			
    			//60% chance for raise - 40% chance for call if risk is below 100 
    			if (rng.getValue () >= 80) return (raise.getValue ());
    			else if (amountRisked <= 100) return 1;
    			else return 0;
    		}
    		else if (value >= 300){ //runs if AI has three of a kind
    			
    			//35% chance for raise - 15% chance for call if risk is below 50 
    			if (rng.getValue () >= 90) return (raise.getValue ());
    			else if (amountRisked <= 50) return 1;
    			else return 0;
    		}
    		else if (value >= 100){ //runs if AI has a pair
    			
    			//10% chance for raise - 90% chance for call if risk is below 30 
    			if (rng.getValue () >= 95) return (raise.getValue ());
    			else if (amountRisked <= 30) return 1;
    			else return 0;
    		}
    		else { //runs if AI has notihing
    			
    			//1% chance for raise - 29 chance for call if risk is below 10 - 70% chance for fold
    			if (rng.getValue () >= 99) return (raise.getValue ());
    			else if (rng.getValue () == 70 && amountRisked <= 10) return 1;
    			else return 0;
    		}
    	}
    }
    
    public int getHandValue (){ //gets the players handValue - DALIP
    	
    	//int to store hand value
    	int handValue = 0;
    	
    	//gets the amount of cards the player currently has
    	int length = GameLibrary.validLength(pokerHand);
    	
    	//sorts the cards
    	ArrayLibrary.rippleSort (pokerHand, true);
    	
    	//store the suit and numbers of the cards
    	int numbers [] = new int [length];
    	int suit [] = new int [length];
    	
    	for (int i = 0; i < length; i++){ //funds the numbers and suits of each card
    		
    		numbers [i] = pokerHand [i] / 4 + 1;
    		suit [i] = findSuit (pokerHand [i], numbers [i]);
    	}
    	
    	//array to store matching numbers
    	int matchingNumbers [] = new int [13];
    	for (int i = 0; i < matchingNumbers.length; i++)	
    		matchingNumbers [i] = 0;
    	
    	//array to store matching suits	
    	int matchingSuits [] = new int [4];
    	for (int i = 0; i < matchingSuits.length; i++)	
    		matchingSuits [i] = 0;
    	
    	//ints to store straights and straight flushes	
		int straight = 0; 	
		int straightFlush = 0;	
    	
    	//array to store possible hands
    	int hands [] = new int [7];
    	for (int i = 0; i < hands.length; i++)	
    		hands [i] = 0;
    	
    	for (int i = 0; i < length; i++){ //checks for matching numbers and stores them into an array
    		
    		for (int j = 0; j < matchingNumbers.length; j++){
    			
    			if (numbers [i] == (j + 1)){
    				
    				matchingNumbers [j]++;
    			}
    		}
    	}

    	for (int i = 0; i < matchingNumbers.length; i++){ //runs through all 13 numbers to see matches
    		
			if (matchingNumbers [i] == 4){ //checks for a four of a kind
				
				if (i == 0){
					hands [4] = 14;
				}
				else{
					hands [4] = i;
				}
			}
			else if (matchingNumbers [i] == 3){ //checks for a three of a kind
				
				if (i == 0){
					hands [1] = 14;
				}
				else{
					hands [1] = i;
				}
			}
			else if (matchingNumbers [i] == 2){ //checks for pairs
				
				if (i == 0){
					hands [0] += 14;
				}
				else{
					hands [0] += i;
				}
			}
    	}
    	
    	for (int i = 0; i < length; i++){ //checks for matching suits
    		
    		for (int j = 0; j < matchingSuits.length; j++){
    			
    			if (suit [i] == (j / 4)){
    				
    				matchingSuits [j]++;
    			}
    		}
    	}
    	
    	for (int i = 0; i < matchingSuits.length; i++){ //checks for 5 matching suits
    		
    		if (matchingSuits [i] >= 5){
    			
    			hands [3] = 1 + numbers [0];
    		}
    	}
    	
    	for (int i = 0; i < length - 1; i++){ //checks for straights and straight flushes
    		
    		if (numbers [i] - numbers [i + 1] == 1){
    			
    			straight ++;
    				
    			if (suit [i] == suit [i + 1]){
    				
    				straightFlush ++;
    			}	
    		}
    	}
    	
    	if (straight == 5){ //checks to see if there was a straight
    		
    		hands [2] = 1 + numbers [length - 1];
    	}
    	
    	if (straightFlush == 5){ //checks to see if there was a straight flush
    		
    		hands [5] = 1 + numbers [length - 1];
    	}
    	
    	//checks for a royal flush
    	if (length >= 5){
    	
    		if (numbers [0] == 13 && numbers [1] == 12 && numbers [2] == 11 && numbers [3] == 10 && numbers [length - 1] == 1 && suit [0] == suit [1] && suit [0] == suit [2] && suit [0] == suit [3] && suit [0] == suit [4])
    			hands [6] = 1;	
    	}				

    	if (hands [6] != 0) { //runs if player has royal flush
    		
    		handValue += (800 + hands [6]);
    	}
    	else if (hands [5] != 0) { //runs if player has straight flush
    		
    		handValue += (700 + hands [5]);
    	}
    	else if (hands [4] != 0) { //runs if player has four of a kind
    		
    		handValue += (600 + hands [4]);
    	}
    	else if (hands [1] != 0 && hands [2] != 0) { //runs if player has a full house
    		
    		handValue += (500 + hands [1] + hands [2]);
    	}	
    	else if (hands [3] != 0) { //runs if player has a flush
    		
    		handValue += (400 + hands [3]);
    	}
    	else if (hands [2] != 0) { //runs if player has a straight
    		
    		handValue += (300 + hands [2]);
    	}
    	else if (hands [1] != 0) { //runs if player has a three of a kind
    		
    		handValue += (200 + hands [1]);
    	}
    	else if (hands [0] != 0) { //runs if player has a pair
    		
    		handValue += (100 + hands [0]);
    	}
    	else{ //runs if player has nothing
    		
    		if (numbers [numbers.length - 1] == 1){
    			
    			handValue += 14;
    		}
    		else
    			
    			handValue += numbers [0];
    	}
    	
    	//returning hand value
    	return handValue;	
    }

    
    public int findSuit (int card, int cardNumber){ //method that will find the suit of each card - DALIP
    	
    	int suit = 0;
    	double preSuit = card / 4 - cardNumber;
    	
    	if (preSuit == 0) suit = 1;
    	else if (preSuit == 0.25) suit = 2;
    	else if (preSuit == 0.5) suit = 3;
    	else if (preSuit == 0.75) suit = 4;
    	
    	return suit;   
    	
    }	
    
    public void setBet(int bet) { //method that will set the players bet
    	this.bet = bet;
    }
    
    public int getBet() { //method to get the players bet
    	return this.bet;
    }
    
    public void toPokerHand(int card) { //method used to add a card to players hand
    	for (int i = 0; i < this.pokerHand.length; i++) {
    		if (this.pokerHand[i] == -1) {
    			this.pokerHand[i] = card;
    			break;
    		}
    	}
    }
    
    public void resetPokerHand() { //method used to reset the players hand
    	for (int i = 0; i < this.pokerHand.length; i++)
    		this.pokerHand[i] = -1;
    }
    
    public int[] getPokerHand() { //method used to get the player hands
    	return this.pokerHand;
    }
    
    public static void main(String[] args) {
    	
    	//*****************TESTING ONLY**********************
        PokerPlayer plyr = new PokerPlayer(500);
        plyr.toPokerHand(0);
    	plyr.toPokerHand(48);
    	plyr.toPokerHand(49);
    	plyr.toPokerHand(50);
    	plyr.toPokerHand(51);
    	
    	System.out.println(plyr.getHandValue ());
    	System.out.println(plyr.getPokerHand()[0]);
    	System.out.println(plyr.getPokerHand()[4]);
    	
    	plyr.resetPokerHand ();
    		
    	plyr.toPokerHand(0);
		System.out.println(plyr.getHandValue ());
    	System.out.println(plyr.getPokerHand()[0]);
    	
    	plyr.toPokerHand(4);
    	plyr.toPokerHand(12);
    	plyr.toPokerHand(16);
    	plyr.toPokerHand(40);
    	plyr.toPokerHand(24);
    	
    	System.out.println(plyr.getHandValue ());
    	System.out.println(plyr.getPokerHand()[0]);
    	System.out.println(plyr.getPokerHand()[4]);
    	
    	plyr.resetPokerHand ();
    	
    	plyr.toPokerHand(4);
    	plyr.toPokerHand(8);
    	plyr.toPokerHand(12);
    	plyr.toPokerHand(16);
    	plyr.toPokerHand(20);
    	
    	System.out.println(plyr.getHandValue ());
    	
    	plyr.resetPokerHand ();
    	
    	plyr.toPokerHand(0);
    	plyr.toPokerHand(1);
    	plyr.toPokerHand(2);
    	plyr.toPokerHand(4);
    	plyr.toPokerHand(5);
    	
    	System.out.println(plyr.getHandValue ());
    	
    	
    	//*****************TESTING ONLY**********************
    }
}
