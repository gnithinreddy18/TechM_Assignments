package Example;
import java.util.Scanner;
public class Exception2 {
	
	public static void checkOdd(int n) throws IllegalArgumentException{
		if (n%2==0) {
			System.out.println("Number is even");
		}
		else {
			throw new IllegalArgumentException("The provided number must be even.");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Enter a number: " );
			int c=sc.nextInt();
			checkOdd(c);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Program has ended.");
			sc.close();
		}
	}

}
