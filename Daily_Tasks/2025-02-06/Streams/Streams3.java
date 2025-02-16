package streams;
import java.util.*;
import java.util.stream.*;

public class Streams3 {
    public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of integers: ");
        int m = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter the integers:");
        for (int i = 0; i < m; i++) {
            numbers.add(scanner.nextInt());
        }
        int sumEven = numbers.stream()
                             .filter(n -> n % 2 == 0) 
                             .mapToInt(Integer::intValue) 
                             .sum(); 
        int sumOdd = numbers.stream()
                            .filter(n -> n % 2 != 0) 
                            .mapToInt(Integer::intValue)
                            .sum(); 
        System.out.println("Sum of even numbers: " + sumEven);
        System.out.println("Sum of odd numbers: " + sumOdd);
    }
}
