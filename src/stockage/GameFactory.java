
package stockage;

/**
 *
 * @author Aziasso_I
 */
public class GameFactory {
    
    
     /*----------------------- VARIABLES --------------------------------*/
    
    
    private static final GameFactory INSTANCE = new GameFactory();

    
    /*----------------------- INITAILAISATION --------------------------------*/
    
    
    private GameFactory() {

    
    } // GameFactory()

    
    /** Point d'accès pour l'instance unique du singleton
      * @return une instance du singleton 
      */
    public static GameFactory getInstance() {	
        
        return INSTANCE;
        
    } // getInstance()
    
    
  /*-------------------------------- FONCTIONS --------------------------------*/

    
    /**
     * Permet d'avoir acces au fichier de sauvegarde
     * @return la classe permettant l'acces au fichier de sauvegarde
     */
    public DAO_Sauvegarde getDAO_Sauvegarde() {
        
        return new DAO_Sauvegarde();
        
    } // getDAO_Sauvegarde()
    
    
}
