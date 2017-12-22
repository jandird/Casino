//The "BJackUI" Class
//Authors: Dalip and Sumeet (Names will be put beside methods) 
//Description: Methods that are useful for black jack and poker
/* Method List:
 
 * public static int[] moneyToChips(int curBet) { //method that calculates which chips will be needed to display the total bet - DALIP
 
 * public static int validLength(int[] array) { //method that calculates how many cardsd the user currently has in their hand - SUMEET
 
 * public static void setDeckImages (Picture cards []){ //method that sets the pictures for the cards - DALIP
 
 * public static int [] addCard (int [] array) { //method that copies an int array into a new array and increases its size by 1 - DALIP
 
 * public static void setDeckInvisible (Picture cards []){ //method that sets all pictures to invisible - DALIP
 
 * public static void setBtnTheme(JButton btn, Color c1, Color c2) { //method that sets the JButton's foreground (text) to Color c1 and its background to Color c2 - SUMEET
 
 * public static Picture [] increaseArray (Picture currentArray [], Picture newPic){ //method that increases the size of a picture array - DALIP
*/

//importing all classes needed
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.awt.geom.*;

public class GameLibrary {
	
	public static int[] moneyToChips(int curBet) { //method that calculates which chips will be needed to display the total bet - DALIP
    	
    	//array needed to store amounts
    	int chipsToDisplay [] = new int [5];
    	
    	//calculating how many chips needed to display bet and storing each amount in the array
    	chipsToDisplay [4] = (curBet / 500);
    	curBet -= (chipsToDisplay [4] * 500);
    	chipsToDisplay [3] = (curBet / 100);
    	curBet -= (chipsToDisplay [3] * 100);
    	chipsToDisplay [2] = (curBet / 25);
    	curBet -= (chipsToDisplay [2] * 25);
    	chipsToDisplay [1] = (curBet / 5);
    	curBet -= (chipsToDisplay [1] * 5);
    	chipsToDisplay [0] = (curBet / 1);
    	
    	//returning array
    	return chipsToDisplay;
	}
	
	public static int validLength(int[] array) { //method that calculates how many cardsd the user currently has in their hand - SUMEET
		
		//creating int to store amount
		int length = 0;
		
		//checking how many cards the user actually has
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1)
				length++;
		}
		
		//returning amount
		return length;
	}

    public static void setDeckImages (Picture cards []){ //method that sets the pictures for the cards - DALIP
    	
    	//setting pictures to the arrays
    	for (int i = 0; i < cards.length - 1; i++){
    		
    		cards [i] = new Picture (new ImageIcon(GameLibrary.class.getResource("Cards/" + (i + 1) + ".png")));
    	}
    	cards [52] = new Picture (new ImageIcon(GameLibrary.class.getResource("Cards/back.png")));
    }
    
    public static int [] addCard (int [] array) { //method that copies an int array into a new array and increases its size by 1 - DALIP
        
        return Arrays.copyOf(array, array.length + 1);
    }
    
    public static void setDeckInvisible (Picture cards []){ //method that sets all pictures to invisible - DALIP
    	
    	for (int i = 0; i < cards.length; i++){
    		
    		cards [i].setVisible (false);
    	}
    }
    
    public static void setBtnTheme(JButton btn, Color c1, Color c2) { //method that sets the JButton's foreground (text) to Color c1 and its background to Color c2 - SUMEET
    	
    	btn.setForeground(c1);
    	btn.setBackground(c2);
    }
    
    public static Picture [] increaseArray (Picture currentArray [], Picture newPic){ //method that increases the size of a picture array - DALIP
    	
    	//creating enlarged array
    	Picture newArray [] = new Picture [currentArray.length + 1];
    	
    	//copying array into enlarged array
    	for (int i = 0; i < currentArray.length; i++)	
    		newArray [i] = currentArray [i];
    	
    	//putting new picture into array	
    	newArray [currentArray.length] = newPic;
    	
    	//returning enlarged array
    	return newArray;		
    }
}
