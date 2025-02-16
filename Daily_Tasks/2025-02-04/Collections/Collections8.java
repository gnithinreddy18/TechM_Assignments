package Day5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Collections8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> colors=new ArrayList<>();
		System.out.println("Enter the length of the array: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.nextInt());
		}
		Collections.sort(colors);
		System.out.println(colors);
	}

}
