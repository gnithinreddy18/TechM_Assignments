package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections20 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked List: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Enter an element to find first and last indices: ");
		int first =sc.nextInt();
		System.out.println(colors);
		System.out.println("first position: "+colors.indexOf(first)+", last position is: "+colors.lastIndexOf(first));
	}

}
