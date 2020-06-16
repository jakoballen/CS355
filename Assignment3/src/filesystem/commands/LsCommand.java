/*
 * LsCommand - command class for ls (list current directory)
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.FileSystemObject;

public class LsCommand extends AbstractCommand {
	// data
	// -- brings down fs from AbstractCommand
	
	// methods
	// constructors
	public LsCommand() {
		// none at this time
	}
	
	public LsCommand(FileSystem fs) {
		this.fs = fs;
	}

	// other methods
	public String execute(String [] params) {
		String outputString = "";
		Directory currentDir = fs.getCurrentWorkingDirectory();
		
		outputString += "ls> ";
		outputString += currentDir.toString()+"\n";						// show directory
		for (FileSystemObject fso : currentDir.getChildren()) {  	// then show children
			outputString += fso.toString()+"\n";
		}
		return outputString;
	}
}	// end - class LsCommand
