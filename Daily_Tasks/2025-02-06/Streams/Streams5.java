package streams;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.*;

public class Streams5 {

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
        System.out.println("Enter a letter:");
        String letter=scanner.next();
        
        long count = words.stream()
                .filter(word -> word.toLowerCase().startsWith(letter.toLowerCase())) // Filter by starting letter
                .count(); // Count the matching words
        System.out.println("Number of words that start with '" + letter + "': " + count);
	}

}
