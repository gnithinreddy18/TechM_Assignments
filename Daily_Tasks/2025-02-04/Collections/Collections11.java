package Day5;
import java.util.LinkedList;
import java.util.Scanner;

public class Collections11 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colors=new LinkedList<>();
		System.out.println("Enter the length of the linked List: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		System.out.println("Enter the new element: ");
		int ele=sc.nextInt();
		colors.addLast(ele);
		System.out.println("After adding at last: ");
		System.out.println(colors);
	}

}
