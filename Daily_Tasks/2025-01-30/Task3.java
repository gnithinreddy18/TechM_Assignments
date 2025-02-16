package Anurag;
import java.util.Arrays;

public class Task3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[]= {1,2,3,4};
		int arr2[]= {1,2,3,4};
		if (Arrays.equals(arr1, arr2)){
			System.out.println("Strings are equal by equals()");
		}
		else {
			System.out.println("Strings are not equal by equals()");
		}
		Object arr3[]= {arr1};
		Object arr4[]= {arr2};
		if (Arrays.deepEquals(arr3, arr4)){
			System.out.println("Strings are equal by deepequals()");
		}
		else {
			System.out.println("Strings are not equal by deepequals()");
		}
	}

}
