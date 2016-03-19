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
        int allSubFiles=0;
        for (File item : fileList){
            if(item.isFile()){
                System.out.println("0\t"+"0\t"+"f\t\t"+item);
            }
            else if(item.isDirectory()){
                subFiles= countFiles(item.getAbsolutePath());
                allSubFiles= countAllFiles(item.getAbsolutePath(),0);
                System.out.println(allSubFiles+"\t"+subFiles+"\t"+"d\t\t"+item);
                listDirectoryContents(item.getAbsolutePath());
            }
        }
    }
    public int countFiles(String directoryPath){
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        int fileCount=0;
        for (File item : fileList){
            if(item.isFile()){
                fileCount++;
            }
        }
        return fileCount;
    }
    public int countAllFiles(String directoryPath, int fileCount){
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        for (File item : fileList){
            if(item.isFile()){
                fileCount++;
            }
            else if (item.isDirectory()){
                fileCount=countAllFiles(item.getAbsolutePath(),fileCount);
            }
        }
        return fileCount;
    }
    public static void main (String args[]){
        FileManager fileManager = new FileManager();
        String directory = "C:\\Users\\Apuroop\\Videos";
        System.out.println("##\t#\tType\tPath");
        fileManager.listDirectoryContents(directory);
    }
}