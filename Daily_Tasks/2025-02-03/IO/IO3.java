package Example;
import java.util.Scanner;
import java.io.File;

public class IO3 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the directory path/file path of you choice: (ex: src/Example)");
        String directoryPath = sc.next();
        check(directoryPath);
    }

    public static void check(String path) {
        File file = new File(path);

        if (file.exists()) {
        	System.out.println("Given path exists");
        } else {
            System.out.println("The file or directory does not exist: " + path);
        }
    }
}
