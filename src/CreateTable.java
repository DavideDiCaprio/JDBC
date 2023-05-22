import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
   static final String url = "jdbc:mysql://localhost:3306/ChickenOrder";
   static final String userName = "root";
   static final String password = "gatesss";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(url, userName, password);
         Statement stmt = conn.createStatement();
      ) {		      
          ////////////// CREATE TABLE ORDERS
          String create_table_instruction = "CREATE TABLE IF NOT EXISTS Orders" +
              "(OrderID int AUTO_INCREMENT, " +
              " Chicken int," +
              " Fries int," +
              " Drink int," +
              " PRIMARY KEY( OrderID ))";

          stmt.executeUpdate(create_table_instruction);
          System.out.println("Created table Orders in the database...");

         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}