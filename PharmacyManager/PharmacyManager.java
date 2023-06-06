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

	@SuppressWarnings("null")
	public float makePurchase(ArrayList<Integer> IDList, ArrayList<Integer> AmountList) {
		
		Float bill = 0.0f;
		String productName = null;
		Float price = 0.0f;
		int amount = 0;
		
		for (int i = 0; i < IDList.size(); i++) {
				
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
					return (Float) null;
				}

				while (rs.next()) {
					productName = rs.getString("name");
					price = rs.getFloat("price");
					amount = rs.getInt("amount");	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return (Float) null;
			}

			if (AmountList.get(i) <= 0) {
				System.out.println("Amount must be greater than 0. " +  AmountList.get(i) + " not usable.");
				return (Float) null;
			}
		
			System.out.println("You selected product: " + productName);
			System.out.println("");

			if (amount <= AmountList.get(i)) {
				System.out.println("Amount not available.");
				System.out.println("");		
				System.out.println(productName + "is no longer available. Max amount: " + amount + ".");		
				return (Float) null;
			}
			
			bill = price * AmountList.get(i);
			System.out.println("Total spent: " + bill);
			System.out.println("");

			int newAmount = amount - AmountList.get(i);
			String sqlUpdate = "UPDATE products SET amount = " + newAmount + " WHERE ID =" + IDList.get(i) + ";";
			
			try (Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();) {
				stmt.executeUpdate(sqlUpdate);
			
			}

	    	catch (SQLException e) {
	    		e.printStackTrace();	    			
	    		}
		}
				
		return bill;			
	}
	
	
	public Product checkProduct(int ID) throws SQLException {
			
		int id = 0;
		String name = null;
		String format = null;
		Float price = 0.0f;
		int amount = 0;
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();) 
		{
			String sql_command = "SELECT * From products WHERE ID =" + ID +";";
			stmt.executeQuery(sql_command);
			ResultSet rs = stmt.executeQuery(sql_command);
			
			if (!rs.isBeforeFirst()){
				System.out.println("The following ID: " + ID + " can not be found in the database.");
		        System.out.println("");
		        return null;
			}
						
			while (rs.next()) {
				id = rs.getInt("ID");
				name = rs.getString("Name");
				format = rs.getString("Format");
				price = rs.getFloat("Price");
				amount = rs.getInt("Amount");
		   }		
	
	    } catch (SQLException e) {
	    	e.printStackTrace();
		}
		
		Product p = new Product(id,name,format,price,amount);
		return p;
   }
	
	
   public Product refill(int ID, int refillAmount) throws SQLException {
	   
	   String name = null;
	   String format = null;
	   int nwAmount = 0;
	   Float price = 0.0f;
	   
	   try(Connection conn = DriverManager.getConnection(url, userName, password);
    			Statement stmt = conn.createStatement();)
		{
		   String sql_command = "SELECT name,amount FROM products WHERE ID = " + ID + ";";
		   stmt.executeQuery(sql_command);
		   ResultSet rs = stmt.executeQuery(sql_command);
		   
		   String productName = null;
		   int amount = 0;
		   
		   if (!rs.isBeforeFirst()) {
			   System.out.println("The following ID: " + ID + " can not be found in the database.");
			   System.out.println("");
			   }
		   
		   while (rs.next()) {
			   productName = rs.getString("name");
			   amount = rs.getInt("amount");
			   }
		   
		   if (refillAmount <= 0) {
			   System.out.println("Amount must be greater than 0. " +  refillAmount + "not usable.");
				return null;
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
	   
	   try (Connection conn = DriverManager.getConnection(url, userName, password);
			   Statement stmt = conn.createStatement();) {
		   
		   String sqlQuery = "SELECT * FROM products WHERE ID =" + ID + ";";
		   stmt.executeQuery(sqlQuery);
		   ResultSet rs = stmt.executeQuery(sqlQuery);
		   
		   while (rs.next()) {
			   name = rs.getString("Name");
			   format = rs.getString("Format");
			   nwAmount = rs.getInt("amount");
			   price = rs.getFloat("price");
			   }
		   }
	   
	   catch (SQLException e) {
		   e.printStackTrace();
		   }
	   
	   
	   Product p = new Product(ID,name,format,price,nwAmount);
	   return p;	
		
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
			
			System.out.println("Closing Scanner...");
			myScanner.close();
			System.out.println("Scanner Closed.");
		}	
	}
}
