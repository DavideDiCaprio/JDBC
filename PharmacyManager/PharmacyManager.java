import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

public class PharmacyManager {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String userName = "";
	private String password = "";
	
	public void makePurchase(ArrayList<Integer> IDList, ArrayList<Integer> AmountList) {
		
		for (int i = 0; i < IDList.size(); i++) {
			try(Connection conn = DriverManager.getConnection(url, userName, password);
			    Statement stmt = conn.createStatement();) 
			{
				String sql_command = "SELECT price,amount From products WHERE ID = " + IDList.get(i) + ";";
				ResultSet rs = stmt.executeQuery(sql_command);
				
				if (rs.next()) {
					if (rs.wasNull()) {
						System.out.println("Result set is null");
					} else {
						int price = rs.getInt("price");
						int amount = rs.getInt("amount");
						int newAmount = amount - AmountList.get(i);
						
						String sql_update = "UPDATE products SET amount =" + newAmount + "WHERE ID =" + IDList.get(i) + ";";
						stmt.executeUpdate(sql_update);
						
						System.out.println("Total: " + price * AmountList.get(i));
					}
				}
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkProduct(int ID) throws SQLException {
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();) 
		{
			String sql_command = "SELECT * From products WHERE ID= " + ID +";" ;
			
			ResultSet rs = stmt.executeQuery(sql_command);
					
			while (rs.next()) {
		          System.out.println("ID: " + rs.getInt("ID"));
		          System.out.println("Name: " + rs.getString("Name"));
		          System.out.println("Format: " + rs.getString("Format"));
		          System.out.println("Price: " + rs.getFloat("Price"));
		          System.out.println("Amount: " + rs.getInt("Amount"));
			}
		}
	}
	
	public void refill(int ID, int refillAmount) throws SQLException {
    	
    	System.out.println("ID = " + ID + "amount =" + refillAmount);
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();)
		{
			String sql_command = "SELECT Amount FROM Products WHERE ID = " + ID +";";
			ResultSet rs = stmt.executeQuery(sql_command);
			
			while (rs.next()) {
				if (rs.wasNull()) {
					System.out.println("Result set is null");
				} else {
					int amount = rs.getInt("amount");
					System.out.println("amount =" + rs.getInt("amount"));
					
					String sql_command1 = "UPDATE products SET amount =" + (refillAmount + amount) + "WHERE ID = " + ID + ";";
					stmt.executeUpdate(sql_command1);
				}
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		  }
			 
	}
       
	
	public void start() throws SQLException {
		
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		ArrayList<Integer> amountList = new ArrayList<Integer>();
		
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Select operation: purchase,check,refill ");
		String op = myScanner.nextLine();
		
		if(op.equals("purchase")) {
			
			while (true) {
				
				System.out.println("enter '0' to exit choise menu, or enter ID:");
				int inputID = myScanner.nextInt();
				
				if (inputID == 0) {
					
					System.out.println("choise terminated.Purchase loading...");
					if (IDList.size() == amountList.size()) {
						makePurchase(IDList,amountList);
						
					} else {
						System.out.println("different size list");
						break;
					}					
				}
								
				if (inputID != 0);{
					
					IDList.add(inputID);
					
					System.out.println("enter amount: ");
					int inputAmount = myScanner.nextInt();
					
					amountList.add(inputAmount);
				}
			}
			
	    } else if (op.equals("check")) {
	    	System.out.println("enter ID: ");
			int inputID = myScanner.nextInt();
			checkProduct(inputID);
			
		} else if (op.equals("refill")) {
	    	
			System.out.println("enter ID: ");
			int inputID = myScanner.nextInt();
			
			System.out.println("enter refil amount: ");
			int refilAmount = myScanner.nextInt();
			
			refill(inputID, refilAmount);
		}  	
	}
}		
