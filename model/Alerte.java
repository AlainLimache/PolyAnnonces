package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Alerte {

	/**
	  * Méthode qui permet de récupèrer le texte du champs principal de l'interface
	  * @param params - objet / composant 
	  * @param title - chaine de caractère correspondant au titre de la fenêtre
	  * @param timeout_ms - temps compte à rebours
	  */
	public final static void fenetreDialogue(Object params, String title, int timeout_ms) {
	    final JOptionPane msg = new JOptionPane(params);
	    final JDialog dlg = msg.createDialog(title);

	    dlg.setAlwaysOnTop(true);
	    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    
	    // timer de la fenêtre de dialogue
	    dlg.addComponentListener(new ComponentAdapter() {
	    	
	      /**
	   	  * Méthode qui permet d'initiliser et commencer le timer
	   	  * @param e - evenement du composant
	   	  */
	        @Override
	        public void componentShown(ComponentEvent e) {
	        	
	            super.componentShown(e);
	            
	            final Timer t = new Timer(timeout_ms, new ActionListener() {
	            	
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    dlg.setVisible(false);
	                }

	            });
	            
	            t.start();
	        }
	    });
	    
	    dlg.setVisible(true);

	}
}
