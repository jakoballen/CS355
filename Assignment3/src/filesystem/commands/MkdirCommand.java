/*
 * MkdirCommand - command class to make directory in current directory
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 * format: mkdir <name>
 */
package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;

public class MkdirCommand extends AbstractCommand {
	// data
	// -- brings fs down from AbstractCommand
	
	// methods
	// constructors
	public MkdirCommand() {
		// none at this time
	}
	
	public MkdirCommand(FileSystem fs) {
		this.fs = fs;
	}

	// other methods
	// -- execute - execute the command
	public String execute(String [] params) {
		String outputString = "mkdir> " + params[1] + " added";
		Directory currentDir = fs.getCurrentWorkingDirectory();
		currentDir.addChild(new Directory(currentDir,params[1]));
		return outputString;
	}

}	// end - class MkdirCommand
