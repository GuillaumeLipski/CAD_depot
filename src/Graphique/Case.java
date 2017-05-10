package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Case extends JPanel {
	private Point p;
	Image image_bateau;

	public enum State {
		VIDE,
		BATEAU_OK,
		BATEAU_TO,
		BATEAU_TT,
		BATEAU_KO,
		PLOUF,
		SELECT_OK,
		SELECT_KO
	}
	
	private State state = State.VIDE;
	private State last_state = State.VIDE;
	private boolean visible = false;
	
	/*
	 * Etats:
	 * 0 - Vide
	 * 1 - Case Bateau Valide 
	 * 2 - Case Bateau Touche
	 * 3 - Case Bateau Coule
	 * 4 - Case Boulet Dans l'Eau
	 * 5 - Case Selection Possible
	 * 6 - Case Selection Non Possible
	 */
	
	public Case(int x, int y)
	{
		super();
		p = new Point(x,y);
	}
	
	public void paintComponent(Graphics g) {
          super.paintComponent(g);
          if (image_bateau != null && visible)
          {
	          int height = this.getSize().height;
	          int width = this.getSize().width;  
	          g.drawImage(image_bateau,0,0,width, height, this);
          }
    } 
	
	public Point getCoords()
	{
		return p;
	}
	
	public void setImage(Image i)
	{
		image_bateau = i;
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
				this.setBackground(Color.LIGHT_GRAY);
				break;
			case BATEAU_TO:
				this.setBackground(Color.YELLOW);
				break;
			case BATEAU_TT:
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
				this.setBackground(Color.DARK_GRAY);
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
				{
					visible = true;
					setState(State.BATEAU_OK);
				}
				else 
				{
					visible = false;
					setState(State.VIDE);
				}
				break;
			case 2: 
				if (player)
				{visible = true;
				}
				else 
				{
					visible = false;
				}
				setState(State.BATEAU_TO);
				break;
			case 3: 
				if (player)
				{
					visible = true;
				}
				else 
				{
					visible = false;
				}
				setState(State.BATEAU_TT);
				break;
			case 4: 
				visible = true;
				setState(State.BATEAU_KO);
				break;
			case 5: 
				setState(State.PLOUF);
				break;
		}
		paintComponent(this.getGraphics());
	}
}
