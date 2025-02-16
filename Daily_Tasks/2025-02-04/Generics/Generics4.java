package Day5;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Generics4 {
	
    public static <T> List<T> reverseList(List<T> originalList) {
        List<T> reversedList = new ArrayList<>();
        for (int i = originalList.size() - 1; i >= 0; i--) {
            reversedList.add(originalList.get(i));
        }

        return reversedList;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Object> userList = new ArrayList<>();

        System.out.println("Enter the number of elements: ");
        int n = Integer.parseInt(sc.nextLine());

        System.out.println("Enter the elements (Mix of numbers, words, etc.):");
        for (int i = 0; i < n; i++) {
            userList.add(sc.next());
        }

        System.out.println("Original List: " + userList);
        System.out.println("Reversed List: " + reverseList(userList));

        sc.close();
    }
}
