package Anurag;
import java.util.Arrays;
public class Task4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[]= {1,2,3,4,5,6,7,8,9,10};
		int arr2[]=Arrays.copyOfRange(arr1, 0, 7);
		for(int i=0;i<arr2.length;i++) {
			System.out.println(arr2[i]);
		}
	}

}
