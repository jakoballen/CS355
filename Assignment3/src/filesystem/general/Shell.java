/*
 * Shell - class to represent OS execution shell
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import filesystem.commands.*;

public class Shell {
	// - data
	private FileSystem fileSystem = new FileSystem();	// the file system class to work with
	
	// - methods
	// -- constructors
	// --- default constructor
	public Shell() {
		CommandFactory.initCommandTable(fileSystem);
		executeCommandBatchFile();
	}
	
	// other methods
	// -- executeCommandString() - execute current command, return the output
	public String executeCurrentCommandString(String commandString) {
		// local data
		Parser parser = new Parser();
		String outputString = "<default output>";
		
		String [] commandParts = parser.parseCommand(commandString); 
		AbstractCommand currentCommand = CommandFactory.getCommand(commandParts[0]);
		outputString = currentCommand.execute(commandParts);
		return outputString;
	}
	
	// -- executeCommandBatchFile() - execute set of commands in batch file
	public void executeCommandBatchFile() {
		String fileName = "";	// path/name of initialization file
		if(System.getProperty("os.name").toLowerCase().contains("mac os x")) {
			fileName ="/Users/jakoballen/Desktop/CS355/Assignments/Assignment3/filesystem.ini.txt"; //path on my mac
		}else{
			fileName = "C:\\Users\\jakob\\iCloudDrive\\Desktop\\CS355\\Assignments\\Assignment3\\filesystem.ini.txt";	// path/name of initialization file on my desktop
		}
		File inFile = new File(fileName); 					// file structure
		BufferedReader br = null;							// reader structure
		String currentCommand = null;						// current command being executed

		// -- open initialization file
		try {			
			br = new BufferedReader(new FileReader(inFile));
			// -- read each command and execute it
			try {
				while ((currentCommand = br.readLine()) != null) {
					executeCurrentCommandString(currentCommand);
				}
			} catch (IOException ioe) {
				System.out.println("problem reading from file system initialization file");
			}	
		} catch (IOException ioe) {
			System.out.println("problem opening input file / initialization file not found");
		}
	}	// end - method executeCommandBatchFile
	
	// -- getFileSystem() - return the file system from the shell
	public FileSystem getFileSystem() {
		return fileSystem;
	}
	
}	// end - class Shell
