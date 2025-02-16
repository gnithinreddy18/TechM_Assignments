package p1;
import java.util.Scanner;
public class Lastword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		String s="";
		for(int i=0;i<str.length();i++) {
			if((str.charAt(i)==' ') && (i>0) && (Character.isLetter(str.charAt(i-1)))) {
				s=s+str.charAt(i-1);
			}
		}
			if(Character.isLetter(str.charAt(str.length()-1))) {
				s=s+str.charAt(str.length()-1);
				
			}
		System.out.println(s);
	}

}
