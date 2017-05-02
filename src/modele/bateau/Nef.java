package modele.bateau;

public class Nef extends Bateau {
	
	public Nef()
	{
		super();
		nom = "Nef";
		NbMunition = 5;
		taille = 2;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 1;
	}
}
