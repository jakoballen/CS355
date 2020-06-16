/*
 * RmlinkCommand - command to remove a link
 *
 * Created - Jakob Allen, 3/9/2020 - format: rmlink <name>
 */

package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;

public class RmlinkCommand extends AbstractCommand {
    //constructors
    public RmlinkCommand(FileSystem fs){
        this.fs = fs;
    }

    //methods
    public String execute(String[] params){
        Directory currentDir = fs.getCurrentWorkingDirectory();
        String output = currentDir.removeLink(params[1]);
        return output;
    }
}   // end -class RmlinkCommand
