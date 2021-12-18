package vue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DAO.Bdd;

public class Recherche extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnOk;	
	JLabel textPrixMin;
	JLabel textPrixMax;
	JLabel textCategorie;
	JLabel textVille;
	JTextField champsPrixMin;
	JTextField champsPrixMax;
	JTextField champsVille;
	JComboBox<String> categories;
	JTable table;
	
	
    public Recherche() {    

		setTitle("Recherche");
		setSize(500, 200);
    	btnOk = new JButton("ok");
    	btnOk.addActionListener(this);
    	btnOk.setActionCommand("ok"); 

    	textPrixMin = new JLabel("Prix min : ");
    	textPrixMin.setHorizontalAlignment(JLabel.CENTER);
	    
    	textPrixMax = new JLabel("Prix max : ");
    	textPrixMax.setHorizontalAlignment(JLabel.CENTER);
	        
    	textCategorie = new JLabel("Catégorie : ");
    	textCategorie.setHorizontalAlignment(JLabel.CENTER);
    	
    	textVille = new JLabel("Ville : ");
    	textVille.setHorizontalAlignment(JLabel.CENTER);
	    
	    categories = new JComboBox<String>();
	    categories.addItem("Maison");
	    categories.addItem("Appartement");
	    categories.addItem("autre");  
	    
	    champsPrixMin = new JTextField();
	    champsPrixMax = new JTextField();
	    champsVille = new JTextField();


		this.init();
		
    	this.setLocationRelativeTo(null); 	
    
       	this.setVisible(true);
    	    	
    }
    
	
    public void init(){

	    String[] headers = {"Catégorie", "Description", "Adresse", "Ville", "Prix", "Vendeur"};
	    
    	table = new JTable(Bdd.getAnnonces(), headers);
    	
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
    	JPanel panel = new JPanel(new GridLayout(5,2,1,1));
    	   
    	panel.add(textPrixMin); 
    	panel.add(champsPrixMin); 
    	
    	panel.add(textPrixMax); 
    	panel.add(champsPrixMax); 

    	panel.add(textCategorie); 
    	panel.add(categories); 
    	
    	panel.add(textVille); 
    	panel.add(champsVille); 
  
    	panel.add(btnOk); 
    	
        getContentPane().add(panel, BorderLayout.EAST);
    	
    	this.pack();
    }
    
    public void initRecherche(){

	    String[] headers = {"Catégorie", "Description", "Adresse", "Ville", "Prix", "Vendeur"};
	    
    	table = new JTable(Bdd.recherche(champsPrixMin.getText(),champsPrixMax.getText(),categories.getSelectedItem().toString(),champsVille.getText()), headers);
    	
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
    	JPanel panel = new JPanel(new GridLayout(5,2,1,1));
    	   
    	panel.add(textPrixMin); 
    	panel.add(champsPrixMin); 
    	
    	panel.add(textPrixMax); 
    	panel.add(champsPrixMax); 

    	panel.add(textCategorie); 
    	panel.add(categories); 
    	
    	panel.add(textVille); 
    	panel.add(champsVille); 
  
    	panel.add(btnOk); 
    	
        getContentPane().add(panel, BorderLayout.EAST);
    	
    	this.pack();
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ok"){
			this.getContentPane().removeAll();
			this.getContentPane().revalidate();
			this.getContentPane().repaint();
			initRecherche();
		}
		
		
	}
		

}

