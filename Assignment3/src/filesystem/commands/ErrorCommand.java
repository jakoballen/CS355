/*
 * ErrorCommand - command class for default error situations (no legitimate command given) 
 *
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.commands;

import filesystem.general.FileSystem;

public class ErrorCommand extends AbstractCommand {
	// data
	
	// methods
	// constructors
	public ErrorCommand() {
		// none at this time
	}
	
	public ErrorCommand(FileSystem fs) {
		this.fs = fs;
	}

	// other methods
	public String execute(String [] params) {
		return ("Error - invalid command");
	}
}	// end - class ErrorCommand
