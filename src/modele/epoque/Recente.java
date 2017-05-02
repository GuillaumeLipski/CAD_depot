package modele.epoque;

import modele.bateau.Bateau;
import modele.bateau.Corvette;
import modele.bateau.Fregate;
import modele.bateau.Patrouilleur;
import modele.bateau.PorteAvion;
import modele.flotte.Flotte;

public class Recente extends Epoque{

	public Recente()
	{
		nom = "Moderne";
		Bateau[] bateaux = new Bateau[6];
		bateaux[0] = new Corvette();
		bateaux[1] = new Corvette();
		bateaux[2] = new Patrouilleur();
		bateaux[3] = new Patrouilleur();
		bateaux[4] = new PorteAvion();
		bateaux[5] = new Fregate();
		flotte_type = new Flotte(bateaux);
	}
	
	public String getNomParTaille(int taille) {
		String nom="";
		switch(taille){
		case 1:
			nom="Mine";
			break;
		case 2:
			nom="Fregatte";
			break;
		case 3:
			nom="Corvette";
			break;
		case 4:
			nom="Patrouilleur";
			break;
		case 5:
			nom="PorteAvion";
			break;		
		}
		return nom;
	}

	@Override
	public String getNom() {
		return nom;
	}
	
}
