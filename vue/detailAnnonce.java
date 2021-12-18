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

import model.Annonce;
import model.Utilisateur;

public class detailAnnonce extends JFrame implements ActionListener{
	private Utilisateur user;
	private Annonce annonce;
	JTextField txt5;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public detailAnnonce(Utilisateur argUser, String cat, String desc, String adr, String ville, String prix, String vend) {
		
		setUser(argUser);
		
		setTitle("Offre de " + vend);
		
		setSize(600, 400);
	    setLocationRelativeTo(null);
	    	    
	    JLabel lbl1 = new JLabel("Catégorie");
	    lbl1.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel lbl2 = new JLabel("Description : ");
	    lbl2.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel lbl3 = new JLabel("Adresse : ");
	    lbl3.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel lbl4 = new JLabel("Ville : ");
	    lbl4.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel lbl5 = new JLabel("Prix : ");
	    lbl5.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel lbl6 = new JLabel("Mail : ");
	    lbl6.setHorizontalAlignment(JLabel.CENTER);
	    
	    
        JTextField txt1 = new JTextField(cat);
        txt1.setEditable(false);
        txt1.setBorder(null);
        
        JTextField txt2 = new JTextField(desc);
        txt2.setEditable(false);
        txt2.setBorder(null);
        
        JTextField txt3 = new JTextField(adr);
        txt3.setEditable(false);
        txt3.setBorder(null);
        
        JTextField txt4 = new JTextField(ville);
        txt4.setEditable(false);
        txt4.setBorder(null);
        
        txt5 = new JTextField((String)prix);
        txt5.setEditable(false);
        txt5.setBorder(null);
        
        JTextField txt6 = new JTextField(vend);
        txt6.setEditable(false);
        txt6.setBorder(null);
        
        JButton btnRetour = new JButton("Retour");
	    btnRetour.addActionListener(this);
	    btnRetour.setActionCommand("retour"); 
	    
        JPanel panel = new JPanel(new GridLayout(7,2,1,1));
        panel.add(lbl1); panel.add(txt1);
    	panel.add(lbl2); panel.add(txt2);
    	panel.add(lbl3); panel.add(txt3);
    	panel.add(lbl4); panel.add(txt4);
    	panel.add(lbl5); panel.add(txt5);
    	panel.add(lbl6); panel.add(txt6);
	    panel.add(btnRetour);
	    
	    //int userAnnonceId = DAO.Bdd.getUserAnnonceId(cat, desc, adr, ville, prix, vend);
	    
        if(this.getUser().getId() != 0) {
			JButton btnOffre = new JButton("Faire une offre");
	        btnOffre.addActionListener(this);
	        btnOffre.setActionCommand("offre"); 	
	        
		    panel.add(btnOffre);
		}
        
    	this.setContentPane(panel);
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "retour"){
			// Ferme la fenêtre
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		if (e.getActionCommand() == "offre"){
			new offre(txt5.getText(), this.getUser(), this.getAnnonce());
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
