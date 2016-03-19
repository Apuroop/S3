/**
 * Created by Apuroop on 19-03-2016.
 */
import java.io.File;
import java.lang.String;
public class FileManager {
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
    public int[] fileTypeCounter(String directoryPath, int[] typeCount){
        String[] extTypes = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml","txt"};
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        String extnCheck="";
        for (File item : fileList){
            if(item.isFile()){
                extnCheck= truncateExtension(item.getAbsolutePath());
                for(int i=0; i<extTypes.length; i++){
                    if(extnCheck.equalsIgnoreCase(extTypes[i])){
                        typeCount[i]++;
                    }
                }
            }
            else if(item.isDirectory()){
                typeCount=fileTypeCounter(item.getAbsolutePath(), typeCount);
            }
        }
        //System.out.println("NUMBER OF FILES BY TYPE");
        //for (int j=0; j<extTypes.length; j++){
        //    System.out.println(extTypes[j]+"\t"+typeCount[j]);
        return typeCount;
    }
    public static String truncateExtension(String filePath){
        String extn = "";
        int i= filePath.lastIndexOf('.');
        if(i>=0){
            extn=filePath.substring(i+1);
        }
        return extn;
    }
    public static void main (String args[]) {
        FileManager fileManager = new FileManager();
        int[] typeCount = new int[100];
        String[] extTypes = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml", "txt"};
        String directory = "C:\\Users\\Apuroop\\Videos";
        System.out.println("##\t#\tType\tPath");
        fileManager.listDirectoryContents(directory);
        typeCount = fileManager.fileTypeCounter(directory, typeCount);
        System.out.println("NUMBER OF FILES BY TYPE");
        for (int j = 0; j < extTypes.length; j++) {
            System.out.println(extTypes[j] + "\t" + typeCount[j]);
        }
    }
}