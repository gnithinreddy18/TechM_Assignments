package streams;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.*;

public class Streams4 {

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
        List<Integer> distinctNumbers = numbers.stream()
                .distinct() 
                .collect(Collectors.toList());

System.out.println("Original list: " + numbers);
System.out.println("List without duplicates: " + distinctNumbers);
	}

}
