package modele;

import java.awt.Point;

import modele.bateau.Bateau;
import modele.flotte.Flotte;

public class Terrain {

	private Flotte J1;
	
	private Flotte J2;
	
	private int hauteur;
	
	private int largeur;
	
	private int[][] plateau1;

	private int[][] plateau2;
	
	public Terrain(int h, int l,Flotte f1,Flotte f2){
		hauteur=h;
		largeur=l;
		plateau1 = new int[h][l];
		plateau2 = new int[h][l];
		J1=f1;
		J2=f2;
		for (int i = 0; i < J1.getNbBateau(); i++)
		{
			Bateau b1 = J1.getBateau(i);
			if (b1 != null)
			{
				Point p = b1.getPosition();
				for (int k = 0; k < b1.getTaille(); k++)
				{
					if (b1.isDirection())
					{
						plateau1[p.y][p.x + k] = 1;
					}
					else 
					{
						plateau1[p.y + k][p.x] = 1;
					}
				}
			}
			Bateau b2 = J2.getBateau(i);
			if (b2 != null)
			{
				Point p = b2.getPosition();
				for (int k = 0; k < b2.getTaille(); k++)
				{
					if (b2.isDirection())
					{
						plateau2[p.y][p.x + k] = 1;
					}
					else 
					{
						plateau2[p.y + k][p.x] = 1;
					}
				}
			}
		}
	}

	public Flotte getJ1() {
		return J1;
	}

	public Flotte getJ2() {
		return J2;
	}
	
	public int[][] getPlateau(int i)
	{
		if (i == 1)
			return plateau1;
		if (i == 2)
			return plateau2;
		return null;
	}
}
