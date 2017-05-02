package modele;

import java.awt.Point;

import modele.bateau.Bateau;
import modele.flotte.Flotte;

public class Terrain {

	private Flotte[] flottes;
	
	private int hauteur;
	
	private int largeur;
	
	private int[][][] plateau;
	
	private int VID = 0, BOK = 1, BTO = 2, BTT = 3, BKO = 4, PLF = 5;
	
	public Terrain(int h, int l,Flotte f1,Flotte f2){
		hauteur=h;
		largeur=l;
		plateau = new int[2][h][l];
		flottes = new Flotte[2];
		flottes[0]=f1;
		flottes[1]=f2;
		/*for (int i = 0; i < flottes[0].getNbBateau(); i++)
		{
			Bateau b1 = flottes[0].getBateau(i);
			if (b1 != null)
			{
				Point p = b1.getPosition();
				for (int k = 0; k < b1.getTaille(); k++)
				{
					if (b1.getDirection())
					{
						plateau[0][p.y][p.x + k] = BOK;
					}
					else 
					{
						plateau[0][p.y + k][p.x] = BOK;
					}
				}
			}
			Bateau b2 = flottes[1].getBateau(i);
			if (b2 != null)
			{
				Point p = b2.getPosition();
				for (int k = 0; k < b2.getTaille(); k++)
				{
					if (b2.getDirection())
					{
						plateau[1][p.y][p.x + k] = BOK;
					}
					else 
					{
						plateau[1][p.y + k][p.x] = BOK;
					}
				}
			}
		}*/
	}

	public Flotte getJ1() {
		return flottes[0];
	}

	public Flotte getJ2() {
		return flottes[1];
	}
	
	public Flotte getFlotte(int i)
	{
		return flottes[i-1];
	}
	
	public int[][] getPlateau(int i)
	{
		return plateau[i-1];
	}
	
	public boolean estTermine()
	{
		if (flottes[0].estDetruite())
			return true;
		if (flottes[1].estDetruite())
			return true;
		return false;
	}
	
	public void updateTir(int i, Point p, int puissance)
	{
		int j = 1 - (i-1);
		if (plateau[j][p.y][p.x] == 0)
		{
			plateau[j][p.y][p.x] = 5;
		} else {
			Bateau b;
			if (puissance != 0)
				b = flottes[j].testerTir(p, puissance);
			else
				b = flottes[j].testerTir(p);
			Point pos = b.getPosition();
			if (b.estDetruit()) 
			{
				for (int k = 0; k < b.getTaille(); k++)
				{
					if (b.getDirection())
					{
						plateau[j][pos.y][pos.x + k] = BKO;
					}
					else 
					{
						plateau[j][pos.y + k][pos.x] = BKO;
					}
				}
			} else
			{
				int l = 0;
				if (b.getDirection())
				{
					l = p.x - pos.x;
				}
				else 
				{
					l = p.y - pos.y;
				}
				int[] vie = b.getTableauVie();
				if (vie[l] == 0)
					plateau[j][p.y][p.x] = BTT;
				else
					plateau[j][p.y][p.x] = BTO;
			}
		}
	}
	public void updatePlacement(int i, int nb, Point position, boolean direction) {
		Bateau b = flottes[i-1].getBateau(nb);
		Point p = b.getPosition();
		if (p.x != -1)
		{
			for (int k = 0; k < b.getTaille(); k++)
			{
				if (b.getDirection())
				{
					plateau[i-1][p.y][p.x + k] = VID;
				}
				else 
				{
					plateau[i-1][p.y + k][p.x] = VID;
				}
			}
		}
		b.setPosition(position);
		b.setDirection(direction);
		for (int k = 0; k < b.getTaille(); k++)
		{
			if (b.getDirection())
			{
				plateau[i-1][position.y][position.x + k] = BOK;
			}
			else 
			{
				plateau[i-1][position.y + k][position.x] = BOK;
			}
		}
	}
}
