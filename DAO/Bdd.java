package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
            String sql = "SELECT COUNT(*) FROM Annonce;";
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int nbLignes;
            if(rs.next()) {
                nbLignes = rs.getInt(1);
            }
            else
            {
                System.out.println("Error with SQL Query on table Annonce");
                nbLignes = 20;
            }
            Object[][] data = new Object[nbLignes][6];
            
            sql = "SELECT a.Catégorie, a.Description, a.Prix, u.Nom AS Vendeur, u.adresse, u.ville "
                    + "FROM Annonce AS a "
                    + "JOIN Utilisateur AS u ON u.IdUtilisateur=a.IdUtilisateur;";
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            int i = 0;
            while(rs.next() && i < nbLignes) {
                data[i][0] = rs.getString("Catégorie");
                data[i][1] = rs.getString("Description");
                data[i][4] = rs.getInt("Prix");
                data[i][5] = rs.getString("Vendeur");
                data[i][2] = rs.getString("Adresse");
                data[i][3] = rs.getString("Ville");
                i++;
            }
            return data;
        }
        catch(SQLException e) {
            System.out.println("Erreur getAnnonces : "+ e);
            return null;
        }
    }

	public static boolean ajouterAnnonce(String argPrix, String argDescription, String argCategorie, int argIdUser) {
		
	    String requete = "";  
	    Double prix = Double.parseDouble(argPrix);
	    
		boolean prixOk;
		
		Pattern pPrix = Pattern.compile("[0-9]*\\.[0-9]*");
		Matcher mPrix = pPrix.matcher(argPrix);
		prixOk = mPrix.matches();
			
		if(!prixOk) {
			// Fenêtre d'alerte
			String message = "Attention, il faut un prix de la forme : ??.?? ";
			JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
			model.Alerte.fenetreDialogue(lbmsg, "Erreur format Prix", 2 * 1000);
			return false;
		}
		
	    if(argDescription.contentEquals("") || argCategorie.contentEquals("")) {
			// Fenêtre d'alerte
			String message = "Veuillez remplir les champs";
			JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
			model.Alerte.fenetreDialogue(lbmsg, "Erreur annonce", 3 * 1000);
	    	return false;
	    }
	
	    requete = "INSERT INTO Annonce(Prix, Description, Catégorie, IdUtilisateur) VALUES(?,?,?,?)";

	    try {
	    	 pst = c.prepareStatement(requete);
	    	 pst.setDouble(1, prix);
	    	 pst.setString(2, argDescription);
	    	 pst.setString(3, argCategorie);
	    	 pst.setInt(4, argIdUser);
			 pst.executeUpdate();
	 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	     return true;
	}
	
	public static boolean creationCompte(String argNom, String argPrenom, String argMail, String argMotDePasse, String argAdresse, String argVille) {
		
		try{
			
			String query = "INSERT INTO Utilisateur (Nom, Prénom, Mail, Mdp, Adresse, Ville) VALUES (?, ?, ?, ?, ?, ?);";
			pst = c.prepareStatement(query);
			
			boolean mailOk, mdpOk ;
			
			Pattern pMail = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
			Matcher mMail = pMail.matcher(argMail);
			mailOk = mMail.matches();
				
			if(!mailOk) {
				// Fenêtre d'alerte
				String message = "Attention, votre email doit être de la forme __@__._";
				JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
				model.Alerte.fenetreDialogue(lbmsg, "Erreur format Mail", 3 * 1000);
				return false;
			}
						
			Pattern pMdp = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
			Matcher mMdp = pMdp.matcher(argMotDePasse);
			mdpOk = mMdp.matches();
			if(!mdpOk) {
				
				// Fenêtre d'alerte
				String message = "<html> Attention, votre mot de passe doit contenir au moins : <br/>"
								+ "	- Une lettre capitale <br/>"
								+ "	- Une lettre miniscule <br/>"
								+ "	- Un chiffre <br/>"
								+ "	- Un caractère spécial (!, $, &, ...) <br/>"
								+ "	- 8 caractères et moins de 20 </html>";
				
				JLabel lbmsg = new JLabel(message);
				model.Alerte.fenetreDialogue(lbmsg, "Erreur format Mot de Passe", 5 * 1000);
				return false;
			}
			
			
			if(argNom.contentEquals("") || argPrenom.contentEquals("") || argAdresse.contentEquals("") || argVille.contentEquals("")) {
				// Fenêtre d'alerte
				String message = "Veuillez remplir les champs";
				JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
				model.Alerte.fenetreDialogue(lbmsg, "Erreur inscription", 3 * 1000);
				return false;
			}
			
			pst.setString (1, argNom);
			pst.setString (2, argPrenom);
			pst.setString (3, argMail);
			pst.setString (4, argMotDePasse);
			pst.setString (5, argAdresse);
			pst.setString (6, argVille);

			pst.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static int connexionCompte(String argMail, String argMdp) {
		
		try{
			
			String query = "SELECT IdUtilisateur FROM Utilisateur WHERE Mail='" + argMail + "' AND Mdp='" + argMdp + "';";
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(!rs.next()) { // Si le résultat est vide, i.e la saisie est erronée ou le compte n'existe pas
				// Fenêtre d'alerte
				String message = "Combinaison mail/mot de passe inconnue, veuillez réessayer.";
				JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
				model.Alerte.fenetreDialogue(lbmsg, "Erreur Connexion", 3 * 1000);
				return -1;
			}
			return rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static Utilisateur getUser(int argUserId) {
		Utilisateur user;
		
		try{
			
			String query = "SELECT * FROM Utilisateur WHERE IdUtilisateur="+ argUserId +";";
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) { 
				user = new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				return user;
			}
			

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
