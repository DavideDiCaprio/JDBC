import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ChickenStoreManager {
	
	private String url = "jdbc:mysql://localhost:3306/ChickenOrder";
	private String userName = "root";
	private String password = "gatesss";
	
	public void start() throws SQLException {
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Select operation: order,modify,delete,visualize ");
		String op = myScanner.nextLine();
		
		if (op.equals("order")) {
			//write order
			System.out.println("Enter chickens number: ");
			int chicken = myScanner.nextInt();
			
			System.out.println("Enter fries: ");
			int fries = myScanner.nextInt();
			
			System.out.println("Enter drinks: ");
			int drinks = myScanner.nextInt();
			
			ChickenOrder ord = new ChickenOrder(chicken, fries, drinks);
			
			try(Connection conn = DriverManager.getConnection(url, userName, password);
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
				e.printStackTrace();}
		}
		
		else if (op.equals("modify")) {
			System.out.println("What you wanna change? (chicken,fries,drink) ");
			String selectedTable = myScanner.nextLine();
			
			System.out.println("enter new quantity:");
			int quantity = myScanner.nextInt();
			
			System.out.println("enter orderID: ");
			int ordID = myScanner.nextInt();
			
			try(Connection conn = DriverManager.getConnection(url, userName, password);
			    Statement stmt = conn.createStatement();
			   ) {
				//Insert into table ChickenOrder
				String sql_command = "UPDATE Orders SET " + selectedTable + " =" + quantity + " WHERE OrderID = " + ordID + ";";
				stmt.executeUpdate(sql_command);
				System.out.println("...");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if (op.equals("delete")) {
			System.out.println("enter orderID ");
			int ordID = myScanner.nextInt();
			try(Connection conn = DriverManager.getConnection(url, userName, password);
			    Statement stmt = conn.createStatement();
			   ) {
				//delete table
				String sql_command = "DELETE FROM Orders WHERE OrderID = " + ordID + ";";
				stmt.executeUpdate(sql_command);
				System.out.println("Execute delete...");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if (op.equals("visualize")) {
			System.out.println("enter orderID: ");
			int ordID = myScanner.nextInt();
			try(Connection conn = DriverManager.getConnection(url, userName, password);
			    
			    Statement stmt = conn.createStatement();
			   ) {
				String sql_command = "Select * From Orders WHERE OrderID = " + ordID + ";";
				ResultSet rs = stmt.executeQuery(sql_command);
				System.out.println("result: ");
				
				while (rs.next()) {
					// Display values
				        System.out.print("Chicken: " + rs.getInt("Chicken"));
				        System.out.print("Fries: " + rs.getInt("Fries"));
				        System.out.print("Drinks: " + rs.getInt("Drink"));
				}
			}
		}
	
	}
	
}
