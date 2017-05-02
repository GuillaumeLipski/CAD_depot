package modele.bateau;

import java.awt.Point;

public class Patrouilleur extends Bateau {
	
	public Patrouilleur()
	{
		super();
		nom = "Patrouilleur";
		NbMunition = 5;
		taille = 3;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 2;
	}
}
