package Anurag;
import java.util.Scanner;
public class Employee {

		Scanner sc = new Scanner(System.in);
		int empId;
		String empName;
		double sal;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Employee e=new Employee();
			e.setEmployeeDetails();
			e.getEmployeeDetails();
			if(e.getLoanEligibility()) {
				if (e.sal==6) {
					System.out.println("2 lakhs of loan is granted.");
				}
				else if(e.sal>=10) {
					System.out.println("5 lakhs of loan is granted.");
				}
				else if(e.sal>=15) {
					System.out.println("7 lakhs of loan is granted.");
				}
				else {
					System.out.println("You are not worhty enough");
				}
			}
			else {
				System.out.println("You are not worhty enough!!!!!!");
			}
	}
		public void getEmployeeDetails() {
			System.out.println("The id of employee is: "+empId);
			System.out.println("The name of employee is: "+empName);
			System.out.println("The salary of employee is: "+sal);
		}
		public void setEmployeeDetails() {
			System.out.println("Enter the id of employee: ");
			empId=sc.nextInt();
			System.out.println("Enter the name of employee: ");
			empName=sc.next();
			System.out.println("Enter the salary of employee (LPA): ");
			sal=sc.nextDouble();
		}
		public boolean getLoanEligibility() {
			System.out.println("Enter your experience: ");
			int exp=sc.nextInt();
			System.out.println(exp);
			if(exp>=5) {
				return true;
			}
			else {
				return false;
			}
		}
		
}
