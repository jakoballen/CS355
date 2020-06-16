/*
 * MkfileCommand - command to make file in current directory
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.File;

public class MkfileCommand extends AbstractCommand {
	// data
	// -- brings fs down from AbstractCommand
	
	// methods
	// constructors
	public MkfileCommand() {
		// none at this time
	}
	
	public MkfileCommand(FileSystem fs) {
		this.fs = fs;
	}

	// other methods
	// -- execute - execute the command
	public String execute(String [] params) {
		String outputString = "mkfile> " + params[1] + " added";
		Directory currentDir = fs.getCurrentWorkingDirectory();
		currentDir.addChild(new File(currentDir, params[1], Integer.parseInt(params[2]), params[3]));
		return outputString;
	}

}	// end - class MkfileCommand
