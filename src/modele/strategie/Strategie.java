package modele.strategie;

import java.awt.Point;

public interface Strategie {

	public Point getPlacementBateau(int xmax,int ymax);
	
	public Point getPlacementTir(int xmax,int ymax);
	
}
