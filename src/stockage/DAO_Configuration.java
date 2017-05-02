
package stockage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import org.jdom2.Attribute;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Element;

import modele.bateau.Bateau;
import modele.epoque.Epoque;
import modele.flotte.Flotte;

/**
 *
 * @author Aziasso_I
 */

public class DAO_Configuration {
    ////////////////////////////// VARIABLES //////////////////////////////////
    
    
    private final String path = "stockage/fich_config.xml";
    private org.jdom2.Document document;
    private final org.jdom2.Element racine;
    
    private DAO_Configuration instance = new DAO_Configuration();
    
    public DAO_Configuration getInstance() {return instance;}

    
    ///////////////////////////// CONSTRUCTEUR ////////////////////////////////
    
    
    public DAO_Configuration() {
        
        SAXBuilder sxb = new SAXBuilder();
        try {
            
            //On crée un nouveau document JDOM avec en argument le fichier XML
            File f = new File(path);
            File dossier = new File("stockage");
            if (!dossier.exists()) {
                dossier.mkdir();
            }
            if (!f.exists()) {
                this.ecrireFichConfig();
            }
            document = sxb.build(f);
            
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        racine = document.getRootElement();
        
    } // DAO_Configuration()

    
    ////////////////////////////// FONCTIONS //////////////////////////////////
    
    
    /**
     * Permet de recuperer la liste des epoques disponibles dans le fichier de
     * configuration
     * @return la liste des epoques disponible
     */
    public HashMap<String, Epoque> getAllEpoques() {
        
        List listeEpoquesXML = racine.getChildren("epoque");
        HashMap<String, Epoque> listeEpoques = new HashMap();
        Iterator i = listeEpoquesXML.iterator();
        while (i.hasNext()) {
            
            Epoque ep = new Epoque();
            org.jdom2.Element courant = (org.jdom2.Element) i.next();
            ep.setEpoque(courant.getChildText("siecle"));
            ep.setId(courant.getChildText("id"));
            ep.setNom(courant.getChildText("nom"));
            Element bateaux = courant.getChild("bateaux");
            Iterator i2 = bateaux.getChildren().iterator();
            Flotte bateauxHM = new Flotte();
            
            while (i2.hasNext()) {
                
                Element courant2 = (Element) i2.next();
                Bateau b = new Bateau(courant2.getChildText("nom"),Integer.parseInt(courant2.getChildText("longueur")),1,1);                
                bateauxHM.add(b);
                
            }
            
            ep.setFlotte(bateauxHM);
            listeEpoques.put(courant.getChildText("nom"), ep);
            
        }
        return listeEpoques;
        
    } // getAllEpoques()

    
    /**
     * Permet de recuperer la liste des bateaux disponibles pour une epoque
     * donnee dans le fichier de configuration
     * @param epoque epoque dont on souhaite recuperer les bateaux
     * @return la liste des bateaux disponibles pour l'epoque donnee
     
    public HashMap getAllBateaux(Epoque epoque) {
        
        return epoque.getListBateaux();

    } // getAllBateaux(Epoque epoque)*/

    
    /**
     * Permet d'ecrire le fichier de config s'il est introuvabel
     */
    private void ecrireFichConfig() {
        
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!-- Fichier de configuration (Epoque/Bateaux)\n"
                + " L'id des images bateaux doit commencer par 1 et les id suivants des images doivent suivre dans \n"
                + " l'ordre croissant -->\n"
                + "<epoques>\n"
                + "    <epoque>\n"
                + "        <id>5678</id>\n"
                + "        <nom>Epoque1700</nom>\n"
                + "        <siecle>17eme</siecle>\n"
                + "        <image>Epoque1700.jpg</image>\n"
                + "        <bateaux>\n"
                + "            <bateau>\n"
                + "                <nom>nom_bateau</nom>\n"
                + "                <longueur>5</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>4</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>3</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "        </bateaux>\n"
                + "    </epoque>\n"
                + "    <epoque>\n"
                + "        <id>4678</id>\n"
                + "        <nom>Epoque1800</nom>\n"
                + "        <siecle>18eme</siecle>\n"
                + "        <image>.png</image>\n"
                + "        <bateaux>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>5</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>4</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>3</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <portee>2</portee>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "        </bateaux>\n"
                + "    </epoque>\n"
                + "    <epoque>\n"
                + "        <id>3678</id>\n"
                + "        <nom>Epoque1900</nom>\n"
                + "        <siecle>19eme</siecle>\n"
                + "        <image>.png</image>\n"
                + "        <bateaux>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>5</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>4</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>3</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "        </bateaux>\n"
                + "    </epoque>\n"
                + "  <epoque>\n"
                + "        <id>2678</id>\n"
                + "        <nom>Epoque2000</nom>\n"
                + "        <siecle>20eme</siecle>\n"
                + "        <image>.png</image>\n"
                + "        <bateaux>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>5</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>4</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>3</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "            <bateau>\n"
                + "                <nom>Nom_bateau</nom>\n"
                + "                <longueur>2</longueur>\n"
                + "                <munitions>5</munitions>\n"
                + "                <vie>5</vie>\n"
                + "                <degats>5</degats>\n"
                + "            </bateau>\n"
                + "        </bateaux>\n"
                + "    </epoque>\n"
                + "</epoques>";
        
        try {
            
            File f = new File("stockage/fichier_config.xml");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(s);
            bw.newLine();
            bw.flush();
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(DAO_Parametre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // ecrireFichConfig()

} // class DAO_Configuration
    

