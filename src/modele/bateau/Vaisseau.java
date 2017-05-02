package modele.bateau;

public class Vaisseau extends Bateau {

	public Vaisseau()
	{
		super();
		nom = "Vaisseau";
		NbMunition = 5;
		taille = 5;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 1;
	}
}
