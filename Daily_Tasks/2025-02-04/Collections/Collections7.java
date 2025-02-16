package Day5;
import java.util.ArrayList;
import java.util.Scanner;

public class Collections7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> colors=new ArrayList<>();
		System.out.println("Enter the length of the array: ");
		int n=sc.nextInt();
		System.out.println("Enter the elements: ");
		for (int i=0;i<n;i++) {
			colors.add(sc.next());
		}
		System.out.println("Enter an element: ");
		String new1=sc.next();
		for(String s:colors) {
			if (s.equals(new1)) {
				System.out.println("The element "+new1+" is present in the array.");
				break;
			}
		}
	}

}
