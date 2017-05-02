package modele.bateau;

public class Caravelle extends Bateau {
	public Caravelle()
	{
		super();
		nom = "Caravelle";
		NbMunition = 5;
		taille = 3;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 1;
	}
}
