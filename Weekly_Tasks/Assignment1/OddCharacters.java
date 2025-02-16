package p1;
import java.util.Scanner;
public class OddCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s=sc.nextLine();
		for(int i=1;i<s.length();i=i+2) {
			System.out.print(s.charAt(i));
		}
	}

}
