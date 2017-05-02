package modele.strategie;

import java.awt.Point;
import java.util.Random;

import modele.Modele;
import modele.Terrain;

public class StrategieAleatoire implements Strategie{
	Modele m;

	private int getRand(int max){
		return (int)(Math.random()*max);
	}

	public void setModele(Modele mo) {m=mo;}
	
	@Override
	public String toString() {return "Ordinateur : Aléatoire";}

	@Override
	public void placerBateau(int njoueur, Terrain t, int nbateau) {
		boolean placement = false;
		do
		{
			Random r = new Random();
			int x = r.nextInt(10), y = r.nextInt(10);
			Point p = new Point(x,y);
			boolean d = r.nextBoolean();
			placement = t.placementValide(njoueur, nbateau, p, d);
			if (placement)
				t.updatePlacement(njoueur, nbateau, p, d);
			
		} while (placement == false);
	}

	@Override
	public void choixTir(int njoueur, Terrain t) {
		boolean tir = false;
		do
		{
			Random r = new Random();
			int x = r.nextInt(10), y = r.nextInt(10);
			Point p = new Point(x,y);
			tir = t.tirValide(njoueur, p);
			if (tir)
				t.updateTir(njoueur, p,1);
			
		} while (tir == false);
		
	}
}
