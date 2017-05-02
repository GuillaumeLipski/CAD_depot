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
import modele.epoque.Epoque;
import modele.strategie.Strategie;
import modele.tir.Tir;

public class NouveauNiveau extends JDialog {
	JComboBox epoque;
	JComboBox modedetir;
	JComboBox stratJ1;
	JComboBox stratJ2;
	VuePrincipale vp;
	
	public NouveauNiveau(VuePrincipale jf)
	{
        super(jf, "Choix du nouveau niveau");
        vp = jf;
        
        setSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 300));
        setMinimumSize(new Dimension(600, 300));
        
        BatailleNavale bn = BatailleNavale.getInstance();
        Object[] epoques = bn.getEpoques();
        Object[] modedetirs = bn.getModeDeTirs();
        Object[] strats = bn.getStrategies();
        JLabel lep = new JLabel("Epoque :");
        JLabel lmdt = new JLabel("Mode de Jeu :");
        JLabel ls1 = new JLabel("Joueur 1 :");
        JLabel ls2 = new JLabel("Joueur 2 :");
        JLabel vide = new JLabel("");
        epoque = new JComboBox(epoques);
        modedetir = new JComboBox(modedetirs);
        stratJ1 = new JComboBox(strats);
        stratJ2 = new JComboBox(strats);
        JPanel boutons = new JPanel();
        NouveauNiveau nn = this;
        JButton ok = new JButton("Valider");
        ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Modele m = new Modele((Tir)modedetir.getSelectedItem(), (Strategie)stratJ1.getSelectedItem(), (Strategie)stratJ2.getSelectedItem(), (Epoque)epoque.getSelectedItem());
				vp.setModele(m);
				Thread t = new Thread(m);
				t.start();
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
