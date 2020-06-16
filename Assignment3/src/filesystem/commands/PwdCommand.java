/*
 * PwdCommand - command class for pwd (show current directory)
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.commands;

import filesystem.general.FileSystem;

public class PwdCommand extends AbstractCommand {
	// data
	// -- fs brought down from AbstractCommand
	
	// methods
	// constructors
	public PwdCommand() {
		// nothing so far
	}
	
	public PwdCommand(FileSystem fs) {
		this.fs = fs;
	}

	// other methods
	public String execute(String [] params) {
		String outputString = "";

		outputString = "pwd> " + fs.getCurrentWorkingDirectoryString();
		return outputString;
	}

}	// end - class PwdCommand
