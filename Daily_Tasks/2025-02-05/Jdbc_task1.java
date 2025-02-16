package jdbc;
import java.sql.*;
import java.util.Scanner;

public class Jdbc_task1 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techm?autoReconnect=true&useSSL=false","root","tiger");
		System.out.println("connected");
		while(true) {
			System.out.println("Enter details:");
			System.out.println("Enter id:");
			int id = sc.nextInt();
			System.out.println("Enter name:");
			String name = sc.next();
			System.out.println("enter marks:");
			int[] m=new int[4];
			for(int i=0;i<4;i++) {
				
				m[i]=sc.nextInt();

			}
			PreparedStatement ps = con.prepareStatement("insert into students values(?,?,?,?,?,?);");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, m[0]);
			ps.setInt(4, m[1]);
			ps.setInt(5, m[2]);
			ps.setInt(6, m[3]);
			ps.execute();
			System.out.println("Inserted the row.");
			System.out.println("want to add another row?(yes/no): ");
			String s=sc.next();
			if (s.equals("no")) {
				break;
			}
		}
	}

}
