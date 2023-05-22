import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
   static final String url = "*";
   static final String userName = "*";
   static final String password = "*";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(url, userName, password);
         Statement stmt = conn.createStatement();
      ) {		      
          ////////////// CREATE TABLE ORDERS
          String create_table_instruction = "CREATE TABLE IF NOT EXISTS nametable " +
              "(PersonID int AUTO_INCREMENT, " +
              " Name VARCHAR(255), " +
              " Surname VARCHAR(255), " +
              " Age INTEGER, " +
              " Gender VARCHAR(255), " +
              " PRIMARY KEY ( PersonID ))";

          stmt.executeUpdate(create_table_instruction);
          System.out.println("Created table Person in the database...");

         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
