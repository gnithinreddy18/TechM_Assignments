package jdbc;
import java.sql.*;
import java.util.Scanner;

public class Jdbc_task3 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techm?autoReconnect=true&useSSL=false","root","tiger");
		System.out.println("connected");
		System.out.println("Enter the id to be deleted:");
		System.out.println("Enter id:");
		int id = sc.nextInt();
		PreparedStatement ps4 = con.prepareStatement("delete from students where id=?;");
		ps4.setInt(1, id);
		ps4.executeUpdate();
		System.out.println("Deleted the row number: "+id);
	}

}
