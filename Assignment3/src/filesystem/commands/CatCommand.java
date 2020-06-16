package filesystem.commands;
import filesystem.general.FileSystem;
import filesystem.hierarchy.*;
import java.util.ArrayList;

public class CatCommand extends AbstractCommand {
    //constructors
    public CatCommand(){

    }

    public CatCommand(FileSystem fs){
        this.fs = fs;
    }

    //methods
    public String execute(String[] params){
        ArrayList<FileSystemObject> children = fs.getCurrentWorkingDirectory().getChildren();
        File f = null;
        String outputString = "";
        for(FileSystemObject fso : children){
            if(fso instanceof File && fso.getName().equals(params[1])){
                outputString =  ((File) fso).getContents();
            }else if(fso instanceof Link && fso.getName().equals(params[1])){
                String param[] = {null, ((Link)fso).getExistingLocation()};
                outputString = execute(param);
            }
        }

        return outputString;
    }
}   // end - class CatCommand
