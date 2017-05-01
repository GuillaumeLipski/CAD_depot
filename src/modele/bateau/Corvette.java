package modele.bateau;

import java.awt.Point;

public class Corvette extends Bateau {

	public Corvette(int x, int y)
	{
		nom = "Corvette";
		NbMunition = 5;
		taille = 3;
		direction = true;
		position = new Point(x,y);
		tableauVie = new int[taille];
	}
}
