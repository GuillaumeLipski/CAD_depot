package modele.bateau;

import java.awt.Point;

public class Fregate extends Bateau {
	public Fregate(int x, int y)
	{
		nom = "Fregate";
		NbMunition = 5;
		taille = 5;
		direction = true;
		position = new Point(x,y);
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 2;
	}
}
