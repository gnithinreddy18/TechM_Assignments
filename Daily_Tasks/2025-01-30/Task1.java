package Anurag;
import java.lang.*;
public class Task1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[]= {10,10,10,10,10,10,10,10,10,10};
		int arr2[]= {1,2,3,4,5,6,7,8,9,11};
		for (int i=0;i<arr1.length;i=i+2) {
			System.arraycopy(arr1,i,arr2,i,1);
		}
		for (int i=0;i<arr1.length;i++) {
			System.out.println(arr2[i]);
		}
	}

}
