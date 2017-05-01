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
	
	private int player, step; //player : Numero du joueur auquel c'est le tour de jouer || step: étape du déroulement de la partie.
	
	private Bateau bateauSelectionne;
	
	private Point pointDonne;
	
	private boolean directionDonne;
	
	private boolean waitPosition;
	
	public Modele(Tir mdt,Strategie j1,Strategie j2,Terrain ter){
		modeDeTir=mdt;
		StrategieJ1=j1;
		StrategieJ2=j2;
		terrain=ter;
		player = 1;
		step = 0;
	}
		
	public synchronized void demanderPositionTir(int idJoueur){
		this.setChanged();
		notifyObservers();
		waitPosition = true;
		//modeDeTir.tir(idJoueur);
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		terrain.updateTir(player, pointDonne, 1);
		this.setChanged();
		notifyObservers();
	}
	
	public synchronized void donnerPosition(Point position, boolean direction)
	{
		if (waitPosition)
		{
			pointDonne = position;
			directionDonne = !direction;
			waitPosition = false;
		}
		notify();
	}
	
	//je ne sais plus a quoi ca sert
	public synchronized void demanderPlacementBateau(int idJoueur,int nbateau){
		this.setChanged();
		notifyObservers();
		waitPosition = true;
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		terrain.updatePlacement(idJoueur, nbateau, pointDonne, directionDonne);
		this.setChanged();
		notifyObservers();
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
		this.setChanged();
		notifyObservers();
	}
	
	public int getStep()
	{
		return step;
	}
	
	public Bateau getBateauSelectionne() {return bateauSelectionne;}

	public int getTaille() {
		if (bateauSelectionne == null)
		{
			return 1;
		} else
		{
			return bateauSelectionne.getTaille();
		}
	}
	
	public void run(){
		setPlayer(1);
		step = 0;
		Flotte J1 = terrain.getJ1();
		for (int i = 0; i < J1.getNbBateau(); i++)
		{
			bateauSelectionne = J1.getBateau(i);
			demanderPlacementBateau(1, i);
		}
		setPlayer(2);
		Flotte J2 = terrain.getJ2();
		for (int i = 0; i < J2.getNbBateau(); i++)
		{
			bateauSelectionne = J2.getBateau(i);
			demanderPlacementBateau(2, i);
		}
		bateauSelectionne = null;
		step = 1;
		while (!terrain.estTermine())
		{
			setPlayer(1 - (player - 1) + 1);
			demanderPositionTir(player);
		}
	}
	
}
