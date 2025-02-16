package jdbc;
import java.sql.*;
import java.util.Scanner;

public class Jdbc_task2 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techm?autoReconnect=true&useSSL=false","root","tiger");
		System.out.println("connected");
		System.out.println("Enter the details to be updated:\n");
		System.out.println("Enter id:");
		int id1 = sc.nextInt();
		System.out.println("Enter whether to update name or marks(1/0):");
		int choice = sc.nextInt();
		if(choice==1) {
			System.out.println("Enter new name:");
			String name = sc.next();
			PreparedStatement ps2 = con.prepareStatement("update students set name=? where id=?;");
			ps2.setString(1, name);
			ps2.setInt(2, id1);
			ps2.execute();
		}
		else {
		System.out.println("Enter subject number:");
		int subnumber = sc.nextInt();
		System.out.println("Enter marks:");
		int marks = sc.nextInt();
		PreparedStatement ps2 = con.prepareStatement("update students set s?=? where id=?;");
		ps2.setInt(1, subnumber);
		ps2.setInt(2, marks);
		ps2.setInt(3, id1);
		ps2.execute();
		}
		System.out.println("Updated the row number: "+id1);
	}

}
