/*
 * DuCommand - command to show the total recursize size of all file system objects
 *
 * Created - Jakob Allen, 3/9/2020 - format: du
 */
package filesystem.commands;
import filesystem.general.FileSystem;

public class DuCommand extends AbstractCommand{
    //constructors
    public DuCommand(FileSystem fs){
        this.fs = fs;
    }

    //other methods
    public String execute(String[] params) {
        return fs.getSize();
    }
}   //end - class DuCommand
