package Example;

import java.io.File;

public class IO1 {
    public static void main(String[] args) {
        String directoryPath = "src/Example";
        listFiles(directoryPath);
    }

    public static void listFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] fileList = directory.listFiles();

            if (fileList != null && fileList.length > 0) {
                System.out.println("Files and Directories in: " + directoryPath);
                for (File file : fileList) {
                    if (file.isFile()) {
                        System.out.println("[File] " + file.getName());
                    } else if (file.isDirectory()) {
                        System.out.println("[Directory] " + file.getName());
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
