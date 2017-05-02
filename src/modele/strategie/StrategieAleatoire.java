package modele.strategie;

import java.awt.Point;

public class StrategieAleatoire implements Strategie{

	@Override
	public Point getPlacementBateau(int xmax,int ymax) {
		// TODO Auto-generated method stub
		Point res=new Point(getRand(xmax),getRand(ymax));
		return res;
	}

	@Override
	public Point getPlacementTir(int xmax,int ymax) {
		// TODO Auto-generated method stub
		Point res=new Point(getRand(xmax),getRand(ymax));
		return res;
	}
	
	private int getRand(int max){
		return (int)(Math.random()*max);
	}

}
