import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTable {
	
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String userName = "";
	private static String password = "";
	
	public static void main(String[] args) {
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();) 
		{
			String sql_command = "INSERT INTO Products (Name,Format,Price,Amount) VALUES('','','','')";
			//
			stmt.executeUpdate(sql_command);
			System.out.println("Insert value in table Products...");
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
			
}
