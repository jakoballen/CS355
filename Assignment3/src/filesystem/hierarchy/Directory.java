/*
 * Directory class - represent directory type of file system object
 * 
 * Last Modified - Jakob Allen, 3/9/2020
 */
package filesystem.hierarchy;

import filesystem.utilities.Observer;
import filesystem.utilities.Subject;
import java.util.ArrayList;

public class Directory extends FileSystemObject implements Subject {
	// data
	private ArrayList<FileSystemObject> children;		//the directory's children
	static private ArrayList<Observer> observerList;	//observers to be notified



	// methods
	// constructors
	// -- default constructor
	public Directory() {
		this("default");
		this.parent = null;
		children = new ArrayList<>();
	}
	
	// -- name constructor
	public Directory(String name) {
		this.name = name;
		this.size = 0;
		parent = null;
		path = "/";
		children = new ArrayList<>();
		if(name.equals("root")) {
			observerList = new ArrayList<>();
		}
	}

	// -- parent and name constructor
	public Directory(Directory parent, String name) {
		this.name = name;
		this.size = 0;
		this.parent = parent;
		if (name.equals("root")) {
			this.path = "/";
		} else if(parent.name.equals("root")) {
			this.path = parent.path + name;
		}else{
			this.path = parent.path + "/" + name;
		}
		children = new ArrayList<>();
	}

	// other methods
	// -- getChildren() - get the children list of this directory
	public ArrayList<FileSystemObject> getChildren(){
		return children;
	}

	// -- getParent() - return the parent directory
	public Directory getParent(){
		return parent;
	}

	// -- getPath() - return the absolute path of the object
	public String getPath(){
		return path;
	}

	// -- getSize() - return a string showing the size and name
	public String getSize(){
		String outputString = "";
		outputString += size + "\t"+ name+"\n";
		for(FileSystemObject fso : children){
			outputString += fso.getSize();
		}
		return outputString;
	}

	// -- addChild(child) - add a child to this directory
	public void addChild(FileSystemObject c){
		children.add(c);
		notifyObservers();
	}

	// -- removeFile(name) - remove the file from this directory
	public String removeFile(String name){
		for(FileSystemObject fso : children){
			if(fso instanceof File &&  fso.getName().equals(name)){
				children.remove(fso);
				notifyObservers();
				return "File: "+name+" removed successfully";
			}
		}
		return "Error - file not found";
	}

	// -- removeDirectory(name) - remove the directory from this directory
	public String removeDirectory(String name){
		for(FileSystemObject fso : children){
			if(fso instanceof Directory &&  fso.getName().equals(name)){
				if(fso.hasChildren()) {
					deleteChildren((Directory) fso);
				}
				children.remove(fso);
				notifyObservers();
				return "Directory: "+name+" removed successfully";
			}
		}
		return "Error - directory not found";

	}

	// -- removeChildren(directory) - recursively delete children of the directory
	private void deleteChildren(Directory d){
		for(FileSystemObject fso : d.children){
			if(fso instanceof Directory){
				deleteChildren((Directory)fso);
			}else{
				children.remove(fso);
			}
		}
	}

	// -- removeLink(name) - remove the link from this directory
	public String removeLink(String name){
		for(FileSystemObject fso : children){
			if(fso instanceof Link  && fso.getName().equals(name)){
				children.remove(fso);
				notifyObservers();
				return "Link: "+name+" removed successfully";
			}
		}
		return "Error - link not found";

	}

	// -- getNumberFiles() - number of objects under this directory
	public int getNumberFiles(){
		return getNumberFiles(this);
	}

	// -- getNumberFiles(directory) - recursively count files of the children
	private int getNumberFiles(Directory d){
		int total = 0;
		for(FileSystemObject fso : d.children){
			total++;
			if(fso instanceof Directory){ //if child is a directory
				total += getNumberFiles((Directory)fso);
			}
		}
		return total;
	}
	
	// -- countChildren - count the number of children in the directory
	public int countChildren() {
		return children.size();
	}
	
	// -- toString - convert the directory to a string
	public String toString() {
		return "dir : " + name;
	}

	// -- getName() - get the name of the directory
	public String getName(){
		return name;
	}

	// -- hasChildren() - determine if the directory has children
	public boolean hasChildren(){
		return !children.isEmpty();
	}

		
	// -- display() - display this directory and children (possibly recursively) to a String
	public String display(int level) {
		String displayString = "";

		displayString += printHelper.genSpaces(level);
		if(name.equals("root")){
			displayString += ("/" + "\n");
		}else {
			displayString += (name + "/" + "\n");
		}
		for(FileSystemObject c : children){
			displayString += c.display(level+1);
		}
		return displayString;
	}

	// -- registerObserver(observer) - add an observer to notify
	public void registerObserver(Observer o) {
		observerList.add(o);
	}

	// -- unregisterObserver(observer) - remove an observer
	public void unregisterObserver(Observer o) {
		observerList.remove(observerList.indexOf(o));
	}

	// -- notifyObservers() - update the observer(s)
	public void notifyObservers() {
		for (Observer o : observerList) {
			o.update();
		}
	}
}	// end - class Directory
