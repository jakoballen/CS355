/*
 * MklinkCommand - command to create a link
 *
 * Created - Jakob Allen, 3/9/2020 - format: mklink <name> <actual-location>
 */

package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.FileSystemObject;
import filesystem.hierarchy.Link;

public class MklinkCommand extends AbstractCommand{

    // constructors
    public MklinkCommand(FileSystem fs) {
        this.fs = fs;
    }

    //methods
    public String execute(String [] params) {
        String outputString = "mklink> " + params[1] + " added";
        Directory currentDir = fs.getCurrentWorkingDirectory();
        for (FileSystemObject fso: currentDir.getChildren()) {
            if(fso.getName().equals(params[2])){
                currentDir.addChild(new Link(currentDir,params[1], params[2]));
                return outputString;
            }
        }
       return "Error - file must exist in same directory";

    }
}   //end - class MklinkCommand
