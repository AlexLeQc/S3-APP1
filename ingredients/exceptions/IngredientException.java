package ingredients.exceptions;

/**
 * Exception pour les ingredients
 */
public class IngredientException extends Exception{
    /**
     * Message d'erreur
     * @param message
     */
    public IngredientException(String message){
        super("IngredientException: " + message);
    }
}
