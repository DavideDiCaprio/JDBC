import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	
	static final String url = "jdbc:mysql://localhost:3306/";
	static final String userName = "";
	static final String password = "";
	
	public static void main(String[] args) {
		// Open a connection
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();)
		{
			//
			String create_table_instruction = "CREATE TABLE IF NOT EXISTS Products" +
			"(ID int AUTO_INCREMENT, " +
			" Name varchar(255) ," +
			" Format enum('pills','powder suppositary'), " +
			" Price float, " +
			" Amount int, " +
			" PRIMARY KEY( ID ))";

			stmt.executeUpdate(create_table_instruction);
			System.out.println("Created table Products in the database...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
