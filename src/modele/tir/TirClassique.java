package modele.tir;

import modele.Terrain;
import modele.strategie.Strategie;

public class TirClassique implements Tir{

	@Override
	public boolean tir(int idJoueur) {
		return true;
	}
	
	public String toString() {return "Tir Classique";}

	@Override
	public void tirer(Strategie strategie, int player, Terrain terrain) {
		strategie.choixTir(player, terrain);
	}

	@Override
	public int getId() {
		return 1;
	}

}
