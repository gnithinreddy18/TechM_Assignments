package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections16 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked List: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Enter a new element to add at beginning: ");
		int first =sc.nextInt();
		System.out.println("Enter a new element to add at ending: ");
		int last =sc.nextInt();
		colors.add(0, first);
		colors.add(colors.size(), last);

		System.out.println("After adding: ");
		System.out.println(colors);
	}

}
