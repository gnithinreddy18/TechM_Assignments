package Anurag;
import java.util.Scanner;
import java.util.Arrays;
public class Task2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the no of students: ");
		int nostu=sc.nextInt();
		System.out.println("Enter the no of subjects : ");
		int nosub=sc.nextInt();
		String[] names= new String[nostu];
		for (int i=0;i<nostu;i++) {
			System.out.println("Enter names of student"+(i+1)+": ");
			names[i]=sc.next();
		}
		for (int i=0;i<nostu;i++) {
			System.out.println("Name of the student"+(i+1)+": "+names[i]);
		}
		int[][] marks = new int[nostu][nosub];
		int[] ranks = new int[nostu];
		int sum=0;
		for(int i=0;i<nostu;i++) {
			System.out.println("Enter marks for "+names[i]+": ");
			for (int j=0;j<nosub;j++) {
				System.out.println("Enter marks for the subject "+j);
				marks[i][j]=sc.nextInt();
				sum+=marks[i][j];
			}
			ranks[i]=sum/nosub;
			sum=0;
		}
		for (int j=0;j<nostu;j++) {
			System.out.println(names[j]+" average is: "+ranks[j]);
		}
	}

}
