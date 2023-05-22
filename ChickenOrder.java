public class ChickenOrder {
  
  private int chicken;
  private int fries;
  private int drinks;

  public ChickenOrder(int chicken, int fries, int drinks){

    this.chicken = chicken;
    this.fries = fries;
    this.drinks = drinks;

  }
  
  @Override
  public String toString () {
	  return this.chicken +"\n"+this.fries +"\n"+this.drinks;
  }
	  
  public int getChicken(){
	  return this.chicken; 
  }
  public void setChicken(int chicken){
	  this.chicken = chicken;
  }
  public int getFries(){
	  return this.fries; 
  }
  public void setFries(int fries){
	  this.fries = fries;
  }
  public int getDrinks(){
	  return this.drinks; 
  }
  public void setDrinks(int drinks){
	  this.drinks = drinks;
  }
  
}
