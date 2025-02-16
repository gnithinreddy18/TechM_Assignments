package streams;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class Streams6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
        System.out.println("Ascending order:");
        words.stream()
             .sorted()
             .forEach(word -> System.out.print(word + " ")); 

        System.out.println("\nDescending order:");
        words.stream()
             .sorted(Comparator.reverseOrder()) 
             .forEach(word -> System.out.print(word + " "));
	}

}
