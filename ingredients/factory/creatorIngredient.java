package ingredients.factory;

import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

/**
 * Creer un ingredient
 */
public interface creatorIngredient {
    /**
     * Creer ingredient
     * @param nom
     * @param etat
     * @return
     * @throws IngredientException
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException;

    /**
     * Creer ingredient
     * @param groupe
     * @param nom
     * @return
     * @throws IngredientException
     */

    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException {
        return null;
    }
}
