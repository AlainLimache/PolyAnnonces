package vue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
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
    	
		this.init(this.getUser().getId());
		
    	this.setLocationRelativeTo(null); 	
    
       	this.setVisible(true);
    	    	
    }
    
	
    public void init(int idConnexion){

	    String[] headers = {"Catégorie", "Description", "Adresse", "Ville", "Prix", "Vendeur"};
	    
    	table = new JTable(new DefaultTableModel(Bdd.getAnnonces(), headers));
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	table.setAutoCreateRowSorter(true);
    	
    	this.getContentPane().setLayout(
    	        new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    	this.getContentPane().add(table.getTableHeader());
    	this.getContentPane().add(table);
    	
    	
    	// listener
    	table.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mousePressed(MouseEvent e) {
    	        int row = table.rowAtPoint(e.getPoint());
	        	new detailAnnonce(user,
	        			(String)table.getValueAt(row, 0),
	        			(String)table.getValueAt(row, 1),
	        			(String)table.getValueAt(row, 2), 
	        			(String)table.getValueAt(row, 3),
	        			(String)table.getValueAt(row, 4),
	        			(String)table.getValueAt(row, 5));
    	    }
    	    
    	    public void mouseReleased(MouseEvent e) {

    	    }
    	});
    	
		if(idConnexion == 0) {
			this.setTitle("Annonces (invité)");	
	        
	    	JPanel panel = new JPanel(new GridLayout(1,2,1,1));
	   
	    	panel.add(btnRecherche); 
	    	
	    	this.getContentPane().add(panel);
	    	this.pack();
	    	
		}else{
			this.setTitle("Annonces ( " + this.getUser().getPrenom() + " " + this.getUser().getNom() + " )");
			
	    	btnAjouterAnnonce = new JButton("Ajouter");
	    	btnAjouterAnnonce.addActionListener(this);
	    	btnAjouterAnnonce.setActionCommand("ajouter"); 

	    	this.getContentPane().add(btnAjouterAnnonce);
	    	JPanel panel = new JPanel(new GridLayout(1,2,1,1));
	    	panel.add(btnAjouterAnnonce); 
	    	panel.add(btnActualiser); 	
	    	panel.add(btnRecherche); 	
	    	
	    	this.getContentPane().add(panel);
	    	this.pack();
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ajouter"){
			new ajouterAnnonce(this.getUser(),this);
		}
		
		if (e.getActionCommand() == "actualiser"){
			this.getContentPane().removeAll();
			this.getContentPane().revalidate();
			this.getContentPane().repaint();
			init(this.getUser().getId());
		}
		
		if (e.getActionCommand() == "recherche"){
			new Recherche();
		}
		
	}
		
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

}

