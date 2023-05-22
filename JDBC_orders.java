import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
 
  static final String DB_CONNECTION_URL = "*";

  public static void main(String args[]) {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      c = DriverManager.getConnection(DB_CONNECTION_URL);
      System.out.println("Connection Opened Successfully");

      Statement stmt = c.createStatement();
      System.out.println("Statement Created Successfully");

      //////////////////////////////

    /*  ////////////// DROP TABLE ORDERS 
        ////////////// use if you want execute code more then one time. To execute code you need to have a empty table ;)
      String drop_table_instruction = "DROP TABLE ORDERS";
      stmt.executeUpdate(drop_table_instruction);
      System.out.println("Dropped table ORDERS in the database..."); */ 
      
      ////////////// CREATE TABLE ORDERS
      String create_table_instruction = "CREATE TABLE IF NOT EXISTS ORDERS" +
          "(OrderID SERIAL not NULL, " +
          " Customer VARCHAR(255), " +
          " Quantity INTEGER, " +
          " PRIMARY KEY ( OrderID ))";

      stmt.executeUpdate(create_table_instruction);
      System.out.println("Created table ORDERS in the database...");
      ////////////// TABLE CREATION SUCCESSFUL ORDERS

      ////////////// ADD ROW TO THE TABLE ORDERS
      System.out.println("Inserting records into the table...");
      String sql_add_row = "INSERT INTO ORDERS VALUES (DEFAULT,'Davide',10)";
      stmt.executeUpdate(sql_add_row);
      System.out.println("Added row in the database...");
      ////////////// ADDED ROW SUCCESSFULLY
      
      ////////////// ADD ROW TO THE TABLE ORDERS
      System.out.println("Inserting records into the table...");
      sql_add_row = "INSERT INTO ORDERS VALUES (DEFAULT,'Giuseppe', 12)";
      stmt.executeUpdate(sql_add_row);
      System.out.println("Added row in the database...");
      ////////////// ADDED ROW SUCCESSFULLY

      ////////////// ADD ROW TO THE TABLE ORDERS
      System.out.println("Inserting records into the table...");
      sql_add_row = "INSERT INTO ORDERS VALUES (DEFAULT,'Paolo', 4)";
      stmt.executeUpdate(sql_add_row);
      System.out.println("Added row in the database...");
      ////////////// ADDED ROW SUCCESSFULLY

      ////////////// QUERY THE TABLE ORDERS
      System.out.println("Selecting records from the table...");
      String select_query = "SELECT * FROM ORDERS";
      ResultSet rs = stmt.executeQuery(select_query);
      System.out.println("Query Results: ");

      while (rs.next()) {
        // Display values
        System.out.print("OrderID: " + rs.getInt("OrderID"));
        System.out.print(", Customer: " + rs.getString("Customer"));
        System.out.println(", Quantity: " + rs.getInt("Quantity"));
      }
      ////////////// QUERY RUN SUCCESFULLY

      ////////////// QUERY THE TABLE ORDERS
      System.out.println("Selecting records from the table...");
      select_query = "SELECT Quantity FROM ORDERS";
      rs = stmt.executeQuery(select_query);
      System.out.println("Query Results: ");
      
      while (rs.next()) {
        // Display values
        System.out.println(", Quantity: " + rs.getInt("Quantity"));
      }
      ////////////// QUERY RUN SUCCESFULLY
      
      ////////////// QUERY THE TABLE ORDERS
      System.out.println("Selecting records from the table...");
      select_query = "SELECT * FROM ORDERS ORDER BY Quantity DESC";
      rs = stmt.executeQuery(select_query);
      System.out.println("Query Results: "); 
      
      while (rs.next()) {
        // Display values
        System.out.println(", Quantity: " + rs.getInt("Quantity"));
      } 
      ////////////// QUERY RUN SUCCESFULLY

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }
}

///////////////
