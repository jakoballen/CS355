/*
 * RmdirCommand - command to remove a directory
 *
 * Created - Jakob Allen, 3/9/2020 - format: rmdir <name>
 */

package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;

public class RmdirCommand extends AbstractCommand {
    //constructors
    public RmdirCommand(FileSystem fs){
        this.fs = fs;
    }

    //methods
    public String execute(String[] params) {
        Directory currentDir = fs.getCurrentWorkingDirectory();
        String output = currentDir.removeDirectory(params[1]);
        return output;
    }
}   //end - class RmdirCommand
