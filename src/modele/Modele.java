package modele;

import java.awt.Point;
import java.util.Observable;

import modele.bateau.Bateau;
import modele.flotte.Flotte;
import modele.strategie.Strategie;
import modele.tir.Tir;

public class Modele extends Observable implements Runnable{

	private Tir modeDeTir;
	
	private Strategie StrategieJ1,StrategieJ2;
	
	private Terrain terrain;
	
	private int player, step;
	
	private Point pointDonne;
	
	private boolean directionDonne;
	
	private boolean waitPosition;
	
	public Modele(Tir mdt,Strategie j1,Strategie j2,Terrain ter){
		modeDeTir=mdt;
		StrategieJ1=j1;
		StrategieJ2=j2;
		terrain=ter;
		player = 1;
		step = 1;
	}
	
	public void run(){
		//Point p = demanderPlacementBateau(1, null);
	}
	
	public void demanderPositionTir(int idJoueur){
		modeDeTir.tir(idJoueur);
	}
	
	public void donnerPosition(Point position, boolean direction)
	{
		System.out.println("Point donné:"+position.x+" "+position.y);
		if (waitPosition)
		{
			pointDonne = position;
			directionDonne = direction;
			waitPosition = false;
		}
	}
	
	//je ne sais plus a quoi ca sert
	public Point demanderPlacementBateau(int idJoueur,Bateau bateau){
		waitPosition = true;
		while (waitPosition)
		{
		}
		return pointDonne;
	}
	public boolean demanderChoixBateau(){
		return false;
	}
	
	public int getPlayerTurn()
	{
		return player;
	}
	
	public Terrain getTerrain()
	{
		return terrain;
	}

	public void setPlayer(int i) {
		player = i;
		notifyObservers();
		this.setChanged();
	}
	
	public int getStep()
	{
		return step;
	}
	
	
}
