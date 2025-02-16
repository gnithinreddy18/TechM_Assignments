package Example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}

public class Exception5 {
    public static void main(String[] args) {
        try {
            checkFile("src/Example/Text1.txt");
            System.out.println("Data is present.");
        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (EmptyFileException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public static void checkFile(String fileName) throws FileNotFoundException, EmptyFileException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        Scanner scanner = new Scanner(file);
        if(!scanner.hasNext()) {
        	scanner.close();
            throw new EmptyFileException("The file is empty");
        }

    }
}

