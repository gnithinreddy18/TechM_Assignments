package Example;
import java.util.Scanner;
import java.io.File;

public class IO5 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the directory path/file path of you choice: (ex: src/Example)");
        String directoryPath = sc.next();
        FileDirectory(directoryPath);
    }

    public static void FileDirectory(String path) {
        File file = new File(path);

        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("The path points to a file: " + path);
            } else if (file.isDirectory()) {
                System.out.println("The path points to a directory: " + path);
            }
        } else {
            System.out.println("The file or directory does not exist: " + path);
        }
    }
}
