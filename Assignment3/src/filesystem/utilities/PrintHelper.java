/*
 * PrintHelper - class for print utilities
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.utilities;

public class PrintHelper {
	// data
	// NONE
	
	// methods
	// constructors
	// -- default constructor
	public PrintHelper() {
		// nothing
	}
	
	// -- generate spaces for tree display
	public String genSpaces (int number) {
		String outputString = "";
		for (int index = 1; index <= 2 * number; index++) {
			outputString += " ";
		}
		return outputString;
	}
}	// end - class PrintHelper

