package modele.epoque;

import modele.bateau.Caravelle;
import modele.bateau.Galion;
import modele.bateau.Nef;
import modele.bateau.Vaisseau;
import modele.flotte.Flotte;

public class Epoque1800 extends Epoque {

	public Epoque1800()
	{
		nom = "18eme";
		flotte_type = new Flotte();
		flotte_type.add(new Nef());
		flotte_type.add(new Nef());
		flotte_type.add(new Caravelle());
		flotte_type.add(new Caravelle());
		flotte_type.add(new Galion());
		flotte_type.add(new Vaisseau());
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
