import java.util.Scanner;

class Main {  	
	
	public static void main(String[] args) {
		
		PersonDBManager DB = new PersonDBManager("*","*","*");
		
		while (true) {
			
			Scanner myScanner = new Scanner(System.in);
			System.out.println("Enter operation: ");		
	        String op = myScanner.nextLine();
			
			if (op.equals("write")) {
				
				System.out.println("Enter name: ");
				String name = myScanner.nextLine();
				
				System.out.println("Enter surname: ");
				String surname = myScanner.nextLine();
				
				System.out.println("Enter age: ");
				int age = myScanner.nextInt();
			    
				System.out.println("Enter gender: ");
				String gender = myScanner.nextLine();
				
				Person p = new Person(name,surname,age,gender);
				DB.writePerson(p);     
		      }
			
			else if (op.equals("read")) {
				System.out.println("Enter personID: ");
				int personID = myScanner.nextInt();
				DB.readPerson(personID);
				System.out.println(personID);
				
		      }
		      
			else if (op.equals("exit")) {
				System.out.println("!");
				break;
				}		
			}
		}
} 
