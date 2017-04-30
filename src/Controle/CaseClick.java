package Controle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Graphique.Case;
import Graphique.Case.State;
import Graphique.PanelPlateau;

public class CaseClick implements MouseListener {

	int status=0;
	int taille = 5;
	int direction = 0;
	PanelPlateau plateau;
	
	public CaseClick(PanelPlateau p)
	{
		plateau = p;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		switch (arg0.getButton())
		{
			case MouseEvent.BUTTON1:		
				Case caseliée = (Case) arg0.getSource();
				int x = (int)caseliée.getCoords().getX();
				int y = (int)caseliée.getCoords().getY();
				State state = State.BATEAU_OK;
				if (direction ==0)
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
				this.mouseEntered(arg0);
				break;
			case MouseEvent.BUTTON2:
				this.mouseExited(arg0);
				direction = 1 - direction;
				this.mouseEntered(arg0);
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		Case caseliée = (Case) arg0.getSource();
		int x = (int)caseliée.getCoords().getX();
		int y = (int)caseliée.getCoords().getY();
		State state = State.SELECT_OK;
		if (direction ==0)
		{
			if (x+taille > 10)
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
			if (y+taille > 10)
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
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		Case caseliée = (Case) arg0.getSource();
		int x = (int)caseliée.getCoords().getX();
		int y = (int)caseliée.getCoords().getY();
		if (direction ==0)
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
