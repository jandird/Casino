//The "Player" Class
//Authors: Sarfaraz (Edited by: Dalip and Sumeet)
//Description:  Handles actions related to altering betting chips. Able to set amount of chips, remove chips, and add chips.
/* Methods:
 		
 * public Player (int chips){ //Constructor to set the amount of chips the user begins with
 
 * public int getChips (){ //Access method to find out how many chips the user currently has
 
 * public void addChips (int amount){ //Access method to deposit chips
 
 * public boolean removeChips (int amount){ //Access method to remove chips. Method return true or false according to whether there is a suffiecent amount of chips or not
 
 * public void setChips (int amount){ //Access method to completely change the amount of chips
 
 * public void setChips (int amount){ //Access method to completely change the amount of chips
 
*/

public class Player {

	//private date to store the amount of chips the user currently has
	private int chips;
    
    public Player (int chips) //Constructor to set the amount of chips the user begins with
    { 
    	this.chips = chips;
    }
    
    public int getChips () //Access method to find out how many chips the user currently has
    { 
    	return this.chips;
    }
    
    public void addChips (int amount) //Access method to deposit chips
    { 
    	this.chips += amount;
    }
    
    public boolean removeChips (int amount) //Access method to remove chips. Method return true or false according to whether there is a suffiecent amount of chips or not
    { 
    	if (amount > this.chips) //Returns false and does not remove chips if amount being removed is higher then amount of chips
    		return false;
    
    	else //Returns true and remove chips if there is a sufficent amount of chips
    	{ 
    		this.chips -= amount;
    		return true;
    	}	
    }
    
    public void setChips (int amount) //Access method to completely change the amount of chips
    { 
    	this.chips = amount;
    }

    public static void main(String[] args) {
    	
    	//*************TESTING ONLY********************//
    	
    	Player chip = new Player (500);
    	System.out.println (chip.getChips ());
    	
    	chip.addChips (250);
    	System.out.println (chip.getChips ());
    	
    	Boolean check = chip.removeChips (800);
    	System.out.println (check);
    	System.out.println (chip.getChips ());
    	
    	check = chip.removeChips (751);
    	System.out.println (check);
    	System.out.println (chip.getChips ());
    	
    	check = chip.removeChips (750);
    	System.out.println (check);
    	System.out.println (chip.getChips ());
    	
    	chip.setChips (800); 
    	System.out.println (chip.getChips ());
    	
    	//*************TESTING ONLY********************//
    }
}