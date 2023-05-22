public class Person {
  
  private String name;
  private String surname;
  private int age;
  private String gender;

  public Person(String name,String surname,int age,String gender){

    this.name = name;
    this.surname = surname;
    this.age = age;
    this.gender = gender;

  }
  
  @Override
  public String toString () {
	  return this.name +"\n"+this.surname +"\n"+this.age +"\n"+this.gender;
  }
	  
  public String getName(){
	  return this.name; 
  }
  public void setName(String name){
	  this.name = name;
  }
  public String getSurname(){
	  return this.surname;
  }
  public void setSurname(String surname){
	  this.surname = surname;
  }
  public int getAge(){
	  return this.age;
  }
  public void setAge(int age){
	  this.age = age;
  }
  public String getGender() {
	  return this.gender;
  }
  public void setGender(String gender) {
	  this.gender = gender;
  }
}
