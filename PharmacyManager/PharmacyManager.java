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
	
	//
	public void makePurchase(ArrayList<Integer> IDList, ArrayList<Integer> AmountList) {
		
		for (int i = 0; i < IDList.size(); i++) {
			try(Connection conn = DriverManager.getConnection(url, userName, password);
			    Statement stmt = conn.createStatement();)
			{
				String sql_command = "SELECT price, amount FROM products WHERE ID = " + IDList.get(i) +";";
				stmt.executeQuery(sql_command);
				ResultSet rs = stmt.executeQuery(sql_command);
				
				float price = 0;
				int amount = 0;
				
				while (rs.next()) {
					price = rs.getFloat("price");
					amount = rs.getInt("amount");
				}
				
				System.out.println("Total spent: " + price * AmountList.get(i));
				int newAmount = amount - AmountList.get(i);
				
	    		String sql_command1 = "UPDATE products SET amount = " + newAmount + " WHERE ID =" + IDList.get(i) + ";";
	    		System.out.println(sql_command1);
	    		stmt.executeUpdate(sql_command1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	//
	public void checkProduct(int ID) throws SQLException {
		try(Connection conn = DriverManager.getConnection(url, userName, password);
		    Statement stmt = conn.createStatement();) 
		{
			String sql_command = "SELECT * From products WHERE ID =" + ID +";";
			stmt.executeQuery(sql_command);
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
	
   //
   public void refill(int ID, int refillAmount) throws SQLException {
	   
	   try(Connection conn = DriverManager.getConnection(url, userName, password);
	       Statement stmt = conn.createStatement();)
	   {
		   String sql_command = "select amount from products WHERE ID = " + ID + ";";
		   stmt.executeQuery(sql_command);
		   ResultSet rs = stmt.executeQuery(sql_command);
		   int amount = 0;
		   
		   while (rs.next()){
			   amount = rs.getInt("amount");
		   }
		   
		   int newAmount = refillAmount + amount;
		   System.out.println("refill amount: " + newAmount);
		   
		   String sqlUpdate = "UPDATE products SET amount = " + newAmount + " WHERE ID =" + ID + ";";
		   stmt.executeUpdate(sqlUpdate);
    		
		}
	   catch (SQLException e) {
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
					
					System.out.println("choicve terminated.Purchase loading...");
					if (IDList.size() == amountList.size()) {		
						makePurchase(IDList,amountList);
						break;
					} else {
						System.out.println("different size list");
						break;
					}					
				}
								
				else if (inputID != 0);{
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
