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
			
			String productName = null;
			float price = 0;
			int amount = 0;
			ResultSet rs = null;
			
			try(Connection conn = DriverManager.getConnection(url, userName, password);
					Statement stmt = conn.createStatement();)
			{
				String sql_command = "SELECT name,price, amount FROM products WHERE ID = " + IDList.get(i) +";";
				stmt.executeQuery(sql_command);
				rs = stmt.executeQuery(sql_command);

				if (!rs.isBeforeFirst()){
					System.out.println("The following ID: " + IDList.get(i) + " can not be found in the database.");
				    System.out.println("");
				    return;
				}

				while (rs.next()) {
					productName = rs.getString("name");
					price = rs.getFloat("price");
					amount = rs.getInt("amount");	
				}
				
			} catch (SQLException e) {
				// 
				System.out.println("ECCEZIONE CATTURATA DAL TRY");
				e.printStackTrace();
				return;
			}

			if (AmountList.get(i) <= 0) {
				System.out.println("Amount must be greater than 0. " +  AmountList.get(i) + " not usable.");
				return;
			}
			System.out.println("You selected product: " + productName);
			System.out.println("Total spent: " + price * AmountList.get(i));
			System.out.println("");

			if (amount <= AmountList.get(i)) {
				System.out.println("Amount not available.");
				System.out.println("");		
				System.out.println(productName + "is no longer available. Max amount: " + amount + ".");		
				return;
			}

			int newAmount = amount - AmountList.get(i);
			String sql_command1 = "UPDATE products SET amount = " + newAmount + " WHERE ID =" + IDList.get(i) + ";";
			
			
			try (Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();) {
				stmt.executeUpdate(sql_command1);
			
			}

	    	catch (SQLException e) {
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
			
			if (!rs.isBeforeFirst()){
				System.out.println("The following ID: " + ID + " can not be found in the database.");
		        System.out.println("");
		        return;
			}
		
			while (rs.next()) {
		          System.out.println("ID: " + rs.getInt("ID"));
		          System.out.println("Name: " + rs.getString("Name"));
		          System.out.println("Format: " + rs.getString("Format"));
		          System.out.println("Price: " + rs.getFloat("Price"));
		          System.out.println("Amount: " + rs.getInt("Amount"));
		          System.out.println("");
		   }
	    } catch (SQLException e) {
	    	e.printStackTrace();
		}
   }
	
	
   public void refill(int ID, int refillAmount) throws SQLException {
    	
    	try(Connection conn = DriverManager.getConnection(url, userName, password);
    			Statement stmt = conn.createStatement();)
		{
    		String sql_command = "SELECT name,amount FROM products WHERE ID = " + ID + ";";
			stmt.executeQuery(sql_command);
			ResultSet rs = stmt.executeQuery(sql_command);
			
			String productName = null;
			int amount = 0;
			
			if (!rs.isBeforeFirst()){
				System.out.println("The following ID: " + ID + " can not be found in the database.");
		        System.out.println("");
		        return;
			}
			
			while (rs.next()) {
				productName = rs.getString("name");
				amount = rs.getInt("amount");
			}
			
			if (refillAmount <= 0) {
				System.out.println("Amount must be greater than 0. " +  refillAmount + "not usable.");
				return;
			}
			
			int newAmount = refillAmount + amount;
			System.out.println("Old amount : " + amount + "updating...");
	        System.out.println("");
			System.out.println("New amount for: " + productName + " is " + newAmount);
			
    		String sql_command1 = "UPDATE products SET amount = " + newAmount + " WHERE ID =" + ID + ";";
    		stmt.executeUpdate(sql_command1);

		}
    	
    	catch (SQLException e) {
    		e.printStackTrace();
		}	
	} 		
	
	public void start() throws SQLException {
		
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		ArrayList<Integer> amountList = new ArrayList<Integer>();
		
		while (true) {
			
			Scanner myScanner = new Scanner(System.in);
			System.out.println("Select operation: purchase, check, refill ... ");
			String op = myScanner.nextLine();	
			
			if(op.equals("purchase")) {
				
				while (true) {
					
					System.out.println("enter '0' to exit choice menu, or enter ID:");
					int inputID = myScanner.nextInt();
					
					if (inputID == 0) {
						
						System.out.println("choice terminated. Loading...");
						if (IDList.size() != amountList.size()) {
							System.out.println("Different size list");
							break;
						}
					    makePurchase(IDList,amountList);
					}

					else if (inputID != 0);{
						IDList.add(inputID);
						System.out.println("Enter amount: ");
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
				
				System.out.println("enter refill amount: ");
				int refilAmount = myScanner.nextInt();
				
				refill(inputID, refilAmount);
		    		    	
		    }
		}	
	}
}
