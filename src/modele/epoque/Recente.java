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
		flotte_type = new Flotte();
		flotte_type.add(new Corvette());
		flotte_type.add(new Corvette());
		flotte_type.add(new Patrouilleur());
		flotte_type.add(new Patrouilleur());
		flotte_type.add(new PorteAvion());
		flotte_type.add(new Fregate());
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
