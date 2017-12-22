/**
 * @(#)Die.java
 *
 *
 * @author 
 * @version 1.00 2014/10/14
 */

public class Die {
        
    private int FACES = 6; // attributes of a die
    private int value; 
        
    /**
     * Creates a new instance of <code>Die</code>.
     */
    public Die() {
    	rollDie();
    }
    
    public Die(int faces) {
    	this.FACES = faces;
    	rollDie();
    }
    
    // method to roll the die
    public void rollDie() {
    	this.value = (int)(Math.random()* this.FACES + 1);
    }
    
    // method to read the die value
    public int getValue() {
    	return this.value;
    }
    
    // checks if two given die values are equal
    public boolean checkDieValue(int d1, int d2) {
    	if (d1 == d2) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public String toString() {
    	return String.valueOf(this.value);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Die d;
        d = new Die();
        
        System.out.println(d.getValue());
    }
}
