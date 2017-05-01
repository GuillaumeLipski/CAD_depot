package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import controle.CaseClick;
import graphique.Case.State;
import modele.Modele;
import modele.Terrain;

public class PanelPlateau extends JPanel  implements Observer{

	int n_joueur;
	Case[][] plateau;
	int height;
	int width;
	CaseClick controleur;
	
	public PanelPlateau(int n, int h, int w)

	{
		super();
		n_joueur = n;
		this.setBackground(Color.BLACK);
		this.setMinimumSize(new Dimension(1000,1000));
		this.setSize(1000,1000);
		this.setMaximumSize(new Dimension(1000,1000));
		this.setLayout(new GridLayout(w+1,h+1,2,2));
		plateau = new Case[h][w];
		width = w;
		height = h;
		controleur = new CaseClick(this, w, h);
		for (int i = h; i >= 0; i--){
			for(int j = -1; j < w; j++)
			{
				Case jpp = new Case(j,i);
				jpp.setSize(new Dimension(100,100));
				if (j == -1)
				{
					JTextPane jtp = new JTextPane();
					if (i == h)
						jtp.setText(" ");
					else
						jtp.setText(""+(char)(('A'-1)+(h-i)));
					jtp.setEditable(false);
					jpp.add(jtp);
					jpp.setBackground(Color.WHITE);
					jpp.setState(State.PLOUF);
				}
				else if (i == h) {
					JTextPane jtp = new JTextPane();
					jtp.setText((j+1)+"");
					jtp.setEditable(false);
					jpp.add(jtp);
					jpp.setBackground(Color.WHITE);
					jpp.setState(State.PLOUF);
				} else {
					jpp.addMouseListener(controleur);
					plateau[i][j] = jpp;
					jpp.setBackground(Color.BLUE);
				}
				this.add(jpp);
			}
		}
	}

	public State getState(int x, int y)
	{
		return plateau[y][x].getState();
	}
	
	public void setState(int x, int y, State state)
	{
		if (x < width && y < height)
			plateau[y][x].setState(state);
	}
	
	public void setLastState(int x, int y)
	{
		if (x < width && y < height)
			plateau[y][x].setLastState();
	}

	@Override
	public void update(Observable o, Object arg) {
		Modele m = (Modele)o;
		int[][] p = m.getTerrain().getPlateau(n_joueur);
		boolean enable = (n_joueur == m.getPlayerTurn());
		int step = m.getStep();
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				plateau[y][x].setState(p[y][x], enable);
			}
		}
		if (step != 0)
			enable = !enable;
		controleur.enable(enable);
		controleur.setStep(step);
		controleur.setTaille(m.getTaille());
	}

	public void setModele(Modele modele) {
		controleur.setModele(modele);
	}
}

