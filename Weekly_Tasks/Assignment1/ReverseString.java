package p1;
import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a word: ");
		String s=sc.next();
		s=s.toLowerCase();
		for(int i=s.length()-1;i>=0;i--) {
			System.out.print(s.charAt(i));
		}
		
		
		sc.close();
	}

}
