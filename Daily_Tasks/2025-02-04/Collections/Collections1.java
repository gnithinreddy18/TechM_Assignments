package Day5;

import java.util.Scanner;
import java.util.ArrayList;

public class Collections1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> colors=new ArrayList<>();
		System.out.println("Enter the length of the array: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.next());
		}
		System.out.println(colors);
	}

}
