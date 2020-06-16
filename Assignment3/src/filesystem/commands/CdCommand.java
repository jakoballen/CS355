/*
 * CDCommand - command to change directory to a specified directory
 * 
 * Created - Paul J. Wagner, 4/27/2014 - format: cd newdir (name or .. for parent)
 */
package filesystem.commands;

import filesystem.general.FileSystem;

public class CdCommand extends AbstractCommand {
	// data
	// -- brings fs down from AbstractCommand, don't need to add that variable here
	
	// methods
	// constructors
	public CdCommand() {
		// none at this time
	}
	
	public CdCommand(FileSystem fs) {
		this.fs = fs;
	}

	// other methods
	// -- execute
	public String execute(String [] params) {
		// TODO: this is just a start - can be changed for directory below vs. .. (directory above),
		//       also what if directory below doesn't exist?
		String outputString = "cd> cwd changed to " + params[1];
		return fs.setCurrentWorkingDirectory(params[1]);
	}

}	// end - class MkdirCommand
