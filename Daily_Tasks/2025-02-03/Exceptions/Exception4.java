package Example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class PositiveNumberException extends Exception {
    public PositiveNumberException(String message) {
        super(message);
    }
}

public class Exception4 {
    public static void main(String[] args) {
        try {
            checkFile("src/Example/Listofnumbers.txt");
            System.out.println("No positive numbers found.");
        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (PositiveNumberException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public static void checkFile(String fileName) throws FileNotFoundException, PositiveNumberException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (num > 0) {
                scanner.close();
                throw new PositiveNumberException("Positive number detected: " + num);
            }
        }
        scanner.close();
    }
}

