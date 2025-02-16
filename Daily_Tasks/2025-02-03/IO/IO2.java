package Example;

import java.io.File;

public class IO2 {
    public static void main(String[] args) {
        String directoryPath = "src/Example";
        String extension=".txt";
        listFiles(directoryPath,extension);
    }

    public static void listFiles(String directoryPath,String Extension) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] fileList = directory.listFiles();

            if (fileList != null && fileList.length > 0) {
                System.out.println("Files and Directories in: " + directoryPath);
                for (File file : fileList) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(Extension.toLowerCase())) {
                        System.out.println("[File] " + file.getName());
                    } 
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("Invalid directory path or directory does not exist.");
        }
    }
}
