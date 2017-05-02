package controle;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import graphique.Case;
import graphique.Case.State;
import graphique.PanelPlateau;
import modele.Modele;

public class CaseClick implements MouseListener {

	int status=0;
	int taille = 5;
	boolean direction = false;
	int height;
	int width;
	PanelPlateau plateau;
	boolean enable = true;
	int step = 0;
	Modele m;
	
	public CaseClick(PanelPlateau p, int w, int h)
	{
		plateau = p;
		width = w;
		height = h;
	}
	
	public void enable(boolean b) {enable = b;}
	
	public void setStep(int s) {step = s; if (s==1) setTaille(1);}
	
	public void setTaille(int t) { taille = t;}
	
	public void setModele(Modele mo) {m = mo;}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (enable)
		{
			switch (arg0.getButton())
			{
				case MouseEvent.BUTTON1:		
					Case caseliée = (Case) arg0.getSource();
					if (caseliée.getState() == Case.State.SELECT_OK)
					{
						int x = (int)caseliée.getCoords().getX();
						int y = (int)caseliée.getCoords().getY();
						State state = State.VIDE;
						if (!direction)
						{
							for (int i = 0; i<taille; i++)
							{
								plateau.setState(x+i, y, state);
							}
						} else 
						{
							for (int i = 0; i<taille; i++)
							{
								plateau.setState(x, y+i, state);
							}
						}
						m.donnerPosition(new Point(x,y), direction);
						this.mouseExited(arg0);
					}
					break;
				case MouseEvent.BUTTON2:
					this.mouseExited(arg0);
					direction = !direction;
					this.mouseEntered(arg0);
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (enable)
		{
			Case caseliée = (Case) arg0.getSource();
			int x = (int)caseliée.getCoords().getX();
			int y = (int)caseliée.getCoords().getY();
			State state = State.SELECT_OK;
			switch (step)
			{
			case 0: //Choix du placement d'un bateau
				if (!direction)
				{
					if (x+taille > width)
						state = State.SELECT_KO;
					else
					{
						boolean check = true;
						for (int i = 0; i<taille; i++)
						{
							check = check & (plateau.getState(x+i, y)==State.VIDE);
						}
						if (!check)
							state = State.SELECT_KO;
					}
					for (int i = 0; i<taille; i++)
					{
						plateau.setState(x+i, y, state);
					}
				} else 
				{
					if (y+taille > height)
						state = State.SELECT_KO;
					else
					{
						boolean check = true;
						for (int i = 0; i<taille; i++)
						{
							check = check & (plateau.getState(x, y+i)==State.VIDE);
						}
						if (!check)
							state = State.SELECT_KO;
					}
					for (int i = 0; i<taille; i++)
					{
						plateau.setState(x, y+i, state);
					}
				}
				break;
			case 1: //Choix d'une case à viser
				boolean check = true;
				State s = plateau.getState(x, y);
				if (s == State.VIDE || s == State.BATEAU_TO)
					state = State.SELECT_OK;
				else
					state = State.SELECT_KO;
				plateau.setState(x, y, state);
				break;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (enable)
		{
			Case caseliée = (Case) arg0.getSource();
			int x = (int)caseliée.getCoords().getX();
			int y = (int)caseliée.getCoords().getY();
			if (!direction)
			{
					for (int i = 0; i<taille; i++)
					{
						plateau.setLastState(x+i, y);
					}
			} else 
			{
					for (int i = 0; i<taille; i++)
					{
						plateau.setLastState(x, y+i);
					}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
