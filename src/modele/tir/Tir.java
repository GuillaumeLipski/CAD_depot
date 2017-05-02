package modele.tir;

import modele.Terrain;
import modele.strategie.Strategie;

public interface Tir {

	public boolean tir(int idJoueur);
	
	public String toString();

	public void tirer(Strategie strategie, int player, Terrain terrain);
	
}
