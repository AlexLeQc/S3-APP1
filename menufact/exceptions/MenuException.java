package menufact.exceptions;

public class MenuException extends Exception{
    /**
     * Message d'Erreur si probleme
     * @param message
     */

    public MenuException(String message){
        super("MenuException: " + message);
    }
}

