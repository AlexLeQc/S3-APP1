package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Laitier;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;


/**
 * Class pour instance de epice
 */
public class concretecreatorEpice implements creatorIngredient{
    /**
     * Constructeur depice
     * @param nom
     * @param etat
     * @return
     * @throws IngredientException
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Epice(nom, etat);
    }

    /**
     * Constructeur d'epice
     * @param groupe
     * @param nom
     * @return
     * @throws IngredientException
     */
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Epice(groupe,nom);
    }
}
