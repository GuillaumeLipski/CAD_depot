package modele.flotte;

import java.awt.Point;

import modele.bateau.Bateau;

public class Flotte {

	private Bateau[] flotte;
	
	public Flotte(Bateau[] bateaux)
	{
		flotte = bateaux;
	}
	
	public void setPosition(int id,int x,int y,boolean horizontale){
		flotte[id].setDirection(horizontale);
		flotte[id].setPosition(new Point(x,y));
	}
	
	public Bateau getBateau(int id){
		if(id>0&&id<flotte.length)
			return flotte[id];
		else
			return null;
	}
	
	public int getNbBateau(){
		return flotte.length;
	}
	
	public Bateau[] getListeBateau()
	{
		return flotte;
	}
	
}
