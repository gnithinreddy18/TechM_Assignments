package p1;
import java.util.Scanner;
public class Uniquechar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the word: ");
		String s=sc.next();
		int[] alp=new int[26];
		for (int i=0;i<alp.length;i++) {
			alp[i]=0;
		}
		
		for (int i=0;i<s.length();i++) {
			alp[((int)(s.charAt(i)))-97]+=1;
		}
		for (int i=0;i<alp.length;i++) {
			if (alp[i]>=1) {
			System.out.print((char)(97+i));}
		}
	}

}
