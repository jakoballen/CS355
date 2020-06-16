/* 
 * FileSystemObject class - abstract composite class for each general file system object
 * 
 * Last Modified - Jakob Allen, 3/9/2020
 */
package filesystem.hierarchy;

import filesystem.utilities.PrintHelper;

public abstract class FileSystemObject {
	// data
	protected String name;			// file system object name
	protected int    size;			// file system object size
	protected PrintHelper printHelper = new PrintHelper();	// print helper for file system display
	protected String path;			//absolute path of the object
	protected Directory parent;		//parent directory of the current object
	
	// methods
	// constructors
	// -- default constructor
	public FileSystemObject() {
		this("default", 0);
	}

	// -- all-arg constructor
	public FileSystemObject(String name, int size) {
		this.name = name;
		this.size = size;
	}

	// -- hasChildren() - determine if the object has children
	abstract boolean hasChildren();

	// -- getName() - get the name of the directory
	abstract public String getName();

	// -- getSize() - return a string showing the size and name
	abstract public String getSize();

	// -- getPath() - return the absolute path of the object
	abstract public String getPath();

	// -- getParent() - return the parent directory
	abstract public Directory getParent();

	// other methods	
	// -- display() - generate display string from file system traversal
	public abstract String display(int level);
	
}	// end - class FileSystemObject
