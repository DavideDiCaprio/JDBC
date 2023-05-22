import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMenageOrder {
	
	private String url = "jdbc:mysql://localhost:3306/PersonDB";
	private String userName = "root";
	private String password = "gatesss";
	
	public DBMenageOrder(String url,String userName, String password) {
		
		this.url = url;
		this.userName = userName;
		this.password = password;
		
		}
	
	public void writeOrder(ChickenOrder ord) {
		
		try(Connection conn = DriverManager.getConnection(this.url, this.userName, this.password);
				Statement stmt = conn.createStatement();
				) {
			//Insert into table ChickenOrder
			String sql_command = "INSERT INTO Orders (Chicken, Fries, Drink) VALUES (" +
			"'" + ord.getChicken() +
			"','" + ord.getFries() +
			"','" + ord.getDrinks() + "');";
			
			stmt.executeUpdate(sql_command);
			System.out.println("Insert value in table Order...");
			} catch (SQLException e) {
				e.printStackTrace();
				} 
	   }
	
	public void readOrder(int OrderID )  {
		try(Connection conn = DriverManager.getConnection(this.url, this.userName, this.password);
				Statement stmt = conn.createStatement();
				) {
			String sql_command = "SELECT * FROM Person WHERE OrderID =" + OrderID + ";";
			stmt.executeQuery(sql_command);
			System.out.println("Select " + OrderID + " user.");
			}
		
		catch (SQLException e) {
			e.printStackTrace();
			} 
	   }
}