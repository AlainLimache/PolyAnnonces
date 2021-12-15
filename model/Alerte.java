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
	  * M�thode qui permet de r�cup�rer le texte du champs principal de l'interface
	  * @param params - objet / composant 
	  * @param title - chaine de caract�re correspondant au titre de la fen�tre
	  * @param timeout_ms - temps compte � rebours
	  */
	public final static void fenetreDialogue(Object params, String title, int timeout_ms) {
	    final JOptionPane msg = new JOptionPane(params);
	    final JDialog dlg = msg.createDialog(title);

	    dlg.setAlwaysOnTop(true);
	    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    
	    // timer de la fen�tre de dialogue
	    dlg.addComponentListener(new ComponentAdapter() {
	    	
	      /**
	   	  * M�thode qui permet d'initiliser et commencer le timer
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
