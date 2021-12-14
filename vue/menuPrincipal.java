package vue;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class menuPrincipal extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btn1;										
	JButton btn2;								
	JButton btn3;										
	
	public menuPrincipal() {
	
		setTitle("Menu Principal");
				
		setSize(300, 200);
	    setLocationRelativeTo(null);
	    
        btn1 = new JButton("Inscription");
        btn1.addActionListener(this);
        btn1.setActionCommand("inscription"); 	
        btn1.setPreferredSize(new Dimension(180,45));
        
        btn2 = new JButton("Connexion");
        btn2.addActionListener(this);
        btn2.setActionCommand("connexion"); 
        btn2.setPreferredSize(new Dimension(180,45));
        
        btn3 = new JButton("Afficher les annonces");
        btn3.addActionListener(this);
        btn3.setActionCommand("afficher"); 		
        btn3.setPreferredSize(new Dimension(180,45));
        
    	JPanel panel = new JPanel();
   
    	panel.add(btn1); 	
    	
    	panel.add(btn2); 	
    	
    	panel.add(btn3); 
    	
    	this.setContentPane(panel);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "inscription"){
			new Inscription();
		}
		
		if (e.getActionCommand() == "connexion"){
			new Connexion();
		}
		
		if (e.getActionCommand() == "afficher"){

		}
	}


}
