// Author: Sumeet Jhand, Campos, whoever made that textbook...
// Description: Contains various sorting and searching algorithms, as well as (less related) loading/saving methods and a validLength method
// Date created: November 14, 2014
/* -------- Method List --------
 * int linearSearch(String array[], String key) - Performs a linear search algorithm, attempting to find String key in String[] (returns int index when key is found, -1 if not found)
 * int binarySearch(String array[], String key) - Performs a binary search algorithm, attempting to find String key in String[] (returns int index when key is found, -1 if not found)
 * void bubbleSort(String array[], boolean descend) - Sorts a String[] with a bubble sort algorithm in either descending (true) or ascending (false) order
 * void rippleSort(String array[], boolean descend) - Sorts a String[] with a ripple sort algorithm in either descending (true) or ascending (false) order
 * void insertSort(String array[], boolean descend) - Sorts a String[] with a insertion sort algorithm in either descending (true) or ascending (false) order
 * void swap(String array[], int first, int second) - Swaps two elements in a String array[] given their indexes
 * void save(String array[], String filename) - Saves a String[] into a file named String filename locally
 * String[] load(String filename) - Loads a file with file name String filename and returns a String[] with each line of that file in each element
 * int vaildLength(String array[]) - Returns the length of an array disregarding null elements (helps to avoid many NullPointerExceptions in SISUserInterface)
 */
import java.io.*;
import javax.swing.*;

public class ArrayLibrary {
        
    public static int linearSearch(String array[], String key) {
    	for (int i = 0; i < array.length; i++) {
    		if (array[i].equalsIgnoreCase(key))
    			return i; // found key
    	}
    	return -1; // key not found
    }
    
    public static int binarySearch(String array[], String key) { // requires lexicographically sorted array
    	int low = 0;
    	int high = array.length - 1;
    	int mid;
    	
    	while (low <= high) {
    		mid = (high + low) / 2;
    		if (key.equalsIgnoreCase(array[mid]))
    			return mid; // found key
    		else if (key.compareToIgnoreCase(array[mid]) < 0)
    			high = mid - 1;
    		else if (key.compareToIgnoreCase(array[mid]) > 0)
    			low = mid + 1;
    	}
    	return -1; // key not found
    }
    
    public static void bubbleSort(String array[], boolean descend) {
    	for (int pass = 1; pass < validLength(array); pass++) {
    		for (int element = 0; element < validLength(array) - 1; element++) {
    			if ((array[element].compareToIgnoreCase(array[element + 1]) > 0) // descending order
    				&& (descend))
    					swap(array, element, element + 1); // swaps two elements
    			else if ((array[element].compareToIgnoreCase(array[element + 1]) < 0) // ascending order
    				&& (!descend))
    					swap(array, element, element + 1); // swaps two elements
    		}
    	}
    }
    
    public static void rippleSort(String array[], boolean descend) {
    	for (int i = 0; i <= validLength(array) - 2; i++) {
    		for (int j = i + 1; j <= validLength(array) - 1; j++) {
    			if ((array[j].compareToIgnoreCase(array[i]) < 0) // descending order
    				&& (descend))
    				swap(array, j, i); // swaps two elements
    			else if ((array[j].compareToIgnoreCase(array[i]) > 0) // ascending order
    				&& (!descend))
    				swap(array, j, i);
    		}
    	}
    }
    
    public static void rippleSort(int array[], boolean descend) {
    	for (int i = 0; i <= array.length - 2; i++) {
    		for (int j = i + 1; j <= array.length - 1; j++) {
    			if ((array[j] > array[i]) // descending order
    				&& (descend))
    				swap(array, j, i); // swaps two elements
    			else if ((array[j] < array[i]) // ascending order
    				&& (!descend))
    				swap(array, j, i);
    		}
    	}
    }
    
    public static void insertSort(String array[], boolean descend) {
    	for (int top = 1; top < validLength(array); top++) {
    		String item = array[top];
    		int i = top;
    		while ((i > 0) && (item.compareToIgnoreCase(array[i - 1]) < 0) && (descend)) { // descending order
    			array[i] = array[i - 1]; // swaps two elements
    			i--;
    		}
    		while ((i > 0) && (item.compareToIgnoreCase(array[i - 1]) > 0) && (!descend)) { // ascending order
    			array[i] = array[i - 1]; // swaps two elements
    			i--;
    		}
    		array[i] = item;
    	}
    }
    
    public static void swap(String array[], int first, int second) {
    	// swaps two elements given their indexes (using temp variables)
    	String hold;
    	hold = array[first];
    	array[first] = array[second];
    	array[second] = hold;
    }
    
    public static void swap(int array[], int first, int second) {
    	// swaps two elements given their indexes (using temp variables)
    	int hold;
    	hold = array[first];
    	array[first] = array[second];
    	array[second] = hold;
    }
    
    public static void save(String array[], String fileName) throws IOException {
    	// FileWriter and BufferedWriter, used to open and write to the file
		FileWriter fw = new FileWriter(fileName);
    	BufferedWriter bw = new BufferedWriter(fw);
    	
    	// writes each element of the array into the file, with line breaks between each element written
    	for (int i = 0; i < validLength(array); i++) {
    		bw.write(array[i]);
    		if (i != array.length - 1)
    			bw.newLine();
    	}
    	// closes the file
    	bw.close();
    }
    
    public static void create(String fileName) throws IOException {
    	// FileWriter and BufferedWriter, used create the file
		FileWriter fw = new FileWriter(fileName);
    	BufferedWriter bw = new BufferedWriter(fw);
    	
    	// closes the file right after the file has been created
    	bw.close();
    }
    
    public static String[] load(String fileName) throws IOException {
    	int lines = 0; // initializes the amount of lines in the file
    	
    	// declares FileReader and BufferedReader to allow for opening and reading the file
		FileReader fr = new FileReader(fileName);
    	BufferedReader br = new BufferedReader(fr);
    	
    	// while a null line is not encountered, add one to lines
    	while (br.readLine() != null) {
    		lines++;
    	}
    	
    	// creates a new String[] with length lines and close the file
    	String[] ins = new String[lines];
    	br.close();
    	
    	// reopen the file (to start from the beginning) and put each line into a seperate element in the String[]
    	FileReader frn = new FileReader(fileName);
    	BufferedReader brn = new BufferedReader(frn);
    	for (int i = 0; i < ins.length; i++) {
    		ins[i] = brn.readLine();
    	}
    	
    	// close file and return the String[] with the file's contents
    	brn.close();
    	return ins;
    }
    
    public static int validLength(String array[]) {
    	// initializes the length of the String[] and adds one to length
    	// as long as the element is not null
    	int length = 0;
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] != null) {
    			length++;
    		}
    	}
    	return length; // returns the length
    }
    
    // testing main
    public static void main(String[] args) throws IOException {
    	// declares and creates various variables, including the aryStr array and its contents (loaded when program is started),
    	// aryStrFile array set to null (no file has been loaded when program is started), buttons array with buttons' text,
    	// JTextArea area that shows the contents of either aryStr or aryStrFile, disallows editing for that JTextArea, and sets
    	// boolean fileLoaded to false at start
    	String[] aryStr = {"This", "is", "just", "a", "testing", "main", "method", "nothing", "more", "nothing", "less"};
    	String[] aryStrFile = null;
    	String[] buttons = {"Binary Search", "Linear Search", "Bubble Sort", "Ripple Sort", "Insertion Sort", "Save", "Load"};
    	JTextArea area = new JTextArea();
    	area.setEditable(false);
    	boolean fileLoaded = false;
    	
    	while (true) { // loops indefinitely until program exits or is broken out of
    		// sets key to -2 (just in case the searching algorithms some how don't set a value for key) and creates column labels for the output area
    		int key = -2;
    		area.setText("[INDEX]\t[STRING]\n");
    		
    		if (!fileLoaded) {
    			// appends each element of aryStr to the output area if a file has not been loaded
    			for (int i = 0; i < aryStr.length; i++) {
    				area.append(i + "\t");
    				area.append(aryStr[i]);
    				if (i != aryStr.length - 1)
    					area.append("\n");
	    		}
    		} else {
    			// appends each element of aryStrFile to the output area if a file has been loaded
    			for (int i = 0; i < aryStrFile.length; i++) {
    				area.append(i + "\t");
    				area.append(aryStrFile[i]);
    				if (i != aryStrFile.length - 1)
    					area.append("\n");
	    		}
    		}
	    	
	    	// shows a dialog containing the output area and all the buttons all on the same line (I would make them on different lines if I knew how to do that on option dialogs)	
			int btnPick = JOptionPane.showOptionDialog (null, area, "Searching, Sorting, Saving & Loading Test", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
	    	
	    	if (btnPick == 0) { // performs binary search
	    		// Prompts the user to ensure the array is sorted in descending order (requirement for binary searching to work)
	    		// gets index for given input in the appropriate array (file loaded or not)
	    		JOptionPane.showMessageDialog(null, "Please ensure the array is sorted in descending order.", "Notice", JOptionPane.WARNING_MESSAGE);
	    		key = binarySearch(((!fileLoaded) ? aryStr : aryStrFile), JOptionPane.showInputDialog("Enter String to search for:"));
	    		
	    		if (key == -1)
	    			JOptionPane.showMessageDialog(null, "String not found."); // returns string not found if index is -1
	    		else
	    			// returns String found with the key's element and the key if the key is anything other than -1
	    			JOptionPane.showMessageDialog(null, "String found: \"" + ((!fileLoaded) ? aryStr[key] : aryStrFile[key]) + "\"\nIndex: " + key);
	    	} else if (btnPick == 1) { // performs linear search
	    		// same comments as above, but it performs a linear searching algorithm instead
	    		key = linearSearch(((!fileLoaded) ? aryStr : aryStrFile), JOptionPane.showInputDialog("Enter String to search for:"));
	    		
	    		if (key == -1)
	    			JOptionPane.showMessageDialog(null, "String not found.");
	    		else
	    			JOptionPane.showMessageDialog(null, "String found: \"" + ((!fileLoaded) ? aryStr[key] : aryStrFile[key]) + "\"\nIndex: " + key);
	    	} else if (btnPick == 2) // performs bubble sort
	    			// performs a bubble sort alogrithm, sorting the appropriate array (if file has been loaded or not) in either
	    			// ascending or descending order depending on what the user inputs
	    			bubbleSort(((!fileLoaded) ? aryStr : aryStrFile), ((JOptionPane.showInputDialog("Enter \"ascend\"" 
	    				+ " for ascending order or\nanything else for descending order:").equalsIgnoreCase("ascend")) ? false : true));
    		else if (btnPick == 3) // performs ripple sort
    				// same comment as above, but is instead a ripple sort algorithm
    				rippleSort(((!fileLoaded) ? aryStr : aryStrFile), ((JOptionPane.showInputDialog("Enter \"ascend\"" 
	    				+ " for ascending order or\nanything else for descending order:").equalsIgnoreCase("ascend")) ? false : true));
    		else if (btnPick == 4) // performs insertion sort
    				// same comment as above, but is instead a insertion sort algorithm
    				insertSort(((!fileLoaded) ? aryStr : aryStrFile), ((JOptionPane.showInputDialog("Enter \"ascend\"" 
	    				+ " for ascending order or\nanything else for descending order:").equalsIgnoreCase("ascend")) ? false : true));
    		else if (btnPick == 5) // saves to a file
    			save(((!fileLoaded) ? aryStr : aryStrFile), JOptionPane.showInputDialog(null, "Enter file name to save as (with extension):", "output.txt"));
    		else if (btnPick == 6) { // loads from a file
    			aryStrFile = load(JOptionPane.showInputDialog(null, "Enter file name to load (with extension):", "test.txt"));
    			fileLoaded = true; // sets fileLoaded to true, to indicate that a file has been loaded
	    	} else {
	    		System.exit(0); // if any other button is pressed other than the buttons above, exit the program (the only other button in this case would be the window's 'X' button)
	    	}
    	}
    }
}
