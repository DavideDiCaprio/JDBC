import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDBManager {
	
	private String url = "*";
	private String userName = "*";
	private String password = "*";
	
	public PersonDBManager(String url,String userName, String password) {
		
		this.url = url;
		this.userName = userName;
		this.password = password;
		
		}
	
	public void writePerson(Person p) {
		
		try(Connection conn = DriverManager.getConnection(this.url, this.userName, this.password);
				
				Statement stmt = conn.createStatement();
				) {
			
			String sql_command = "INSERT INTO Person(Name, Surname, Age, Gender) VALUES(" 
							   "'" + p.getName() +
					           "','" + p.getSurname() +
					           "','" + p.getAge() +
					           "','" + p.getGender() + "')";
			
			stmt.executeUpdate(sql_command);
			System.out.println("Insert value in table Person...");
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
	}
	   
	   public void readPerson(int PersonID) {
		   try(Connection conn = DriverManager.getConnection(this.url, this.userName, this.password);
				   Statement stmt = conn.createStatement();
				   ) {
			   String sql_command = "SELECT * FROM Person WHERE PersonID =" + PersonID + ";";
			   stmt.executeQuery(sql_command);
			   System.out.println("Select " + PersonID + " user.");
			   }
		   
		   catch (SQLException e) {
			   e.printStackTrace();
			   } 
	   }
}
