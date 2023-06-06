import javax.print.attribute.standard.MediaSize.Other;

import org.junit.jupiter.api.Order;

public class Product {
	
	public int id;
	public String name;
	public String format;
	public float price;
	public int amount;
  
  public Product(int id, String name, String format, float price, int amount){
    
    this.id = id;
    this.name = name;
    this.format = format;
    this.price = price;
    this.amount = amount;
	  
  }
	
  @Override
  public boolean equals(Object obj) {
	  
	  if (obj == null) {
		  return false;
	  }
	  
	  if (obj.getClass() != this.getClass()) {
		  return false;
	  }
	  
	  Product other = (Product) obj;
	  
	  if (this.id != other.id) {
		  return false;
	  } 
	  
	  if (!(this.name.equals(other.name))) {
		  return false;  
	  }
	  
	  if (!(this.format.equals(other.format))) {
		  return false;
	  }
	  
	  if (this.price != other.price) {
		  return false;
	  }
	  
	  if (this.amount != other.amount) {
		  return false;
	  }
	  return true;
  }
	@Override
	public String toString() {
		return "--- \n" + this.id +"\n"+this.name +"\n"+this.format +"\n"+this.price +"\n"+this.amount ;
	}
}
