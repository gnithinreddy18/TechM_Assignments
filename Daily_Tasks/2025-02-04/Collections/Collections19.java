package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections19 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked List: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Enter the number of elements to insert: ");
        int m = sc.nextInt();
		System.out.println("Enter an index: ");
		int index=sc.nextInt();
		LinkedList<Integer> newElements = new LinkedList<>();
        System.out.println("Enter the elements to insert: ");
        for (int i = 0; i < m; i++) {
            newElements.add(sc.nextInt());
        }
        colors.addAll(index,newElements);
		System.out.println("After adding: ");
		System.out.println(colors);
	}

}
