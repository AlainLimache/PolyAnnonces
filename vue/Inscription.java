package vue;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Inscription extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnOk;										
	JButton btnRetour;		
	JLabel textNom;
	JLabel textPrenom;
	JLabel textMail;
	JLabel textMotDePasse;
	JLabel textAdresse;
	JLabel textVille;
	JTextField champsNom;
	JTextField champsPrenom;
	JTextField champsMail;
	JTextField champsMotDePasse;
	JTextField champsAdresse;
	JTextField champsVille;
	
	
	public Inscription() {
	
		setTitle("Inscription");
				
		setSize(800, 300);
	    setLocationRelativeTo(null);
	    
	    textNom = new JLabel("Nom : ");
	    textNom.setHorizontalAlignment(JLabel.CENTER);
	    
	    textPrenom = new JLabel("Prénom : ");
	    textPrenom.setHorizontalAlignment(JLabel.CENTER);
	    
	    textMail = new JLabel("Mail : ");
	    textMail.setHorizontalAlignment(JLabel.CENTER);
	    
	    textMotDePasse = new JLabel("Mot de passe : ");
	    textMotDePasse.setHorizontalAlignment(JLabel.CENTER);
	    
	    textAdresse = new JLabel("Adresse : ");
	    textAdresse.setHorizontalAlignment(JLabel.CENTER);
	    
	    textVille = new JLabel("Ville : ");
	    textVille.setHorizontalAlignment(JLabel.CENTER);
	    
	    champsNom = new JTextField();
	    champsPrenom = new JTextField();
	    champsMail = new JTextField();
	    champsMotDePasse = new JTextField();
	    champsAdresse = new JTextField();
	    champsVille = new JTextField();
	    
	    btnOk = new JButton("Ok");
	    btnOk.addActionListener(this);
	    btnOk.setActionCommand("inscription"); 	
	    btnOk.setPreferredSize(new Dimension(180,45));
        
	    btnRetour = new JButton("Retour");
	    btnRetour.addActionListener(this);
	    btnRetour.setActionCommand("retour"); 
	    btnRetour.setPreferredSize(new Dimension(180,45));
        
    	JPanel panel = new JPanel(new GridLayout(7,2,1,1));
   
    	panel.add(textNom); 
    	panel.add(champsNom); 
    	
    	panel.add(textPrenom); 
    	panel.add(champsPrenom); 

    	panel.add(textMail); 
    	panel.add(champsMail); 
    	
    	panel.add(textMotDePasse); 
    	panel.add(champsMotDePasse); 
    	
    	panel.add(textAdresse); 
    	panel.add(champsAdresse); 
    	
    	panel.add(textVille); 
    	panel.add(champsVille); 
  
    	panel.add(btnOk); 
    	panel.add(btnRetour); 
    	
    	getRootPane().setDefaultButton(btnOk);
    	
    	this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "inscription"){
			
			 boolean requeteOK;
			 requeteOK = DAO.Bdd.creationCompte(champsNom.getText(),champsPrenom.getText(),champsMail.getText(),champsMotDePasse.getText(),champsAdresse.getText(),champsVille.getText());
			 
			 if(requeteOK) {
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				
				// Fenêtre d'alerte
				String message = "Inscription réussit !";
				JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
				model.Alerte.fenetreDialogue(lbmsg, "Inscription", 2 * 1000);
			 }
		}
		
		if (e.getActionCommand() == "retour"){
			// Ferme la fenêtre
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
	}


}
