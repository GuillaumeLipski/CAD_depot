package Graphique;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

public class Case extends JPanel {
	private Point p;
	

	public enum State {
		VIDE,
		BATEAU_OK,
		BATEAU_TO,
		BATEAU_KO,
		PLOUF,
		SELECT_OK,
		SELECT_KO
	}
	
	private State state = State.VIDE;
	private State last_state = State.VIDE;
	
	/*
	 * Etats:
	 * 0 - Vide
	 * 1 - Case Bateau Valide 
	 * 2 - Case Bateau Touch�
	 * 3 - Case Bateau Coul�
	 * 4 - Case Boulet Dans l'Eau
	 * 5 - Case S�lection Possible
	 * 6 - Case S�lection Non Possible
	 */
	
	public Case(int x, int y)
	{
		super();
		p = new Point(x,y);
	}
	
	public Point getCoords()
	{
		return p;
	}
	
	public State getState() {return state;}
	
	public State getLastState() {return last_state;}

	public void setLastState(){
		this.setState(last_state);
	}
	public void setState(State x) {
		last_state = state;
		state = x;
		switch (state)
		{
			case VIDE:
				this.setBackground(Color.BLUE);
				break;
			case BATEAU_OK:
				this.setBackground(Color.GRAY);
				break;
			case BATEAU_TO:
				this.setBackground(Color.ORANGE);
				break;
			case BATEAU_KO:
				this.setBackground(Color.RED);
				break;
			case PLOUF:
				this.setBackground(Color.WHITE);
				break;
			case SELECT_OK:
				this.setBackground(Color.GREEN);
				break;
			case SELECT_KO:
				this.setBackground(Color.RED);
				break;
		}
	}
}
