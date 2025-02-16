package Day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generics2 {

    public static <T extends Number> void calculateSum(List<T> numbers) {
        int evenSum = 0;
        int oddSum = 0;

        for (T num : numbers) {
            if (num.intValue() % 2 == 0) {
                evenSum += num.intValue();
            } else {
                oddSum += num.intValue(); 
            }
        }
        System.out.println("Sum of even numbers: " + evenSum);
        System.out.println("Sum of odd numbers: " + oddSum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter the number of elements in the list: ");
        int n = sc.nextInt();
        System.out.println("Enter the values of the list: ");
        for (int i = 0; i < n; i++) {
            numbers.add(sc.nextInt());
        }
        calculateSum(numbers);

        sc.close();
    }
}
