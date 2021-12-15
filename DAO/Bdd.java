package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import model.Utilisateur;

public class Bdd {
	
	private static Connection c;
	private static Statement stmt;
	private static PreparedStatement pst ;
	
	public static void connexion() {
		   
		Properties userInfo = new Properties();
		userInfo.setProperty("user", "alain");
		userInfo.setProperty("password",  "alainlimache1798");

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/polyVentes", userInfo);
		  
		} catch (SQLException ex2) {
		    	  
		    System.out.println("Erreur JDBC: "+ex2);
		    System.exit(1);
		      	  
		}
		      
	}
	
	public static Object[][] getAnnonces() {
		try {
			String sql = "COUNT(*) FROM Annonce;";
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int nbLignes = rs.getInt(1);
			Object[][] data = new Object[nbLignes][];
			
			sql = "SELECT a.Catégorie, a.Description, a.Prix, u.Nom AS Vendeur "
					+ "FROM Annonce AS a "
					+ "JOIN Utilisateur AS u ON u.IdUtilisateur=a.IdUtilisateur;";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()) {
				data[i][0] = rs.getString("Catégorie");
				data[i][1] = rs.getString("Description");
				data[i][2] = rs.getString("Adresse");
				data[i][3] = rs.getString("Ville");
				data[i][4] = rs.getInt("Prix");
				data[i][5] = rs.getString("Vendeur");
				i++;
			}
			return data;
		}
		catch(SQLException e) {
			System.out.println("Erreur getAnnonces : "+ e);
		    return null;
		}
	}
	

	@SuppressWarnings("resource")
	public static void ajouterAnnonce(Utilisateur user) {
		
	    String requete = "";  
	    
	    Scanner sc ;
	    
	    System.out.println("Entrez un prix : ");
		sc = new Scanner(System.in);
		Double prix = sc.nextDouble();
		
	    System.out.println("Entrez une description : ");
		sc = new Scanner(System.in);
		String description = sc.nextLine();
		
	    System.out.println("Entrez une catégorie : ");
		sc = new Scanner(System.in);
		String categorie = sc.nextLine();
						
	
	    requete = "INSERT INTO Annonce(Prix, Description, Categorie, IdUtilisateur) VALUES(?,?,?,?)";

	    try {
	    	 pst = c.prepareStatement(requete);
	    	 pst.setDouble(1, prix);
	    	 pst.setString(2, description);
	    	 pst.setString(3, categorie);
	    	 pst.setInt(4, user.getId());
			 pst.executeUpdate();
	 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

	



}
