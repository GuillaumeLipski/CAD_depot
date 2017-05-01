package modele.bateau;

import java.awt.Point;

public class Corvette extends Bateau {

	public Corvette()
	{
		super();
		nom = "Corvette";
		NbMunition = 5;
		taille = 2;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 2;
	}
}
