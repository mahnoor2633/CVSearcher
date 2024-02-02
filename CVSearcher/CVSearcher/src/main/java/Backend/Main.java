package Backend;

import GUI.HomeFrameGUI;
import java.io.File;

public class Main{
    public static void main(String[] args)
    {
        CheckFolder();
        new HomeFrameGUI();
    }

    public static void CheckFolder() //this method runs each time the program is ran. if the cv folder doesnt already exist on the users pc, it makes it for them
    {
        File check_folder = new File(FolderReader.getPath("CVs"));

        if(!check_folder.exists() && !check_folder.isDirectory())
        {
            File currentDirectory = new File(System.getProperty("user.dir")); //getting the user's current directory
            File parentDirectory = currentDirectory.getParentFile();
            String parentPath = parentDirectory.getAbsolutePath();

            String fileName = "CVs";

            File CVFolder = new File(parentPath, fileName);

            CVFolder.mkdir(); //creating the cvs folder
        }
    }
}