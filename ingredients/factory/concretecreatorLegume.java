package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Legume;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

/**
 * Classe pour instance de legume
 */
public class concretecreatorLegume implements creatorIngredient {
    /**
     * Creer un legume
     * @param nom
     * @param etat
     * @return
     * @throws IngredientException
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Legume(nom, etat);
    }

    /**
     * Creer un legume
     * @param groupe
     * @param nom
     * @return
     * @throws IngredientException
     */

    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Legume(groupe,nom);
    }
}
