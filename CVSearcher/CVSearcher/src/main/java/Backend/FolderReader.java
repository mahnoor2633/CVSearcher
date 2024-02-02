package Backend;
import java.io.*;
public class FolderReader{

    public static File folder = new File(getPath("CVs")); //this represents the folder where our CVs are stored

    static File[] cvNames;
    static int numberOfCvs;
    public static int getNumberOfCVs()
    {
        cvNames = folder.listFiles();
        numberOfCvs = cvNames.length;
        return numberOfCvs;
    }
    public static File[] getCVNames()
    {
        cvNames = folder.listFiles();
        return cvNames;
    }
    public static String getPath(String folderName)
    {
        File currentDirectory = new File(System.getProperty("user.dir"));
        File parentDirectory = currentDirectory.getParentFile();

        File folder = new File(parentDirectory, folderName);

        return folder.getAbsolutePath();
    }
}
