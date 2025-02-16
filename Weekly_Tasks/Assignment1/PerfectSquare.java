package p1;
import java.util.Scanner;
public class PerfectSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int n=sc.nextInt();
		int sq=(int)Math.sqrt(n);
		if(sq*sq==n) {
			System.out.println("True");
		}
		else {
			System.out.println("False");
		}
	}

}
