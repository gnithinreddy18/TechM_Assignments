package Example;
import java.io.File;
import java.util.Scanner;

public class IO8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the file path: ");
		   String filePath = sc.next(); 
	        
	        File file = new File(filePath);
	        
	        if (file.exists()) {
	            
	            long fileSizeInBytes = file.length();
	            
	            
	            double fileSizeInKB = fileSizeInBytes / 1024.0;
	            double fileSizeInMB = fileSizeInKB / 1024.0;
	            
	            
	            System.out.println("File size in bytes: " + fileSizeInBytes);
	            System.out.println("File size in KB: " + fileSizeInKB);
	            System.out.println("File size in MB: " + fileSizeInMB);
	        } else {
	            System.out.println("The file does not exist.");
	        }
	        sc.close();

	}

}
