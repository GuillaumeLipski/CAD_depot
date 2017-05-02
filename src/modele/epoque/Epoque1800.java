package modele.epoque;

import modele.bateau.Bateau;
import modele.bateau.Caravelle;
import modele.bateau.Galion;
import modele.bateau.Nef;
import modele.bateau.Vaisseau;
import modele.flotte.Flotte;

public class Epoque1800 extends Epoque {

	public Epoque1800()
	{
		nom = "18eme";
		Bateau[] bateaux = new Bateau[6];
		bateaux[0] = new Nef();
		bateaux[1] = new Nef();
		bateaux[2] = new Caravelle();
		bateaux[3] = new Caravelle();
		bateaux[4] = new Galion();
		bateaux[5] = new Vaisseau();
		flotte_type = new Flotte(bateaux);
	}
	
	public String getNomParTaille(int taille) {
		String nom="";
		switch(taille){
		case 1:
			nom="Barque";
			break;
		case 2:
			nom="Nef";
			break;
		case 3:
			nom="Caravelle";
			break;
		case 4:
			nom="Galion";
			break;
		case 5:
			nom="Vaisseau";
			break;		
		}
		return nom;
	}

	public String getApparanceParTaille(int taille) {
		return null;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}
	
	public String toString() {return nom;}
}
