package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.Modele;
import modele.bateau.Bateau;
import modele.bateau.Corvette;

public class PanelInformation extends JPanel  implements Observer {
	
	ListeBateau lj1, lj2;
	JTextField nj1, nj2, nt1, nt2, ntr1, ntr2,step;
	Modele m;
	
	public PanelInformation(Modele mo)
	{
		super();
		m = mo;
		this.setBackground(Color.WHITE);
		this.setMinimumSize(new Dimension(1000,1000));
		this.setLayout(new GridLayout(2,1));
		nj1 = new JTextField("Joueur 1");
		nj1.setEnabled(false);
		nj1.setHorizontalAlignment(JLabel.CENTER);
		JLabel labeljoueur = new JLabel("Tour de ");
		labeljoueur.setHorizontalAlignment(JLabel.CENTER);
		nj2 = new JTextField("Joueur 2");
		nj2.setEnabled(false);
		nj2.setHorizontalAlignment(JLabel.CENTER);

		nt1 = new JTextField("0");
		nt1.setEnabled(false);
		nt1.setHorizontalAlignment(JLabel.CENTER);
		JLabel labeltire = new JLabel("Tirs effectués");
		labeltire.setHorizontalAlignment(JLabel.CENTER);
		nt2 = new JTextField("0");
		nt2.setEnabled(false);
		nt2.setHorizontalAlignment(JLabel.CENTER);
		
		ntr1 = new JTextField("0");
		ntr1.setEnabled(false);
		ntr1.setHorizontalAlignment(JLabel.CENTER);
		JLabel labeltirr = new JLabel("Tirs restants");
		labeltirr.setHorizontalAlignment(JLabel.CENTER);
		ntr2 = new JTextField("0");
		ntr2.setEnabled(false);
		ntr2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel jle = new JLabel("Etape");
		jle.setHorizontalAlignment(JLabel.CENTER);
		step = new JTextField("WAIT");
		step.setHorizontalAlignment(JLabel.CENTER);
		step.enable(false);
		
		JPanel pinfo = new JPanel();
		pinfo.setLayout(new GridLayout(5,3));
		pinfo.add(nj1);
		pinfo.add(labeljoueur);
		pinfo.add(nj2);
		pinfo.add(nt1);
		pinfo.add(labeltire);
		pinfo.add(nt2);
		pinfo.add(ntr1);
		pinfo.add(labeltirr);
		pinfo.add(ntr2);
		pinfo.add(new JLabel());
		pinfo.add(jle);
		pinfo.add(new JLabel());
		pinfo.add(new JLabel());
		pinfo.add(step);
		pinfo.add(new JLabel());
		this.add(pinfo);
		
		JPanel pliste = new JPanel();
		pliste.setLayout(new BorderLayout());
		JLabel labelliste = new JLabel("Bateaux restants");
		labelliste.setHorizontalAlignment(JLabel.CENTER);
		lj1 = new ListeBateau();
		lj1.setCellRenderer(new ListeBateauComponent());
		lj2 = new ListeBateau();
		lj2.setCellRenderer(new ListeBateauComponent());
		pliste.add(labelliste, BorderLayout.PAGE_START);
		pliste.add(lj1, BorderLayout.LINE_START);
		pliste.add(lj2, BorderLayout.LINE_END);
		this.add(pliste);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Modele m = (Modele)arg0;
		if (m != null && m.getTerrain()!=null)
		{
			lj1.setListData(m.getTerrain().getJ1().getListeBateau());
			lj2.setListData(m.getTerrain().getJ2().getListeBateau());
			if (m.getPlayerTurn() == 1)
			{
				nj1.setBackground(new Color(10,135,10));
				nj2.setBackground(Color.BLACK);
			} else {
				nj2.setBackground(new Color(10,135,10));
				nj1.setBackground(Color.BLACK);
			}
			switch (m.getStep())
			{
				case 0:
					step.setText("Placer votre bateau");
					break;
				case 1:
					step.setText("Tirer");
					break;
				case 2:
					step.setText("Choisir un bateau");
					break;
			}
		}
	}
}
