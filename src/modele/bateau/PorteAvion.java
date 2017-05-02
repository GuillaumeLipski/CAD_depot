package modele.bateau;

public class PorteAvion extends Bateau {

	public PorteAvion()
	{
		super();
		nom = "PorteAvion";
		NbMunition = 5;
		taille = 4;
		direction = true;
		tableauVie = new int[taille];
		for (int i = 0 ; i < taille; i++)
			tableauVie[i] = 2;
	}
}
