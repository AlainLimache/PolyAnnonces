package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Bdd;
import model.Utilisateur;

public class offres extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
	JButton btnAjouterAnnonce;	
	JButton btnActualiser;
	JButton btnRecherche;
	JButton btnOffres;
	String[] headers = {"Catégorie", "Description", "Prix initial", "Prix proposé", "Acheteur"};
	JTable table;
	DefaultTableModel tableModel;
	
	public offres(Utilisateur argUser) {
		this.setTitle("Mes offres");
		
    	initTableau();

    	JPanel panel = new JPanel(new GridLayout(1,2,1,1));
    	
    	this.getContentPane().add(panel);
    	
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null); 	
    
    	this.pack();
    	this.setVisible(true);  	
    }
    
	
    public void initTableau(){
	    
    	table = new JTable(new DefaultTableModel(Bdd.getOffres(), headers));
    	tableModel = (DefaultTableModel)table.getModel();
    	
    	this.getContentPane().setLayout(
    	        new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    	this.getContentPane().add(table.getTableHeader());
    	this.getContentPane().add(table);
    	
    	// listener
    	table.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	        int row = table.rowAtPoint(e.getPoint());
	        	new detailAnnonce(user,
	        			(String)table.getValueAt(row, 0),
	        			(String)table.getValueAt(row, 1),
	        			(String)table.getValueAt(row, 2), 
	        			(String)table.getValueAt(row, 3),
	        			(String)table.getValueAt(row, 4),
	        			(String)table.getValueAt(row, 5));
    	    }
    	});
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
