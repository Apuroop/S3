/**
 * Created by Apuroop on 19-03-2016.
 */
import java.io.File;
import java.lang.String;
public class FileManager {
    //This method takes in the Starting directory path (directoryPath) as it's input. It does not return any value.
    //This method recursively prints the total number of files and folders by calling other methods defined
    public static void listDirectoryContents(String directoryPath){
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
    //This method takes in the Directory path as a string which is sent by listDirectoryContents.
    // It returns the number of  files present in a particular folder. It does not check sub-folders
    public static int countFiles(String directoryPath){
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
    //This method accepts 2 parameters, the directory path as a string and fileCount which stores the number of files
    //that the method has counted previously. fileCount is initially zero, and gets updated every time countAllFiles
    //is recursively called. The return value is the total number of files present within a folder. This does check
    //all subfolders present in a particular file.
    public static int countAllFiles(String directoryPath, int fileCount){
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
    //This method accepts a string and a 2D long as the parameters. the string provides the directoryPath of the file currently
    //being checked. The 2D long stores 2 values. the first row is devoted to keeping track of number of files of each kind that are
    //present. the second row is devoted to keeping track of the total size of files of each type. this method returns a 2D long
    //array which contains all the updated stats for each file.
    public static long [][] fileTypeCounter(String directoryPath, long[][] typeCount){
        String[] extTypes = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml","txt"};
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        long tempSize=0;
        String extnCheck="";
        for (File item : fileList){
            if(item.isFile()){
                tempSize= fileSize(item.getAbsolutePath());
                extnCheck= truncateExtension(item.getAbsolutePath());
                for(int i=0; i<extTypes.length; i++){
                    if(extnCheck.equalsIgnoreCase(extTypes[i])){
                        typeCount[0][i]++;
                        typeCount[1][i]=typeCount[1][i]+tempSize;
                    }
                }
            }
            else if(item.isDirectory()){
                typeCount=fileTypeCounter(item.getAbsolutePath(), typeCount);
            }
        }
        return typeCount;
    }
    //this method takes in a string as its parameter, which contains the absolute path of the file that is being sent.
    //the return type of this is a string which contains only the extension of the file. No folders can be sent to this method.
    public static String truncateExtension(String filePath){
        String extn = "";
        int i= filePath.lastIndexOf('.');
        if(i>=0){
            extn=filePath.substring(i+1);
        }
        return extn;
    }
    //this method takes in a string as its parameter which contains the absolute path of a file.
    //it returns the size of the file as a long value.
    public static long fileSize(String filePath){
        return filePath.length();
    }
    public static void main (String args[]) {
        long t0= System.currentTimeMillis();
        FileManager fileManager = new FileManager();
        long [][] typeCount = new long [2][20];
        String[] extTypes = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml", "txt"};
        String directory = "C:\\Users\\Apuroop\\Videos";
        System.out.println("##\t#\tType\tPath");
        fileManager.listDirectoryContents(directory);
        typeCount = fileManager.fileTypeCounter(directory, typeCount);
        System.out.println("NUMBER OF FILES BY TYPE");
        for (int j = 0; j < extTypes.length; j++) {
            System.out.println(extTypes[j] + "\t\t" + typeCount[0][j]+"\t\t"+typeCount[1][j]);
        }
        long t1= System.currentTimeMillis();
        System.out.println("TOTAL TIME TAKEN = "+(t1-t0));
    }
}
