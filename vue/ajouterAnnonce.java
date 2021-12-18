package vue;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Utilisateur;

public class ajouterAnnonce extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
	JButton btnAjouter;										
	JButton btnAnnuler;		
	JLabel textCategorie;
	JLabel textPrix;
	JLabel textDescription;
	JLabel textAdresse;
	JLabel textVille;
	JTextField champsCategorie;
	JTextField champsPrix;
	JTextField champsDescription;
	JTextField champsAdresse;
	JTextField champsVille;
	JComboBox<String> categories;
	JFrame f;
	
	public ajouterAnnonce(Utilisateur argUser,JFrame fenetre) {
	
		setTitle("Ajouter une annonce");
				
		setSize(500, 250);
	    setLocationRelativeTo(null);
	    
	    this.setUser(argUser);
	    f = fenetre;
	    textCategorie = new JLabel("Catégorie : ");
	    textCategorie.setHorizontalAlignment(JLabel.CENTER);
	    
	    textPrix = new JLabel("Prix : ");
	    textPrix.setHorizontalAlignment(JLabel.CENTER);
	    
	    textDescription = new JLabel("Description : ");
	    textDescription.setHorizontalAlignment(JLabel.CENTER); 

	    textAdresse = new JLabel("Adresse : ");
	    textAdresse.setHorizontalAlignment(JLabel.CENTER);
	    
	    textVille = new JLabel("Ville : ");
	    textVille.setHorizontalAlignment(JLabel.CENTER); 
	    
	    champsPrix = new JTextField();
	    champsDescription = new JTextField();
	    champsAdresse = new JTextField();
	    champsVille = new JTextField();
	    
	    categories = new JComboBox<String>();
	    categories.addItem("Maison");
	    categories.addItem("Appartement");
	    categories.addItem("autre");  
	    	    	    
	    btnAjouter = new JButton("Ajouter");
	    btnAjouter.addActionListener(this);
	    btnAjouter.setActionCommand("ajouter"); 	
	    btnAjouter.setPreferredSize(new Dimension(180,45));
        
	    btnAnnuler = new JButton("Annuler");
	    btnAnnuler.addActionListener(this);
	    btnAnnuler.setActionCommand("annuler"); 
	    btnAnnuler.setPreferredSize(new Dimension(180,45));
        
    	JPanel panel = new JPanel(new GridLayout(6,2,1,1));
   
    	panel.add(textCategorie); 
    	panel.add(categories); 
    	
    	panel.add(textPrix); 
    	panel.add(champsPrix); 

    	panel.add(textDescription); 
    	panel.add(champsDescription); 
    	
    	panel.add(textAdresse); 
    	panel.add(champsAdresse); 
    	
    	panel.add(textVille); 
    	panel.add(champsVille); 
    	
    	panel.add(btnAjouter); 
    	panel.add(btnAnnuler); 
    	
    	getRootPane().setDefaultButton(btnAjouter);
    	 
    	this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ajouter"){
			
			 boolean requeteOK;
			 requeteOK = DAO.Bdd.ajouterAnnonce(champsPrix.getText(),champsDescription.getText(),categories.getSelectedItem().toString(),this.getUser().getId(), champsAdresse.getText(), champsVille.getText());
			 
			 if(requeteOK) {
				//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				
				// Fenêtre d'alerte
				String message = "Annonce ajouté !";
				JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
				model.Alerte.fenetreDialogue(lbmsg, "Ajout annonce", 2 * 1000);
				
			 }
		}
		
		if (e.getActionCommand() == "annuler"){
			// Ferme la fenêtre
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


}
