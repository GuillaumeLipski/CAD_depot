package modele.flotte;

import java.awt.Point;
import java.util.ArrayList;

import modele.bateau.Bateau;
import modele.bateau.Corvette;

public class Flotte {

	private ArrayList<Bateau> flotte;
	
	public static final Bateau[] PETITE_FLOTTE={new Corvette(),new Corvette(),new Corvette()};
	
	public Flotte()
	{
		flotte = new ArrayList<Bateau>();
	}
	
	public Flotte(Bateau[] bateaux)
	{
		flotte = new ArrayList<Bateau>(bateaux.length);
		for (int i = 0; i < bateaux.length; i++)
		{
			flotte.add(bateaux[i]);
		}
	}
	
	public Flotte(Flotte f)
	{
		int taille = f.getNbBateau();
		flotte = new ArrayList<Bateau>(taille);
		for (int i = 0; i < taille; i++)
		{
			flotte.add(new Bateau(f.getBateau(i)));
		}
	}
	
	public void setPosition(int id,int x,int y,boolean horizontale){
		flotte.get(id).setDirection(horizontale);
		flotte.get(id).setPosition(new Point(x,y));
	}
	
	public Bateau getBateau(int id){
		if(id>=0&&id<flotte.size())
		{
			return flotte.get(id);
		}
		else
			return null;
	}
	
	public void add(Bateau b)
	{
		flotte.add(b);
	}
	
	public int getNbBateau(){
		return flotte.size();
	}
	
	public Bateau[] getListeBateau()
	{
		Bateau[] b = new Bateau[0];
		return flotte.toArray(b);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < flotte.size(); i++)
		{
			sb.append(flotte.get(i)+"`\n");
		}
		return sb.toString();
	}
	public Bateau testerTir(Point p, int puissance) {
		for (int i = 0; i < flotte.size(); i++)
		{
			Bateau b = flotte.get(i);
			if (b.testerTir(p, puissance))
				return b;
		}
		return null;
	}
	
	public Bateau testerTir(Point p) {
		for (int i = 0; i < flotte.size(); i++)
		{
			Bateau b = flotte.get(i);
			if (b.testerTir(p))
				return b;
		}
		return null;
	}

	public boolean estDetruite() {
		for (int i = 0; i < flotte.size(); i++)
		{
			if (!flotte.get(i).estDetruit())
				return false;
		}
		return true;
	}
	
	public Bateau[] getDefaultFlotte(){
		return null;
	}
}
