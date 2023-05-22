import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		DBMenageOrder DB = new DBMenageOrder("jdbc:mysql://localhost:3306/","","");
		
		while (true) {
		
			Scanner myScanner = new Scanner(System.in);
			System.out.println("Enter operation: ");		
	        String op = myScanner.nextLine();
	        
	        if (op.equals("order")) {
	        	
	        	System.out.println("Enter chickens number: ");
	        	int chicken = myScanner.nextInt();

	        	System.out.println("Enter fries: ");
	        	int fries = myScanner.nextInt();

	        	System.out.println("Enter drinks: ");
	        	int drinks = myScanner.nextInt();

	        	ChickenOrder ord = new ChickenOrder(chicken,fries,drinks);
	        	DB.writeOrder(ord);
	        	System.out.println(DB.toString());	
	        	
	        }
	        
			else if (op.equals("read")) {
				System.out.println("Enter OrderID: ");
				int OrderID = myScanner.nextInt();				
			}
			
			else if (op.equals("exit")) {
				System.out.println("!");
				break;
			}
			
		}
		
}
