/*
 * File class - represent file type of file system object
 * 
 * Last Modified - Jakob Allen, 3/9/2020
 */
package filesystem.hierarchy;


public class File extends FileSystemObject {
	// data
	private String contents;		// simulated contents of file as string
	
	// methods
	// constructors
	// -- default constructor
	public File() {
		this("default", 0, "none");
	}
	
	// -- all-arg constructor
	public File(String name, int size, String contents) {
		this.name = name;
		this.size = size;
		this.contents = contents;

	}
	public File(Directory parent, String name, int size, String contents) {
		this.name = name;
		this.size = size;
		this.contents = contents;
		this.parent = parent;
		if(parent.name.equals("root")){
			this.path = parent.path + name;
		}else {
			this.path = parent.path + "/" + name;
		}
	}

	// other methods
	// -- toString - convert file to a string
	public String toString() {
		return "File: " + name + " (" + size + ")";
	}

	// -- hasChildren() - determine if the object has children
	public boolean hasChildren(){
		return false;
	}

	// -- getName() - get the name of the directory
	public String getName(){
		return name;
	}

	//-- getContents() - get the contents of the file
	public String getContents(){
		return contents;
	}

	// -- getSize() - return a string showing the size and name
	public String getSize(){
		return size +"\t" + name +"\n";
	}

	// -- getParent() - return the parent directory
	public Directory getParent(){
		return parent;
	}

	// -- getPath() - return the absolute path of the object
	public String getPath(){
		return path;
	}

	// -- display() - display the file information to a string
	public String display(int level) {
		String displayString = "";
		
		displayString += printHelper.genSpaces(level);
		displayString += (name + "\n");
		
		return displayString;	
	}

}	// end - class File
