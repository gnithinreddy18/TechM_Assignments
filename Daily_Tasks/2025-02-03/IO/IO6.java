package Example;
import java.io.*;
import java.util.Scanner;
import java.util.Date;

public class IO6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the directory path/file path of you choice: (ex: src/Example)");
        String directoryPath = sc.next();
        getLastModifiedDate(directoryPath);

	}
	
    public static void getLastModifiedDate(String filePath) {
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            long lastModified = file.lastModified();
            System.out.println(lastModified);
            System.out.println("hiii");
            Date date = new Date(lastModified);
            System.out.println("The last modified date is: "+date);
    }

}
}
