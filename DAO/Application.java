package DAO;
import vue.menuPrincipal;

public class Application {
	
	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch (ClassNotFoundException ex1) {
	    	  System.out.println("Pilote non trouvé!");
	    	  System.exit(1);
	    }
	      
		new menuPrincipal();
		
	}
	   
}
