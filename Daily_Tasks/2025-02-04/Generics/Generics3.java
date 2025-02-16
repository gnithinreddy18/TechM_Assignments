package Day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generics3 {

    public static <T extends Number> int calculateSum(List<T> numbers,int x) {

        for (int i=0;i<numbers.size();i++) {
            if (numbers.get(i).equals(x)) {
                return i;
            }
        }
        return -1;
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
        System.out.println("Enter a target number: ");
        int x=sc.nextInt();
        int temp=calculateSum(numbers,x);
        if (temp==-1) {
        	System.out.println("No such element.");
        }
        else {
        	System.out.println("Target element's index is: "+temp);
        }

        sc.close();
    }
}
