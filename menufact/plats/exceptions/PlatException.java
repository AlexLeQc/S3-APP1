package menufact.plats.exceptions;

public class PlatException extends Throwable{
    /**
     * Message d'erreur
     * @param message
     */
    public PlatException(String message){
        super("PlatException: " + message);
    }
}
