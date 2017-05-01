package graphique;

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
	 * 2 - Case Bateau Touché
	 * 3 - Case Bateau Coulé
	 * 4 - Case Boulet Dans l'Eau
	 * 5 - Case Sélection Possible
	 * 6 - Case Sélection Non Possible
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

	public void setState(int i, boolean player) {
		switch (i)
		{
			case 0: 
				setState(State.VIDE);
				break;
			case 1: 
				if (player)
					setState(State.BATEAU_OK);
				else 
					setState(State.VIDE);
				break;
			case 2: 
				setState(State.BATEAU_TO);
				break;
			case 3: 
				setState(State.BATEAU_KO);
				break;
			case 4: 
				setState(State.PLOUF);
				break;
			case 5: 
				setState(State.SELECT_OK);
				break;
			case 6: 
				setState(State.SELECT_KO);
				break;
		}
	}
}
