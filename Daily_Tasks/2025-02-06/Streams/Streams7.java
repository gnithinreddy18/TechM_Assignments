package streams;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Streams7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of integers: ");
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter the integers:");
        for (int i = 0; i < n; i++) {
            numbers.add(scanner.nextInt());
        }
        int max = numbers.stream()
                .mapToInt(Integer::intValue) 
                .max()                       
                .getAsInt();                 

        int min = numbers.stream()
                .mapToInt(Integer::intValue) 
                .min()                       
                .getAsInt(); 
        
        System.out.println("Maximum value: " + max);
        System.out.println("Minimum value: " + min);
	}
}
