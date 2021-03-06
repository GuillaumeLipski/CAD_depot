package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import modele.BatailleNavale;
import modele.Modele;
import modele.Terrain;
import modele.bateau.Bateau;
import modele.bateau.Corvette;
import modele.bateau.Fregate;
import modele.bateau.Patrouilleur;
import modele.bateau.PorteAvion;
import modele.flotte.Flotte;
import stockage.DAO_Sauvegarde;
import stockage.GameFactory;

public class VuePrincipale extends JFrame{

	PanelPlateau plateauj1, plateauj2;
	PanelInformation infos;
	BatailleNavale bn;
	Modele m;
	int width = 10;
	int height = 10;
	
	
	public VuePrincipale()
	{

        super("Bataille Navale");
        
        setSize(new Dimension(1500, 500));
        setMinimumSize(new Dimension(1500, 500));
        setMinimumSize(new Dimension(1500, 500));
        
        m = new Modele(null);
        
		plateauj1 = new PanelPlateau(1,width,height);
		plateauj2 = new PanelPlateau(2,width,height);
		infos = new PanelInformation(m);
				
		this.setBackground(Color.BLACK);
		
		
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("Fichier");
		JMenuItem nouveau = new JMenuItem("Nouveau");
		VuePrincipale p = this;
		nouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NouveauNiveau nn = new NouveauNiveau(p);
			}
			

        });
		jm.add(nouveau);
		JMenuItem sauvegarde = new JMenuItem("Sauvegarder");
		sauvegarde.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DAO_Sauvegarde dao = GameFactory.getInstance().getDAO_Sauvegarde();
				dao.saveProfil(m);
			}
			

        });
		jm.add(sauvegarde);
		JMenuItem charger = new JMenuItem("Charger");
		sauvegarde.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DAO_Sauvegarde dao = GameFactory.getInstance().getDAO_Sauvegarde();
			}
			

        });
		jm.add(charger);
		jmb.add(jm);
		
		this.add(jmb, BorderLayout.PAGE_START);
		JPanel pp = new JPanel();
		pp.setLayout(new GridLayout());
		pp.add(plateauj1);
		pp.add(infos);
		pp.add(plateauj2);
		this.add(pp,  BorderLayout.CENTER);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setModele(m);
		m.setPlayer(1);
	}

	public void setModele(Modele modele) {
		m = modele;
		plateauj1.setModele(modele);
		plateauj2.setModele(modele);
		m.addObserver(plateauj1);
		m.addObserver(plateauj2);
		m.addObserver(infos);
	}
}
