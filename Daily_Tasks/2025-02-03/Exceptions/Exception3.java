package Example;
import java.io.File;
import java.io.FileNotFoundException;
public class Exception3 {

    public static void readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if(file.exists()) {
        	System.out.println("It exists.");
        }
        else {
        	throw new FileNotFoundException("No file found");
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			readFile("src/Example/text1.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
