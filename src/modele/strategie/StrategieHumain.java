package modele.strategie;

import java.awt.Point;

import modele.Modele;
import modele.Terrain;
import modele.bateau.Bateau;

public class StrategieHumain implements Strategie {

	Modele m;
	
	public StrategieHumain()
	{
	}
	
	public void setModele(Modele mo) {m=mo;}
	
	@Override
	public String toString() {return "Humain";}


	@Override
	public void choixTir(int njoueur, Terrain t) {
		m.demanderPositionTir(njoueur);
	}

	@Override
	public void placerBateau(int njoueur, Terrain t, int nbateau) {
		m.demanderPlacementBateau(njoueur, nbateau);
	}
}
