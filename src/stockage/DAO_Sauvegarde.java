package stockage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import org.jdom2.Attribute;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Aziasso_I
 */
public class DAO_Sauvegarde {
    /*
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
     
    public HashMap getAllProfils() {

        HashMap<String, Profil> liste = new HashMap<>();
        File folder = new File("users");
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".xml")) {
                try {

                    SAXBuilder builder = new SAXBuilder();
                    Document document = (Document) builder.build(listOfFiles[i]);
                    Element rootNode = document.getRootElement();
                    Profil p = new Profil();
                    
                    // ID
                    Element id = rootNode.getChild("id");
                    p.setId(id.getAttributeValue("id"));
                    
                    // Nom
                    Element nom = rootNode.getChild("nom");
                    p.setNom(nom.getAttributeValue("nom"));
                    
                    // Parties
                    List list = rootNode.getChildren("partie");
                    HashMap<String,Partie> parties = new HashMap<>();
                    for (int j = 0; j < list.size(); j++) {
                        
                        Element partie = (Element) list.get(j);
                        String idPartie = partie.getAttributeValue("id");
                        Partie laPartie = this.getPartie(idPartie, p);
                        parties.put(laPartie.getId(), laPartie);
                        
                    }
                    p.setParties(parties);
                    
                    liste.put(p.getNom(), p);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DAO_Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JDOMException | IOException ex) {
                    Logger.getLogger(DAO_Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        
        return liste;

    } // getAllProfils()


/*                    String[]uri=listOfFiles[i].split("\");
                    String nom=uri[uro.length-1].replace(".xml","");
                    liste.add(nom);*/

    
    /*------------------------------ PROFIL ----------------------------------
     
    
    /**
     * Permet de creer un nouveau profil
     * @param profil nouveau profil a enregistrer
     
    public void saveProfil(Profil profil) {

        try {
 
            // Profil
            Element elProfil = new Element("profil");
            Document doc = new Document();
            doc.setRootElement(elProfil);

            // Id
            Element idProfil = new Element("id");
            idProfil.setAttribute(new Attribute("id", profil.getId()));
            doc.getRootElement().addContent(idProfil);

            // Nom
            Element nomProfil = new Element("nom");
            nomProfil.setAttribute(new Attribute("nom", profil.getNom()));
            doc.getRootElement().addContent(nomProfil);

            Iterator iterator = profil.getParties().keySet().iterator();
            while(iterator.hasNext()) {
                
                Partie partie = (Partie)profil.getParties().get(iterator.next());
                
                Element p = new Element("partie");
                p.setAttribute(new Attribute("id", partie.getId()));
                p.addContent(new Element("date").setText(partie.getDate()));
                String auto = partie.isAutomatique() ? "1" : "0";
                p.addContent(new Element("automatique").setText(auto));
                
                // IA
                Element ia = new Element("IA");
                for(Case c : partie.getIntelligenceArtificielle().getListeCaseATester()) {
               
                    Element caseIA = new Element("caseIA");
                    caseIA.addContent(new Element("abs").setText(c.getAbs()+""));
                    caseIA.addContent(new Element("ord").setText(c.getOrd()+""));
                    ia.addContent(caseIA);
                    
                }
                p.addContent(ia);
                
                // Parametre
                Element parametre = new Element("parametre");
               
                parametre.addContent(new Element("nbCaseX").setText(partie.getParametre().getNbCaseX()+""));
                parametre.addContent(new Element("nbCaseY").setText(partie.getParametre().getNbCaseY()+""));
                parametre.addContent(new Element("difficulte").setText(partie.getParametre().getDifficulte()+""));
                String majPortee = partie.getParametre().isMajPortee() ? "1" : "0";
                parametre.addContent(new Element("majPortee").setText(majPortee));
                parametre.addContent(new Element("nomEpoque").setText(partie.getParametre().getEpoque().getNom()));
                p.addContent(parametre);
                
                // Joueur J1
                Element joueur1 = new Element("joueur1");
                joueur1.setAttribute(new Attribute("num", 1+""));
                joueur1.addContent(new Element("nom").setText(partie.getJ1().getNom()+""));
                joueur1.addContent(new Element("nbTirsGagnant").setText(partie.getJ1().getNbTirsGagnant()+""));
                joueur1.addContent(new Element("nbTirsPerdant").setText(partie.getJ1().getNbTirsPerdant()+""));
                for(Case c : partie.getJ1().getCases()) {
                    
                    Element caseJ = new Element("case");
                    String etat = c.isEtat() ? "1" : "0";
                    caseJ.addContent(new Element("etat").setText(etat));
                    String aPortee = c.isAPortee() ? "1" : "0";
                    caseJ.addContent(new Element("aPortee").setText(aPortee));
                    caseJ.addContent(new Element("abs").setText(c.getAbs()+""));
                    caseJ.addContent(new Element("ord").setText(c.getOrd()+""));
                    caseJ.addContent(new Element("idPartie").setText(c.getPartie().getId()));
                    if(c.getBateau() == null) {
                        
                        // Case vide
                        caseJ.addContent(new Element("bateau").setText("null"));
                        caseJ.addContent(new Element("nbCasesNonTouchees").setText("0"));
                        caseJ.addContent(new Element("orientation").setText("0"));
                    
                    } else {
                        
                        // Case bateau
                        caseJ.addContent(new Element("bateau").setText(c.getBateau().getNom()));
                        caseJ.addContent(new Element("orientation").setText(c.getBateau().getOrientation()+""));
                        caseJ.addContent(new Element("imageBateau").setText(((CaseBateau)c).getImage()));
                        caseJ.addContent(new Element("nbCasesNonTouchees").setText(c.getBateau().getNbCasesNonTouchees()+""));
                        
                    }
                    joueur1.addContent(caseJ);
                    
                }
                p.addContent(joueur1);
                
                // Joueur J2
                Element joueur2 = new Element("joueur2");
                joueur2.setAttribute(new Attribute("num", 2+""));
                joueur2.addContent(new Element("nom").setText(partie.getJ2().getNom()+""));
                joueur2.addContent(new Element("nbTirsGagnant").setText(partie.getJ2().getNbTirsGagnant()+""));
                joueur2.addContent(new Element("nbTirsPerdant").setText(partie.getJ2().getNbTirsPerdant()+""));
                joueur2.addContent(new Element("difficulte").setText(((JoueurMachine)partie.getJ2()).getDifficulte()));
                for(Case c : partie.getJ2().getCases()) {
                    
                    Element caseJ = new Element("case");
                    String etat = c.isEtat() ? "1" : "0";
                    caseJ.addContent(new Element("etat").setText(etat));
                    String aPortee = c.isAPortee() ? "1" : "0";
                    caseJ.addContent(new Element("aPortee").setText(aPortee));
                    caseJ.addContent(new Element("abs").setText(c.getAbs()+""));
                    caseJ.addContent(new Element("ord").setText(c.getOrd()+""));
                    caseJ.addContent(new Element("idPartie").setText(c.getPartie().getId()));
                    if(c.getBateau() == null) {
                        
                        // Case vide
                        caseJ.addContent(new Element("bateau").setText("null"));
                        caseJ.addContent(new Element("nbCasesNonTouchees").setText("0"));
                        caseJ.addContent(new Element("orientation").setText("0"));
                    
                    } else {
                        
                        // Case bateau
                        caseJ.addContent(new Element("bateau").setText(c.getBateau().getNom()));
                        caseJ.addContent(new Element("orientation").setText(c.getBateau().getOrientation()+""));
                        caseJ.addContent(new Element("imageBateau").setText(((CaseBateau)c).getImage()));
                        caseJ.addContent(new Element("nbCasesNonTouchees").setText(c.getBateau().getNbCasesNonTouchees()+""));
                        
                    }
                    joueur2.addContent(caseJ);
                    
                }
                p.addContent(joueur2);
                
                doc.getRootElement().addContent(p);
                
            }

            
            XMLOutputter xmlOutput = new XMLOutputter();

            // Enregistre le fichier
            String file = "users" + File.separator + profil.getNom() + ".xml";
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(file));
 
        } catch (IOException io) {
              System.out.println(io.getMessage());
        }
      
    } // fin saveProfil(Profil profil)
    
    
    /**
     * Permet de supprimer un profil
     * @param profil profil a supprimer
     
    public void removeProfil(Profil profil) {
        
        String s = "users" + File.separator+profil.getNom() + ".xml";
        File f = new File(s);
        f.delete();
        
    } // removeProfil(int id)

    
    /**
     * Permet de savoir si un profil du meme nom existe deja
     * @param nom nom a tester
     * @return TRUE si un profil ayant le meme nom existe deja, FALSE sinon on le crée
     
    public boolean isExistingProfil(String nom) {  // Profil existant(String nom)

        Iterator iterator = this.getAllProfils().keySet().iterator();
        while (iterator.hasNext()) {

            if (((Profil) this.getAllProfils().get(iterator.next())).getNom().equals(nom)) {

                return true;

            }

        }

        return false;

    } 

   
    /*------------------------------- PARTIE ---------------------------------
    
    
    /**
     * Permet de recuperer une partie d'un profil
     * @param id identifiant de la partie
     * @param profil profil dans lequel on souhaite recuperer une partie
     * @return la partie souhaitee
     
    public Partie getPartie(String id, Profil profil) {
        
        Partie partie = new Partie();
        File folder = new File("users");
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().replaceAll("\\..*", "").equals(profil.getNom())) {
                try {
                    
                    SAXBuilder builder = new SAXBuilder();
                    Document document = (Document) builder.build(listOfFiles[i]);
                    Element rootNode = document.getRootElement();
                    //Profil p = new Profil();
                    
                    List list = rootNode.getChildren("partie");
                    for (int j = 0; j < list.size(); j++) {
                        
                        Element partieElt = (Element) list.get(j);
                        String idPartie = partieElt.getAttributeValue("id");
                        
                        // On en recupere que la bonne partie
                        if(idPartie.equals(id)) {
                        
                            boolean auto = ("1".equals(partieElt.getChildText("automatique")));
                            partie.setId(idPartie);
                            partie.setAutomatique(auto);
                            partie.setDate(partieElt.getChildText("date"));

                            // Parametre
                            Element param = (Element) partieElt.getChild("parametre");
                            Parametre parametre = new Parametre();
                            parametre.setNbCaseX(Integer.parseInt(param.getChildText("nbCaseX")));
                            parametre.setNbCaseY(Integer.parseInt(param.getChildText("nbCaseY")));
                            parametre.setDifficulte(param.getChildText("difficulte"));
                            boolean majPortee = ("1".equals(param.getChildText("majPortee")));
                            parametre.setMajPortee(majPortee);
                            Epoque epoque = DAOFactory.getInstance().getDAO_Configuration().getAllEpoques().get(param.getChildText("nomEpoque"));
                            parametre.setEpoque(epoque);

                            // Joueur J1
                            Element j1 = (Element) partieElt.getChild("joueur1");
                            Joueur joueur1 = new JoueurHumain();
                            joueur1.setPartie(partie);
                            joueur1.setNom(j1.getChildText("nom"));
                            //joueur1.setNbTirsGagnant(Integer.parseInt(j1.getChildText("nbTirsGagnant")));
                            //joueur1.setNbTirsPerdant(Integer.parseInt(j1.getChildText("nbTirsPerdant")));
                            List listCasesXML = j1.getChildren("case");
                            ArrayList<Case> cases = new ArrayList<>();
                            HashMap<String,Bateau> bateauxJ1 = new HashMap();
                            for (int k = 0; k < listCasesXML.size(); k++) {

                                Element caseElt = (Element) listCasesXML.get(k);
                                Case c = null;
                                if(caseElt.getChildText("bateau").equals("null")) {

                                    c = new CaseVide();
                                    c.setPartie(partie);

                                } else {

                                    if(bateauxJ1.containsKey(((Bateau)Factory.getInstance().getDAO_Configuration().getAllBateaux(epoque).get(caseElt.getChildText("bateau"))).getNom())) {
                                        // On a deja memorise le bateau, alors on cree une CaseBateau a partir de ce dernier
                                        c = new CaseBateau(bateauxJ1.get(((Bateau)Factory.getInstance().getDAO_Configuration().getAllBateaux(epoque).get(caseElt.getChildText("bateau"))).getNom()),partie);
                                    } else {
                                        // Le bateau n'a pas encore ete memorise, on l'ajoute a la liste
                                        Bateau bateau = new Bateau(((Bateau)DAOFactory.getInstance().getDAO_Configuration().getAllBateaux(epoque).get(caseElt.getChildText("bateau"))));
                                        bateauxJ1.put(bateau.getNom(),bateau);
                                        c = new CaseBateau(bateau,partie);
                                        
                                    }
                                    c.getBateau().setOrientation(Integer.parseInt(caseElt.getChildText("orientation")));
                                    c.getBateau().setNbCasesNonTouchees(Integer.parseInt(caseElt.getChildText("nbCasesNonTouchees")));
                                    ((CaseBateau)c).setImage(caseElt.getChildText("imageBateau"));
                                    
                                }
                                boolean etat = ("1".equals(caseElt.getChildText("etat")));
                                c.setEtat(etat);
                                c.setAbs(Integer.parseInt(caseElt.getChildText("abs")));
                                c.setOrd(Integer.parseInt(caseElt.getChildText("ord")));
                                boolean aPorte = ("1".equals(caseElt.getChildText("aPorte")));
                                c.setPortee(aPorte);
                                cases.add(c);

                            }
                            joueur1.setCases((ArrayList<Case>) cases);
                            partie.setJ1(joueur1);
                            
                            // IA
                            IntelligenceArtificielle ia = FactoryIA.getInstance().getIntelligenceArtificielle(parametre);
                            Element IA = partieElt.getChild("IA");
                            List<Case> casesIA = new ArrayList<>();
                            List casesIAXML = IA.getChildren("caseIA");
                            for (int k = 0; k < casesIAXML.size(); k++) {

                                Element caseIAElt = (Element) casesIAXML.get(k);
                                int abs = Integer.parseInt(caseIAElt.getChildText("abs"));
                                int ord = Integer.parseInt(caseIAElt.getChildText("ord"));
                                int test = 0;
                                while(cases.get(test).getAbs() != abs || cases.get(test).getOrd() != ord) {
                                    test++;
                                }
                                casesIA.add(cases.get(test));
                             
                            }
                            ia.setListeCaseATester(casesIA);
                            partie.setIntelligenceArtificielle(ia);

                            // Joueur J2
                            Element j2 = (Element) partieElt.getChild("joueur2");
                            Joueur joueur2 = new JoueurMachine();
                            joueur2.setPartie(partie);
                            joueur2.setNom(j2.getChildText("nom"));
                            joueur2.setNbTirsGagnant(Integer.parseInt(j2.getChildText("nbTirsGagnant")));
                            joueur2.setNbTirsPerdant(Integer.parseInt(j2.getChildText("nbTirsPerdant")));
                            ((JoueurMachine)joueur2).setDifficulte(j2.getChildText("difficulte"));
                            List listCasesBisXML = j2.getChildren("case");
                            ArrayList<Case> casesBis = new ArrayList<>();
                            HashMap<String,Bateau> bateauxJ2 = new HashMap();
                            for (int k = 0; k < listCasesBisXML.size(); k++) {

                                Element caseElt = (Element) listCasesBisXML.get(k);
                                Case c = null;
                                if(caseElt.getChildText("bateau").equals("null")) {

                                    c = new CaseVide();
                                    c.setPartie(partie);

                                } else {

                                    if(bateauxJ2.containsKey(((Bateau)DAOFactory.getInstance().getDAO_Configuration().getAllBateaux(epoque).get(caseElt.getChildText("bateau"))).getNom())) {
                                        // On a deja memorise le bateau, alors on cree une CaseBateau a partir de ce dernier
                                        c = new CaseBateau(bateauxJ2.get(((Bateau)DAOFactory.getInstance().getDAO_Configuration().getAllBateaux(epoque).get(caseElt.getChildText("bateau"))).getNom()),partie);
                                    } else {
                                        // Le bateau n'a pas encore ete memorise, on l'ajoute a la liste
                                        Bateau bateau = new Bateau(((Bateau)DAOFactory.getInstance().getDAO_Configuration().getAllBateaux(epoque).get(caseElt.getChildText("bateau"))));
                                        bateauxJ2.put(bateau.getNom(),bateau);
                                        c = new CaseBateau(bateau,partie);
                                        
                                    }
                                    c.getBateau().setOrientation(Integer.parseInt(caseElt.getChildText("orientation")));
                                    c.getBateau().setNbCasesNonTouchees(Integer.parseInt(caseElt.getChildText("nbCasesNonTouchees")));
                                    ((CaseBateau)c).setImage(caseElt.getChildText("imageBateau"));
                                    
                                }
                                boolean etat = ("1".equals(caseElt.getChildText("etat")));
                                c.setEtat(etat);
                                c.setAbs(Integer.parseInt(caseElt.getChildText("abs")));
                                c.setOrd(Integer.parseInt(caseElt.getChildText("ord")));
                                boolean aPorte = ("1".equals(caseElt.getChildText("aPorte")));
                                c.setPortee(aPorte);
                                casesBis.add(c);

                            }
                            joueur2.setCases((ArrayList<Case>) casesBis);
                            partie.setJ2(joueur2);

                        }
                        
                    }
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DAO_Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JDOMException | IOException ex) {
                    Logger.getLogger(DAO_Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        partie.initialisationPorteeCases();
        return partie;
        
    } // getPartie(String id, Profil profil)
    
    */
} // class DAO_Sauvegarde
