package Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Modele.BatailleNavale;

public class VuePrincipale extends JFrame implements Observer{

	PanelPlateau plateauj1, plateauj2;
	BatailleNavale bn;
	
	
	public VuePrincipale(BatailleNavale bn)
	{

        super("Projet Toucan - animation des algorithmes de tris");
        
        setSize(new Dimension(1500, 600));
        setMinimumSize(new Dimension(1500, 600));
        setMinimumSize(new Dimension(1500, 600));
        
		plateauj1 = new PanelPlateau(1);
		plateauj2 = new PanelPlateau(2);
				
		this.setBackground(Color.BLACK);
		
		
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("Fichier");
		JMenuItem nouveau = new JMenuItem("Nouveau");
		nouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NouveauNiveau nn = new NouveauNiveau(null, bn);
				
			}
			

        });
		jm.add(nouveau);
		JMenuItem sauvegarde = new JMenuItem("Sauvegarder");
		jm.add(sauvegarde);
		JMenuItem charger = new JMenuItem("Charger");
		jm.add(charger);
		jmb.add(jm);
		
		this.add(jmb, BorderLayout.PAGE_START);
		JPanel pp = new JPanel();
		pp.setLayout(new GridLayout(1,3,1,1));
		pp.add(plateauj1);
		pp.add(plateauj2);
		this.add(pp,  BorderLayout.CENTER);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void main(String[] args)
	{
		VuePrincipale v = new VuePrincipale(null);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
