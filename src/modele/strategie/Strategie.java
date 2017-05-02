package modele.strategie;

import java.awt.Point;

import modele.Modele;
import modele.Terrain;
import modele.bateau.Bateau;

public interface Strategie {
	
	public void setModele(Modele m);
	
	public void placerBateau(int njoueur, Terrain t,int nbateau);

	public void choixTir(int joueur, Terrain t);
	
}
