package modele;

import java.util.ArrayList;
import java.util.List;

import modele.epoque.Epoque;
import modele.epoque.Epoque1800;
import modele.epoque.Recente;
import modele.strategie.Strategie;
import modele.strategie.StrategieAleatoire;
import modele.strategie.StrategieHumain;
import modele.tir.Tir;
import modele.tir.TirClassique;

public class BatailleNavale {
	private static BatailleNavale INSTANCE = new BatailleNavale();
	private Epoque[] epoques;
	private Strategie[] strategies;
	private Tir[] modedetirs;
	
	public static BatailleNavale getInstance() {return INSTANCE;}
	
	public BatailleNavale()
	{
		epoques = new Epoque[2];
		epoques[0] = new Recente();
		epoques[1] = new Epoque1800();
		strategies = new Strategie[2];
		strategies[0] =new StrategieHumain();
		strategies[1] = new StrategieAleatoire();
		modedetirs = new Tir[1];
		modedetirs[0]=new TirClassique();
	}
	
	public Epoque[] getEpoques()
	{
		return epoques;
	}
	
	public Strategie[] getStrategies()
	{
		return strategies;
	}
	
	public Tir[] getModeDeTirs()
	{
		return modedetirs;
	}
	
}
