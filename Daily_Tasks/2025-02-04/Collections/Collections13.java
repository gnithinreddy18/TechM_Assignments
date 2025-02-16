package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections13 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked list: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Enter the index: ");
		int index=sc.nextInt();
		System.out.println("Elements are: ");
		for (int i=index;i<colors.size();i++) {
            System.out.print(colors.get(i)+" ");
        }
	}

}
