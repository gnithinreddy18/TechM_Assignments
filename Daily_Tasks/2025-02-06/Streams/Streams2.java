package streams;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Streams2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        
        System.out.println("Enter words (type 'exit' to stop):");
        while (true) {
            String input = scanner.next();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            words.add(input);
        }
        
        System.out.println("Choose conversion: 1 for Uppercase, 2 for Lowercase");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            List<String> convertedWords = words.stream()
                                               .map(String::toUpperCase)
                                               .collect(Collectors.toList());
            System.out.println("Converted to Uppercase: " + convertedWords);
        } else if (choice == 2) {
            List<String> convertedWords = words.stream()
                                               .map(String::toLowerCase)
                                               .collect(Collectors.toList());
            System.out.println("Converted to Lowercase: " + convertedWords);
        } else {
            System.out.println("Invalid choice. No conversion applied.");
        }
        
        scanner.close();
    }
}
