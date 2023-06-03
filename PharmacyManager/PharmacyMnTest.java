import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import junit.framework.Assert;

class PharmacyManagerTests {
  
  PharmacyManager mypharmacy = new PharmacyManager();

	@Test
	void testCkeck() throws SQLException {
    
    Product DBProductTestCheck = new Product(,"","",f,); //
    assertEquals(DBProductTestCheck, mypharmacy.checkProduct());
    
	}	

	@Test
	void testRefill() throws SQLException {
    
    Product DBProductTestRefill = new Product(,"","",f,); //
    assertEquals(DBProductTestRefill, mypharmacy.refill(, ));
    
  }
  
	@Test
	void testmakePurchase() {
    ArrayList<Integer> IDArray = new ArrayList<Integer>();
		IDArray.add();
		
		ArrayList<Integer> AmountArray = new ArrayList<Integer>();
		AmountArray.add();
		
		Product DBProductTestPurchase = new Product((,"","",f,); 
		assertEquals(DBProductTestPurchase,mypharmacy.makePurchase(IDArray,AmountArray));
		}  
}
