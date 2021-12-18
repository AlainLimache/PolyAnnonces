package vue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import DAO.Bdd;
import model.Utilisateur;   

public class Annonces extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
	JButton btnAjouterAnnonce;	
	JButton btnActualiser;
	JButton btnRecherche;	
	JTable table;
	
	
    public Annonces(Utilisateur argUser) {    

		setUser(argUser);
		
		btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(this);
		btnActualiser.setActionCommand("actualiser"); 
    	
		btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(this);
		btnRecherche.setActionCommand("recherche"); 
    	
		if(this.getUser().getId() == 0) {
			this.setTitle("Annonces (invité)");
			initTableau();
			
	        
	    	JPanel panel = new JPanel(new GridLayout(1,2,1,1));
	   
	    	panel.add(btnActualiser); 	
	    	panel.add(btnRecherche); 	
	    	
	    	this.getContentPane().add(panel);
	    	
		}else{
			this.setTitle("Annonces ( " + this.getUser().getPrenom() + " " + this.getUser().getNom() + " )");
			
	    	initTableau();
	    	btnAjouterAnnonce = new JButton("Ajouter");
	    	btnAjouterAnnonce.addActionListener(this);
	    	btnAjouterAnnonce.setActionCommand("ajouter"); 

	    	this.getContentPane().add(btnAjouterAnnonce);
	    	JPanel panel = new JPanel(new GridLayout(1,2,1,1));
	    	panel.add(btnAjouterAnnonce); 
	    	panel.add(btnActualiser); 	
	    	panel.add(btnRecherche); 	
	    	
	    	this.getContentPane().add(panel);
	    	
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
    	this.setLocationRelativeTo(null); 	
    
    	this.pack();
    	this.setVisible(true);
    	    	
    }
    
	
    public void initTableau(){

	    String[] headers = {"Catégorie", "Description", "Adresse", "Ville", "Prix", "Vendeur"};
	    
    	table = new JTable(new DefaultTableModel(Bdd.getAnnonces(), headers));
    	
    	
    	this.getContentPane().setLayout(
    	        new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    	this.getContentPane().add(table.getTableHeader());
    	this.getContentPane().add(table);
    	
    	
    	// listener
    	table.getTableHeader().addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	        int col = table.columnAtPoint(e.getPoint());
    	        String name = table.getColumnName(col);
    	        System.out.println("Column index selected " + col + " " + name);
    	    }
    	});
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ajouter"){
			new ajouterAnnonce(this.getUser());
		}
		
		if (e.getActionCommand() == "actualiser"){
			

		}
		
		if (e.getActionCommand() == "recherche"){
	
		}
		
	}
		
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

}

