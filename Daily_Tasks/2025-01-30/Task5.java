package Anurag;
import java.util.Scanner;
import java.lang.*;
public class Task5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the word: ");
		String s1=sc.next();
		String s2="";
//		StringBuilder sb=new StringBuilder(sc.next());
		for(int i=0;i<s1.length();i++) {
			if (i%2!=0) {
			s2+=s1.charAt(i);
			}
			else {
				s2+=Character.toUpperCase(s1.charAt(i));
			}
		}
		System.out.println(s2);
	}

}
