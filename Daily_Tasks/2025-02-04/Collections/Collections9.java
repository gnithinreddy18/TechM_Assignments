package Day5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Collections9 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> colors=new ArrayList<>();
		System.out.println("Enter the length of the array: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		
		System.out.println("Original List: "+colors);
		ArrayList<Integer> copy=new ArrayList<>(colors);
		System.out.println("Copied List: "+copy);

	}

}
