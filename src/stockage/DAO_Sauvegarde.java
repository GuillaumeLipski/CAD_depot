package stockage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import modele.Modele;
import modele.bateau.Bateau;

/**
 *
 * @author Aziasso_I
 */
public class DAO_Sauvegarde {

	///////////////////////////// CONSTRUCTEUR ////////////////////////////////


	public DAO_Sauvegarde() {

		File f = new File("users");
		if (!f.exists()) {
			f.mkdir();
		}

	} /*-- DAO_Sauvegarde()--*/


	/*---------------------------- FONCTIONS ---------------------------------*/


	/**
	 * Permet de recuperer la liste de tous les profils disponibles
	 * @return la liste des profils disponibles
	 */
	public ArrayList<String> getAllProfils() {

		ArrayList<String> liste = new ArrayList<String>();
		File folder = new File("users");
		File[] listOfFiles = folder.listFiles();
		String nom="";
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".xml")) {
				nom=listOfFiles[i].getName().replace(".xml","");
				liste.add(nom);
			}
		}

		return liste;

	} // getAllProfils()


	/*------------------------------ PROFIL ----------------------------------


    /**
	 * Permet de creer un nouveau profil
	 * @param profil nouveau profil a enregistrer
	 */
	public void saveProfil(Modele partieEnCours) {

		String nom=partieEnCours.getNomPartie();
		int largeur=partieEnCours.getTerrain().getPlateau(1).length;
		int hauteur=partieEnCours.getTerrain().getPlateau(1)[0].length;
		Bateau[] bateauxJ1=partieEnCours.getTerrain().getJ1().getListeBateau();
		Bateau[] bateauxJ2=partieEnCours.getTerrain().getJ2().getListeBateau();
		int mdt=partieEnCours.getModeDeTir();
		try {
			Document document = null;
			Element root = null;
			document = new Document();
			root = new Element("partie");
			
			Element taille=new Element("taillePartie");
			taille.addContent(new Element("tailleHorizontale").setText(""+largeur));
			taille.addContent(new Element("tailleVerticale").setText(""+hauteur));	
			Element J1=convertirFlotteXML(bateauxJ1,1);
			Element J2=convertirFlotteXML(bateauxJ2,2);
			root.addContent(taille);
			root.addContent(new Element("ModeDeTir").setText(""+mdt));
			root.addContent(J1);
			root.addContent(J2);
			document.setContent(root);
			FileWriter writer = new FileWriter("users/"+nom+".xml");
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, writer);
            outputter.output(document, System.out);
            writer.close(); // close writer
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}

	} // fin saveProfil(Profil profil)
	
	private Element convertirFlotteXML(Bateau[] flotte,int joueur){
		Element res=new Element("Joueur"+joueur);
		Element bateau,position,tabVie;
		int[] vie;
		for(int i=0;i<flotte.length;i++){
			vie=flotte[i].getTableauVie();
			bateau=new Element("bateau");
			bateau.addContent(new Element("nom").setText(flotte[i].getNom()));
			bateau.addContent(new Element("direction").setText(""+flotte[i].getDirection()));
			bateau.addContent(new Element("taille").setText(""+flotte[i].getTaille()));
			bateau.addContent(new Element("munition").setText(""+flotte[i].getNbMunition()));
			position=new Element("position");
			position.addContent(new Element("positionH").setText(""+(int)flotte[i].getPosition().getX()));
			position.addContent(new Element("positionV").setText(""+(int)flotte[i].getPosition().getY()));
			bateau.addContent(position);
			tabVie=new Element("tabVie");
			for(int j=0;j<vie.length;j++){
				tabVie.addContent(new Element("PointVie").setText(""+vie[j]));
			}
			bateau.addContent(tabVie);
			res.addContent(bateau);
		}
		return res;
	}

	/**
	 * Permet de supprimer un profil
	 * @param profil profil a supprimer
	 */
	public void removeProfil(String profil) {

		String s = "users" + File.separator+profil + ".xml";
		File f = new File(s);
		f.delete();

	} // removeProfil(int id)


	/**
	 * Permet de savoir si un profil du meme nom existe deja
	 * @param nom nom a tester
	 * @return TRUE si un profil ayant le meme nom existe deja, FALSE sinon on le crée
	 */
	public boolean isExistingProfil(String nom) {  // Profil existant(String nom)

		Iterator iterator = this.getAllProfils().iterator();
		while (iterator.hasNext()) {

			if (iterator.next().equals(nom)) {

				return true;

			}

		}

		return false;

	} 

} // class DAO_Sauvegarde
