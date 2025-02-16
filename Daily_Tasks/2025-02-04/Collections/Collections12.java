package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections12 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked List: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		for (int x:colors) {
            System.out.println(x);
        }
	}

}
