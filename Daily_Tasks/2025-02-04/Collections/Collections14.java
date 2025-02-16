package Day5;

import java.util.LinkedList;
import java.util.Scanner;

public class Collections14 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the Linked list: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Elements are: ");
		for (int i=colors.size()-1;i>=0;i--) {
            System.out.println(colors.get(i));
        }
	}

}
