package jdbc;
import java.sql.*;
import java.util.Scanner;

public class Jdbc_task4 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techm?autoReconnect=true&useSSL=false","root","tiger");
		System.out.println("connected");
		boolean flag =true;
		do{
			System.out.println("1.Insert\n2.Update\n3.Read\n4.delete\n5.Exit\nEnter a choice:");
			int choice = sc.nextInt();
			int m[] = new int[4];
			switch(choice)
			{
			case 1:
				System.out.println("Enter details:");
				System.out.println("Enter id:");
				int id = sc.nextInt();
				System.out.println("Enter name:");
				String name = sc.next();
				System.out.println("enter marks:");
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
				break;
			case 2:
				System.out.println("Enter the id and subject number and marks to be updated:\n");
				System.out.println("Enter id:");
				int id1 = sc.nextInt();
				System.out.println("Enter Subject no:");
				int subno = sc.nextInt();
				System.out.println("Enter marks:");
				int marks = sc.nextInt();
				PreparedStatement ps2 = con.prepareStatement("update students set s?=? where id=?;");
				ps2.setInt(1, subno);
				ps2.setInt(2, marks);
				ps2.setInt(3, id1);
				ps2.execute();
				break;
			case 3:
				PreparedStatement ps3 = con.prepareStatement("select * from students;");
				ResultSet rs = ps3.executeQuery();
				while(rs.next())
				{
					System.out.print("id:"+rs.getInt(1)+", ");
					System.out.print("name:"+rs.getString(2)+", ");
					System.out.print("subject 1 marks:"+rs.getInt(3)+", ");
					System.out.print("subject 2 marks:"+rs.getInt(4)+", ");
					System.out.print("subject 3 marks:"+rs.getInt(5)+", ");
					System.out.println("subject 4 marks:"+rs.getInt(6));
				}
				break;
			case 4:
				System.out.println("Enter the id to be deleted:");
				System.out.println("Enter id:");
				int id4 = sc.nextInt();
				PreparedStatement ps4 = con.prepareStatement("delete from students where id=?;");
				ps4.setInt(1, id4);
				ps4.executeUpdate();
				break;
			case 5:
				flag=false;
			}	
		}while(flag);

		
	}

}
