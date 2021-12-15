package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.Bdd;   

public class Annonces {
	
    public Annonces() {    
    	JFrame frame = new JFrame();
	    frame.setLocationRelativeTo(null);
	    String[] headers = {"Catégorie", "Description", "Adresse", "Ville", "Prix", "Vendeur"};
    	final JTable table = new JTable(new DefaultTableModel(Bdd.getAnnonces(), headers));
    	frame.getContentPane().setLayout(
    	        new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    	frame.getContentPane().add(table.getTableHeader());
    	frame.getContentPane().add(table);
    	frame.pack();
    	frame.setVisible(true);

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
}
