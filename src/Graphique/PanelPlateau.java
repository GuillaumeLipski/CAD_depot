package Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import Controle.CaseClick;
import Graphique.Case.State;

public class PanelPlateau extends JPanel {

	int n_joueur;
	Case[][] plateau;
	CaseClick controleur;
	char[] lettre = {' ','A','B','C','D','E','F','G','H','I','J'};
	
	public PanelPlateau(int n)

	{
		super();
		n_joueur = n;
		this.setBackground(Color.BLACK);
		this.setMinimumSize(new Dimension(600,600));
		this.setSize(600,600);
		this.setMaximumSize(new Dimension(600,600));
		this.setLayout(new GridLayout(11,11,2,2));
		plateau = new Case[10][10];
		controleur = new CaseClick(this);
		for (int i = 10; i >= 0; i--){
			for(int j = -1; j < 10; j++)
			{
				Case jpp = new Case(j,i);
				jpp.setSize(new Dimension(60,60));
				if (j == -1)
				{
					JTextPane jtp = new JTextPane();
					jtp.setText(lettre[10-i]+"");
					jtp.setEditable(false);
					jpp.add(jtp);
					jpp.setBackground(Color.WHITE);
					jpp.setState(State.PLOUF);
				}
				else if (i == 10) {
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
		if (x < 10 && y < 10)
			plateau[y][x].setState(state);
	}
	
	public void setLastState(int x, int y)
	{
		if (x < 10 && y < 10)
			plateau[y][x].setLastState();
	}
}

