import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import junit.framework.Assert;

class PharmacyManagerTest {
	
	private String url = "";
	private String userName = "";
	private String password = "";
	
	void createProductTable() {
 		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();)
 		{
 			String createTable = "CREATE TABLE products" +
			"(ID int AUTO_INCREMENT, " +
			" Name varchar(255) ," +
			" Format enum('pills','powder suppositary'), " +
			" Price float, " +
			" Amount int, " +
			" PRIMARY KEY( ID ))";

			stmt.executeUpdate(createTable);
			System.out.println("Created table products in the test database...");
			System.out.println("...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void dropProdcutTable() {
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();)
		{
			String dropTable = "DROP TABLE products";
			stmt.executeUpdate(dropTable);
			System.out.println("Drop table products in the test database...");
			System.out.println("...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void insertProduct(String name, String format, float price, int amount){

		try(Connection conn = DriverManager.getConnection(url, userName, password);
				Statement stmt = conn.createStatement();) 
		
		{
			String insertProducts = "INSERT INTO products (Name,Format,Price,Amount) VALUES('" 
			+ name + "','" + format +  "','" + price +  "','" + amount + "')";
			
			stmt.executeUpdate(insertProducts);
			System.out.println("Insert value in table products...");
			System.out.println("...");
			
		} catch (SQLException e) {
				e.printStackTrace();
		}		    	    
	 }
	
	@Test
	void testCkeck() throws SQLException {

		createProductTable();
		
		PharmacyManager testPharmacy = new PharmacyManager();
		insertProduct();
		
		Product DBProductTest = new Product();
		assertEquals(DBProductTest, testPharmacy.checkProduct(1));
		
		dropProdcutTable();

	}	
	
	@Test
	void testRefill() throws SQLException {
		
		createProductTable();
		PharmacyManager testPharmacy = new PharmacyManager();
		insertProduct();
		
		// + 2 amount
		Product ProductTestRefill = new Product();
		
		assertEquals(ProductTestRefill, testPharmacy.refill(1,2)); 
		
		dropProdcutTable();
	}	
	
	@Test
	void testmakePurchase() throws SQLException {
		
		createProductTable();
		PharmacyManager testPharmacy = new PharmacyManager();
		insertProduct("","",.00f,);
		
		ArrayList<Integer> IDArray = new ArrayList<Integer>();
		IDArray.add(1);
		ArrayList<Integer> AmountArray = new ArrayList<Integer>();
		AmountArray.add(2);
		
		
		Product ProductTestMakePurchase = new Product(,"","",.0f,);

		assertEquals(00.00f, testPharmacy.makePurchase(IDArray, AmountArray));
		assertEquals(ProductTestMakePurchase, testPharmacy.checkProduct(1));				
		
		dropProdcutTable();
		
		}  
}

