package p1;
import java.util.Scanner;
public class Vowels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s=sc.next();
		System.out.println(s);
		s=s.toLowerCase();
		int c=0;
		for (int i=0;i<s.length();i++) {
			if ((s.charAt(i)=='a')||(s.charAt(i)=='e')||(s.charAt(i)=='i')||(s.charAt(i)=='o')||(s.charAt(i)=='u')) {
				c++;
			}
		}
		System.out.println("No of vowels: "+c);
		System.out.println("No of consonants: "+(s.length()-c));
		sc.close();
	}

}
