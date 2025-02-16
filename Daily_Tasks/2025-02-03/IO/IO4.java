package Example;
import java.util.Scanner;
import java.io.File;

public class IO4 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the directory path/file path of you choice: (ex: src/Example)");
        String directoryPath = sc.next();
        check(directoryPath);
    }

    public static void check(String path) {
        File file = new File(path);

        if (file.exists()) {
            if (file.canRead()) {
            	System.out.println("The file has read permission.");
            	}
            else {
            	System.out.println("The file does not have read permission.");
            	}
            if (file.canWrite()) {
            	System.out.println("The file has write permission.");
            	}
            else {
            	System.out.println("The file does not have write permission.");
            	}
            
        } else {
            System.out.println("The file or directory does not exist: " + path);
        }
    }
}
