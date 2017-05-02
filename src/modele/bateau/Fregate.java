package modele.bateau;

import java.awt.Point;

public class Fregate extends Bateau {
	
	public Fregate()
	{
		super();
		nom = "Fregate";
		NbMunition = 5;
		taille = 5;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 2;
	}
}
