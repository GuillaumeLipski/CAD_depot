package modele.bateau;

import java.awt.Point;

public abstract class Bateau {

	public static final boolean DIRECTION_HORISONTALE=true;
	public static final boolean DIRECTION_VERTICALE=false;
	
	private String nom;
	
	private int NbMunition;
	
	private int taille;
	
	private boolean direction;
	
	private Point position;
	
	private int[] tableauVie;

	public String getNom() {
		return nom;
	}

	public int getNbMunition() {
		return NbMunition;
	}

	public int getTaille() {
		return taille;
	}

	public boolean isDirection() {
		return direction;
	}
	
	public void setDirection(boolean direction) {
		this.direction = direction;
	}


	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	public int[] getTableauVie() {
		return tableauVie.clone();
	}
	
	public boolean testerTir(Point tir){
		double x=tir.getX();
		double y=tir.getY();
		boolean res=false;
		if(direction){
			if(y==position.getY()&&x>=position.getX()&&x<position.getX()+taille){
				res=true;
				tableauVie[(int)(x-position.getX())]=0;
			}
		}else{
			if(x==position.getX()&&y>=position.getY()&&y<position.getY()+taille){
				res=true;
				tableauVie[(int)(y-position.getY())]=0;
			}
		}
		return res;
	}
	
	public boolean estDetruit(){
		boolean res=false;
		for(int i=0;i<taille;i++){
			if(tableauVie[i]!=0){
				res=true;
			}
		}
		return res;
	}
}