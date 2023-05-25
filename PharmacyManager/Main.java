import java.sql.SQLException;

class Main {
	
	public static void main(String[] args) throws SQLException {
		
		PharmacyManager myPharmacy = new PharmacyManager();
		myPharmacy.start();

		}	
}

//check ok 
//refill to your MySQL server version for the right syntax to use near 'ID=1' at line 1

//classe PharmacyManager manipola tabella products
// tab Products con le colonne: id, name, format (uno tra pills, powder suppository), price e amount
//Amount si riferisce alla giacenza (quanti prodotti sono in magazzino)
//classe PharmacyManager deve avere i metodi:
//Make purchase (prende in input una lista di id e una lista di quantità. Per ogni elemento, rimuovi dal database 
//la quantità desiderata e fai una print del conto (prezzo dell’articolo * la quantità comprata)
//metodo checkProduct che dato l’id ti ritorna tutta la riga del db
// metodo refill storage che dato un id e un amount aumenta l’amount corrispondente a quell’id dell’amount preso come input