/*
 * FileSystem class - overall class for holding the entire file system
 * 
 * Last Modified - Paul J. Wagner, 3/5/2020
 */
package filesystem.general;

import filesystem.commands.ErrorCommand;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.FileSystemObject;
import filesystem.hierarchy.Link;

import java.io.File;
import java.util.ArrayList;

public class FileSystem {
	// data
	private Directory root;                            // root of the file system
	private String currentWorkingDirectoryString;    // current working directory as String in the file system
	private Directory currentWorkingDirectory;        // current working directory object

	// methods
	// constructors
	// -- default constructor
	public FileSystem() {
		root = new Directory("root");
		currentWorkingDirectoryString = "/";
		currentWorkingDirectory = root;
	}

	// other methods
	// -- toString() - overall top-level file system display
	public String toString() {
		String outputString = "";
		outputString += "fs: cwd is: " + currentWorkingDirectoryString;
		return outputString;
	}

	// -- genDisplay - generate display of file system (gathers each file system display recursively)
	public String genDisplay() {
		String displayString = "";
		displayString = root.display(0);
		return displayString;
	}

	public String getSize(){
		return currentWorkingDirectory.getSize();
	}

	// -- getCurrentWorkingDirectoryString - return the current working directory as a string
	public String getCurrentWorkingDirectoryString() {
		return currentWorkingDirectoryString;
	}

	// -- getCurrentWorkingDirectory - return the current working directory as a Directory object
	public Directory getCurrentWorkingDirectory() {
		return currentWorkingDirectory;
	}

	// -- setCurrentWorkingDirectory - set the current working directory and string to the new directory
	public String setCurrentWorkingDirectory(String newDirString) {
		if (newDirString.charAt(0) == '.' && newDirString.charAt(1) == '.') {        // .. - change to parent
			// TODO: change to parent (if exists/not at root)
			if (!currentWorkingDirectory.equals(root)) {
				currentWorkingDirectory = currentWorkingDirectory.getParent();
				currentWorkingDirectoryString = currentWorkingDirectory.getPath();
				return "cd> cwd changed to "+currentWorkingDirectoryString;
			}else{
				return "Error - already at root";
			}
		} else if (newDirString.charAt(0) == '~') {        // ~ - change to root
			// TODO: change to root
			currentWorkingDirectory = root;
			currentWorkingDirectoryString = "/";
			return "cd> cwd changed to root";
		} else {                                        // change to specified subdirectory if it exists
			// TODO: find child object with this name
			ArrayList<FileSystemObject> children = currentWorkingDirectory.getChildren();
			FileSystemObject child = null;
			Boolean childExists = false;
			for (FileSystemObject fso : children) {
				if (fso instanceof Directory && fso.getName().equals(newDirString)) {
					childExists = true;
					child = fso;
				}else if(fso instanceof Link && fso.getName().equals(newDirString)){
					return setCurrentWorkingDirectory(((Link) fso).getExistingLocation());
				}
			}

			if (childExists) { // if child exists, set current object and string
				currentWorkingDirectory = (Directory) child; //   set current working directory object
				currentWorkingDirectoryString = ((Directory) child).getPath(); //   set current working directory string
				return "cd> cwd changed to "+currentWorkingDirectoryString;
			} else { // else if child doesn't exist
				return "Error - specified directory does not exist";// error
			}
		}
	}

	// -- getRoot - return the root directory of the file system
	public Directory getRoot() {
		return root;
	}

}	// end - class FileSystem
