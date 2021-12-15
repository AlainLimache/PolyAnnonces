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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Connexion extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnOk;										
	JButton btnRetour;		
	JLabel textMail;
	JLabel textMotDePasse;
	JTextField champsMail;
	JPasswordField champsMotDePasse;
	
	
	public Connexion() {
	
		setTitle("Connexion");
				
		setSize(400, 160);
	    setLocationRelativeTo(null);
	   
	    textMail = new JLabel("Mail : ");
	    textMail.setHorizontalAlignment(JLabel.CENTER);
	    
	    textMotDePasse = new JLabel("Mot de passe : ");
	    textMotDePasse.setHorizontalAlignment(JLabel.CENTER);
		
	    champsMail = new JTextField();

	    champsMotDePasse = new JPasswordField();
		
	    btnOk = new JButton("Ok");
	    btnOk.addActionListener(this);
	    btnOk.setActionCommand("connexion"); 	
	    btnOk.setPreferredSize(new Dimension(180,45));
        
	    btnRetour = new JButton("Retour");
	    btnRetour.addActionListener(this);
	    btnRetour.setActionCommand("retour"); 
	    btnRetour.setPreferredSize(new Dimension(180,45));
        
    	JPanel panel = new JPanel(new GridLayout(3,2,1,1));
   
    	panel.add(textMail); 	
    	panel.add(champsMail); 	
    	
    	panel.add(textMotDePasse); 	
    	panel.add(champsMotDePasse); 	
    	
    	panel.add(btnOk); 	
    	panel.add(btnRetour); 	
    	
    	this.setContentPane(panel);
	    this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "connexion"){
			int idUser;
			idUser = DAO.Bdd.connexionCompte(champsMail.getText(),champsMotDePasse.getText());
			if(idUser != -1) {
				// si valide ouvre la vue annonce
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			// new Annonces();
		}
		
		if (e.getActionCommand() == "retour"){
			
			// Ferme la fenêtre
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
	}


}
