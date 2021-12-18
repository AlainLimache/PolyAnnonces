package vue;

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

import model.Annonce;
import model.Utilisateur;

public class offre extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnOffre;										
	JButton btnRetour;		
	JLabel lbl1;
	JLabel lbl2;
	JTextField txt1;
	JTextField txt2;
	JFrame fenetreMenu;
	private Utilisateur user;
	private Annonce annonce;
	
	public offre(String prix, Utilisateur user, Annonce annonce){
	
		setTitle("Faire une offre");
				
		setSize(400, 160);
	    setLocationRelativeTo(null);
	    
	    
	    lbl1 = new JLabel("Prix proposé : ");
	    lbl1.setHorizontalAlignment(JLabel.CENTER);
	    
	    lbl2 = new JLabel("Message : ");
	    lbl2.setHorizontalAlignment(JLabel.CENTER);
		
	    txt1 = new JTextField(prix);

	    txt2 = new JTextField("Ecrivez au vendeur");
		
	    btnOffre = new JButton("Faire une offre");
	    btnOffre.addActionListener(this);
	    btnOffre.setActionCommand("offre"); 	
        
	    btnRetour = new JButton("Retour");
	    btnRetour.addActionListener(this);
	    btnRetour.setActionCommand("retour"); 
        
    	JPanel panel = new JPanel(new GridLayout(3,2,1,1));
   
    	panel.add(lbl1); 	
    	panel.add(txt1); 	
    	
    	panel.add(lbl2); 	
    	panel.add(txt2); 	
    	
    	panel.add(btnOffre); 	
    	panel.add(btnRetour); 	
    	
    	this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "offre"){
			boolean requeteOK;
			 requeteOK = DAO.Bdd.ajouterOffre(txt1.getText(),txt2.getText(), this.getAnnonce().getId(), this.getUser().getId());
			 
			 if(requeteOK) {
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				
				// Fenêtre d'alerte
				String message = "Offre envoyée !";
				JLabel lbmsg = new JLabel(message, SwingConstants.CENTER);
				model.Alerte.fenetreDialogue(lbmsg, "Ajout offre", 2 * 1000);
				//tableModel.fireTableDataChanged();
	
			 }
		}
		
		if (e.getActionCommand() == "retour"){
			
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
	
	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
		
}
