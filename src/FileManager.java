/**
 * Created by Apuroop on 19-03-2016.
 */
import java.io.File;
import java.lang.String;
public class FileManager {
    public void listDirectoryContents(String directoryPath){
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        int subFiles=0;
        for (File item : fileList){
            if(item.isFile()){
                System.out.println("0\t"+"f\t\t"+item);
            }
            else if(item.isDirectory()){
                subFiles= countFiles(item.getAbsolutePath());
                System.out.println(subFiles+"\t"+"d\t\t"+item);
                listDirectoryContents(item.getAbsolutePath());
            }
        }
    }
    public int countFiles(String directoryPath){
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        int fileCounter=0;
        for (File item : fileList){
            if(item.isFile()){
                fileCounter++;
            }
        }
        return fileCounter;
    }
    public static void main (String args[]){
        FileManager fileManager = new FileManager();
        String directory = "C:\\Users\\Apuroop\\Videos";
        System.out.println("#\tType\tPath");
        fileManager.listDirectoryContents(directory);
    }
}