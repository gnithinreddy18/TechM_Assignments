package p1;
import java.util.Scanner;

public class Smallest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
           String s=sc.next();
           s=s.toLowerCase();
           String vowels="";
           char temp='z';
   		for (int i=0;i<s.length();i++) {
			if ((s.charAt(i)=='a')||(s.charAt(i)=='e')||(s.charAt(i)=='i')||(s.charAt(i)=='o')||(s.charAt(i)=='u')) {
				vowels+=s.charAt(i);
			}
   		}
			for (int i=0;i<vowels.length();i++) {
				if (temp>vowels.charAt(i)) {
					temp=vowels.charAt(i);
				}
			}
		if (temp!='z') {
			System.out.println(temp);
		}
		else {
			System.out.println("No vowels");

		}
		sc.close();
	}

}
