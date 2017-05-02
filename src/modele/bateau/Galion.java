package modele.bateau;

public class Galion extends Bateau {
	
	public Galion()
	{
		super();
		nom = "Galion";
		NbMunition = 5;
		taille = 4;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 1;
	}
}
