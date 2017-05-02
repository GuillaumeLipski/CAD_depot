package modele.flotte;

import java.awt.Point;

import modele.bateau.Bateau;
import modele.bateau.Corvette;

public class Flotte {

	private Bateau[] flotte;
	
	public static final Bateau[] PETITE_FLOTTE={new Corvette(),new Corvette(),new Corvette()};
	
	public Flotte(Bateau[] bateaux)
	{
		flotte = new Bateau[bateaux.length];
		for (int i = 0; i < bateaux.length; i++)
		{
			flotte[i] = bateaux[i];
			System.out.println(flotte[i]);
		}
	}
	
	public void setPosition(int id,int x,int y,boolean horizontale){
		flotte[id].setDirection(horizontale);
		flotte[id].setPosition(new Point(x,y));
	}
	
	public Bateau getBateau(int id){
		if(id>=0&&id<flotte.length)
		{
			return flotte[id];
		}
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
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < flotte.length; i++)
		{
			sb.append(flotte[i]+"`\n");
		}
		return sb.toString();
	}
	public Bateau testerTir(Point p, int puissance) {
		for (int i = 0; i < flotte.length; i++)
		{
			Bateau b = flotte[i];
			if (b.testerTir(p, puissance))
				return b;
		}
		return null;
	}
	
	public Bateau testerTir(Point p) {
		for (int i = 0; i < flotte.length; i++)
		{
			Bateau b = flotte[i];
			if (b.testerTir(p))
				return b;
		}
		return null;
	}

	public boolean estDetruite() {
		for (int i = 0; i < flotte.length; i++)
		{
			if (!flotte[i].estDetruit())
				return false;
		}
		return true;
	}
	
	public Bateau[] getDefaultFlotte(){
		return null;
	}
}
