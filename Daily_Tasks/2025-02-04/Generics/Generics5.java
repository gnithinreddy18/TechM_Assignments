package Day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generics5 {

    public static <T> List<T> mergeAlternately(List<T> list1, List<T> list2) {
        List<T> mergedList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() || j < list2.size()) {
            if (i < list1.size()) mergedList.add(list1.get(i++));
            if (j < list2.size()) mergedList.add(list2.get(j++));
        }
        return mergedList;
    }

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in first list:");
        int n1 = sc.nextInt();
        List<String> list1 = new ArrayList<>();
        System.out.println("Enter " + n1 + " elements:");
        for (int i = 0; i < n1; i++) {
            list1.add(sc.next());
        }

        System.out.println("Enter the number of elements in second list:");
        int n2 = sc.nextInt();
        sc.nextLine(); 
        List<String> list2 = new ArrayList<>();
        System.out.println("Enter " + n2 + " elements:");
        for (int i = 0; i < n2; i++) {
            list2.add(sc.next());
        }

        List<String> mergedList = mergeAlternately(list1, list2);
        System.out.println("Merged List: " + mergedList);

    }
}
