/*
 * File class - represent file type of file system object
 *
 * Last Modified - Jakob Allen, 3/9/2020
 */
package filesystem.hierarchy;

public class Link extends FileSystemObject {
    private String existingLocation;

    // -- all-arg constructor
    public Link(Directory parent, String name, String existingLocation) {
        this.parent = parent;
        this.name = name;
        this.size = 0;
        this.path = parent.getPath() + "/" + name;
        this.existingLocation = existingLocation;
    }

    //other methods
    // -- hasChildren() - determine if the directory has children
    boolean hasChildren() {
        return false;
    }

    // -- getName() - get the name of the directory
    public String getName() {
        return name;
    }

    // -- getSize() - return a string showing the size and name
    public String getSize() {
        return size +"\t" + name +"* ->"+existingLocation+"\n";
    }

    // -- getExistingLocation() - return the name of the actual object
    public String getExistingLocation(){
        return existingLocation;
    }

    // -- toString - convert the directory to a string
    public String toString() {
        return "link : " + name;
    }

    // -- getPath() - return the absolute path of the object
    public String getPath(){
        return path;
    }

    // -- getParent() - return the parent directory
    public Directory getParent(){
        return parent;
    }

    // -- display() - display the file information to a string
    public String display(int level) {
        String displayString = "";

        displayString += printHelper.genSpaces(level);
        displayString += (name + "* -> "+existingLocation+"\n");

        return displayString;
    }
}   //end - class Link
