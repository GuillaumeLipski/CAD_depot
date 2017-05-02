package modele.bateau;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bateau {

	public static final boolean DIRECTION_HORISONTALE=true;
	public static final boolean DIRECTION_VERTICALE=false;
	
	protected String nom;
	
	protected int NbMunition;
	
	protected int taille;
	
	protected boolean direction;
	
	protected Point position;
	
	protected int[] tableauVie;

	public Bateau()
	{
		position = new Point(-1,-1);
	}
	
	public Bateau(Bateau b)
	{
		nom = b.getNom();
		taille = b.getTaille();
		direction = b.getDirection();
		position = new Point(b.getPosition());
		tableauVie = new int[taille];
		for (int i = 0; i < taille; i++)
			tableauVie[i] = b.getTableauVie()[0];
	}

	public String getNom() {
		return nom;
	}

	public int getNbMunition() {
		return NbMunition;
	}

	public int getTaille() {
		return taille;
	}

	public boolean getDirection() {
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
	public boolean testerTir(Point tir, int puissance){
		double x=tir.getX();
		double y=tir.getY();
		boolean res=false;
		if(direction){
			if(y==position.getY()&&x>=position.getX()&&x<position.getX()+taille){
				res=true;
				tableauVie[(int)(x-position.getX())]-= puissance;
			}
		}else{
			if(x==position.getX()&&y>=position.getY()&&y<position.getY()+taille){
				res=true;
				tableauVie[(int)(y-position.getY())]-=puissance;
			}
		}
		return res;
	}
	
	public boolean estDetruit(){
		boolean res=true;
		for(int i=0;i<taille;i++){
			res = res &&(tableauVie[i]<=0);
		}
		return res;
	}
	
	public String toString()
	{
		return nom +" (Taille "+taille+") ";
	}
		
	public Image getImage(int i)
	{
		String res = "/"+nom+""+(i+1)+".png";
	    if (!direction)
	    	res = "/"+nom+""+(i+1)+"r.png";
	    java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
	    java.net.URL url = getClass().getResource(res);
		Image image =  toolkit.getImage(url);
		return image;
	}
}