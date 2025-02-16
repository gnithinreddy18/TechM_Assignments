package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections18 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked List: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Enter a new element to add at ending: ");
		int first =sc.nextInt();
		colors.add(colors.size(), first);

		System.out.println("After adding: ");
		System.out.println(colors);
	}

}
