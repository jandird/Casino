// Author: Sarfaraz Jahangir (Edited by Sumeet and Dalip)
// Date created: December 24th, 2014
/* Description: Creates a deck of cards (excluding Jokers) with the ability to remove cards from it. 
 * 				Supports resetting the deck as well as picking a random card from it. */
/* --- Method List: ---
 * int getCard() - picks a random card and removes that card from the deck when called, returning the card's ID
 * void resetDeck() - resets the deck so that all cards are present
 */

public class Deck {
	
	private boolean[] deck;
	private int count = 0;
        
    public Deck() // Creates a standard deck of 52 cards using an array
    {
    	deck = new boolean[52];
    	resetDeck();
    }
    
    public int getCard() // Picks a random card and removes that card from the array when called, returning the card's ID
    {
    	int cardPick;
    		
    	while(true) 
    	{
    		cardPick = (int) Math.floor(Math.random() * 52);
    		
    		if (deck[cardPick]) 
    		{
    			count++;
    			deck[cardPick] = false;
    			break;
    		} 
    		else if (count > 51) 
    		{
    			cardPick = -1;
    			break;
    		}
    	}
    	return cardPick;
    }
    
    public void resetDeck() // Resets all cards
    {
    	count = 0;
    	for (int i = 0; i < deck.length; i++)
    		deck[i] = true;
    }
    
    // testing main
    public static void main(String[] args) 
    {
    	Deck deck = new Deck();
    	
    	System.out.println("All cards randomly drawn from deck to make it empty:");
    	for (int i = 0; i < 52; i++) 
    	{
    		if (i == 51) 
    		{
    			System.out.print(deck.getCard());
    			break;
    		} 
    		else
    		System.out.print(deck.getCard() + ", ");
    	}
    	
    	System.out.print("\n\n");
    	System.out.println("Attempting to draw from empty deck:");
    	System.out.print(deck.getCard());
    	
    	System.out.print("\n\n");
    	System.out.println("Resetting empty deck and randomly drawing all cards again:");
    	deck.resetDeck();
    	for (int i = 0; i < 52; i++) 
    	{
    		if (i == 51) 
    		{
    			System.out.print(deck.getCard());
    			break;
    		} 
    		else
    		System.out.print(deck.getCard() + ", ");
    	}
    }
}
