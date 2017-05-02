package modele.epoque;

public abstract class Epoque {

	Flotte flotte_type;
	String nom, epoque;
	int id;
	
	public Bateau getBateauParTaille(int taille)
	{
		for (int i = 0; i < flotte_type.getNbBateau() ; i++)
		{
			Bateau b = flotte_type.getBateau(i);
			if (b.getTaille() == taille)
				return b;
		}
		return null;
	}
	
	public String getNom()
	{
		return nom;
	}

	public void setEpoque(String n) {
		epoque = n;
	}
	
	public void setId(String n) {
		id = Integer.parseInt(n);
	}
	
	public void setNom(String n) {
		nom = n;
	}
	public void setFlotte(Flotte f)
	{
		flotte_type = f;
	}
	
	public String getNom();
	
	public String toString()
	{
		return nom;
	}
}
