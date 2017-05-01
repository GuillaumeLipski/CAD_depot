package modele;

import java.awt.Point;
import java.util.Observable;

import modele.bateau.Bateau;
import modele.flotte.Flotte;
import modele.strategie.Strategie;
import modele.tir.Tir;

public class Modele extends Observable {

	private Tir modeDeTir;
	
	private Strategie StrategieJ1,StrategieJ2;
	
	private Terrain terrain;
	
	public Modele(Tir mdt,Strategie j1,Strategie j2,Terrain ter){
		modeDeTir=mdt;
		StrategieJ1=j1;
		StrategieJ2=j2;
		terrain=ter;
	}
	
	public void run(){
		//initialisation
		Flotte j1 =terrain.getJ1();
		int nbBateau=j1.getNbBateau();
		for(int i=0;i<nbBateau;i++){
			
		}
	}
	
	public void demanderPositionTir(int idJoueur){
		modeDeTir.tir(idJoueur);
	}
	
	//je ne sais plus a quoi ca sert
	public Point demanderPlacementBateau(int idJoueur,Bateau bateau){
		return null;
	}
	public boolean demanderChoixBateau(){
		return false;
	}
	

	
	
}
