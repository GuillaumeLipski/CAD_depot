package modele.epoque;

public class Recente implements Epoque{
	
	

	@Override
	public String getNomParTaille(int taille) {
		// TODO Auto-generated method stub
		String nom="";
		switch(taille){
		case 1:
			nom="Mine";
			break;
		case 2:
			nom="Fregatte";
			break;
		case 3:
			nom="Corvette";
			break;
		case 4:
			nom="Patrouilleur";
			break;
		case 5:
			nom="PorteAvion";
			break;		
		}
		return nom;
	}

	@Override
	public String getApparanceParTaille(int taille) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
