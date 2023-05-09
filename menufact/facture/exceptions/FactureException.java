package menufact.facture.exceptions;

/**
 * Exception des factures
 */
public class FactureException extends Exception{
    /**
     * Message d'erreur sil y en a
     * @param message
     */

    public FactureException(String message){
        super("FactureException: " + message);
   }
}
