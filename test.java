import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

public class PharmacyManager {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String userName = "root";
	private String password = "";
	
	public void makePurchase(ArrayList<Integer> IDList, ArrayList<Integer> AmountList) {
		
		for (int i = 0; i < IDList.size(); i++) {
			
			try(Connection conn = DriverManager.getConnection(url, userName, password);
					Statement stmt = conn.createStatement();) 
			{
				String sql_command = "SELECT price,amount, From products WHERE ID=" + IDList.get(i) +";";
				
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
			String sql_command = "SELECT * From products WHERE ID=" + ID +";";
			
			ResultSet rs = stmt.executeQuery(sql_command);
					
			while (rs.next()) {
		          System.out.print("ID: " + rs.getInt("ID"));
		          System.out.print("Name: " + rs.getString("Name"));
		          System.out.print("Format: " + rs.getString("Format"));
		          System.out.print("Price: " + rs.getFloat("Price"));
		          System.out.print("Amount: " + rs.getInt("Amount"));
		   }
	    }
   }
		
    public void refill(int ID, int refillAmount) throws SQLException {
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();)
		
		{
			String sql_command = "SELECT amount From products WHERE ID=" + ID +";";
			
			ResultSet rs = stmt.executeQuery(sql_command);
			
			if (rs.next()) {
				
				if (rs.wasNull()) {
					System.out.println("Result set is null");
				} else {
					int amount = rs.getInt("amount");
					
					String sql_command1 = "UPDATE products SET amount =" + (refillAmount + amount) + "WHERE ID=" + ID + ";";
					stmt.executeUpdate(sql_command1);
				}
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
				
		
			}
			 
		}
       
	
	public void start() {
		
		ArrayList<Integer> arlist = new ArrayList<Integer>();
		ArrayList<Integer> amount = new ArrayList<Integer>();
		
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Select operation: purchase,check,refill");
		String op = myScanner.nextLine();
		
		if(op.equals("purchase")) {
			
			System.out.println("enter '0' to exit");
			System.out.println("enter ID list: ");
			int inputID = myScanner.nextInt();
					
			if (inputID == 0) {
				System.out.println("Program has been terminated.");
			
			} else {
				arlist.add(inputID);
			}
			
			System.out.println("enter '0' to exit");
			System.out.println("enter amount: ");
			int inputAmount = myScanner.nextInt();
			
			while (true);{
				if (inputAmount == 0) {
					System.out.println("Program has been terminated.");
					break;
				} else {
					amount.add(inputAmount);
				}
				
				if (arlist.size() == amount.size()) {
				makePurchase(arlist,amount);
				
				} else {
					break;
				}
			}
	    }
	}
	
}

}
