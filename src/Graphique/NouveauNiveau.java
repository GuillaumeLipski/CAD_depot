package graphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.BatailleNavale;
import modele.Modele;
import modele.Terrain;
import modele.strategie.Strategie;
import modele.tir.Tir;

public class NouveauNiveau extends JDialog {
	public NouveauNiveau(VuePrincipale jf, BatailleNavale bn)
	{
        super(jf, "Choix du nouveau niveau");
        
        setSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 300));
        setMinimumSize(new Dimension(600, 300));
        
        Object[] epoques = new Object[]{"1700","1800","1900","2000"};
        Object[] modedetirs = new Object[]{"Classique","Limité"};
        Object[] strats = new Object[]{"Humain","IA_Aleatoire","IA_Quadrillage","IA_MCTS"};
        JLabel lep = new JLabel("Epoque :");
        JLabel lmdt = new JLabel("Mode de Jeu :");
        JLabel ls1 = new JLabel("Joueur 1 :");
        JLabel ls2 = new JLabel("Joueur 2 :");
        JLabel vide = new JLabel("");
        JComboBox epoque = new JComboBox(epoques);
        JComboBox modedetir = new JComboBox(modedetirs);
        JComboBox stratJ1 = new JComboBox(strats);
        JComboBox stratJ2 = new JComboBox(strats);
        JPanel boutons = new JPanel();
        NouveauNiveau nn = this;
        JButton ok = new JButton("Valider");
        ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setModele(new Modele((Tir)modedetir.getSelectedItem(), (Strategie)stratJ1.getSelectedItem(), (Strategie)stratJ2.getSelectedItem(), (Terrain)epoque.getSelectedItem()));
				nn.dispose();
			}
			

        });
        JButton ko = new JButton("Annuler");
        ko.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				nn.dispose();
			}
			

        });
        this.setLayout(new GridLayout(6,2,10,10));
        this.add(lep);
        this.add(epoque);
        this.add(lmdt);
        this.add(modedetir);
        this.add(ls1);
        this.add(stratJ1);
        this.add(ls2);
        this.add(stratJ2);
        this.add(vide);
        boutons.add(ok);
        boutons.add(ko);
		this.add(boutons);
        
        pack();
		setVisible(true);
	}
}
