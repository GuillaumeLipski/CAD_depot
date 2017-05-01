package modele;

import modele.flotte.Flotte;

public class Terrain {

	private Flotte J1;
	
	private Flotte J2;
	
	private int hauteur;
	
	private int largeur;
	
	public Terrain(int h, int l,Flotte f1,Flotte f2){
		hauteur=h;
		largeur=l;
		J1=f1;
		J2=f2;
	}

	public Flotte getJ1() {
		return J1;
	}

	public Flotte getJ2() {
		return J2;
	}
	
}
