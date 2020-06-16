/*
 * RmlinkCommand - command to remove a file
 *
 * Created - Jakob Allen, 3/9/2020 - format: rm <name>
 */

package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;

public class RmCommand extends AbstractCommand {
    //constructors
    public RmCommand(FileSystem fs){
        this.fs = fs;
    }

    //methods
    public String execute(String[] params) {
        Directory currentDir = fs.getCurrentWorkingDirectory();
        String output = currentDir.removeFile(params[1]);
        return output;
    }
}   //end - class RmCommand
