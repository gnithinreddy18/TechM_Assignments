package Day5;

import java.util.ArrayList;
import java.util.Scanner;

public class Collections5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> colors=new ArrayList<>();
		System.out.println("Enter the length of the array: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.next());
		}
		System.out.println("Enter old element to be updated: ");
		String index=sc.next();
		System.out.println("Enter new element: ");
		String new1=sc.next();
		for(int i=0;i<n;i++) {
			if (colors.get(i).equals(index)) {
				colors.set(i, new1);
				break;
			}
		}
		System.out.println("Updated array: "+colors);
	}

}
