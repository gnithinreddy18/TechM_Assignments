package p1;
import java.util.Scanner;

public class SpaceCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a sentence: ");
		String s=sc.nextLine();
		System.out.println(s);
		int c=0;
		int c1=0;
		for(int i=0;i<s.length();i++) {
			if (Character.isLetter(s.charAt(i))) {
				c++;
			}
			else if (s.charAt(i)==' ') {
				c1++;
			}
		}
		System.out.println("Spcae count: "+c1);
		System.out.println("Characters count: "+c);

	}

}
