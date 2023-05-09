package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Viande;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

/**
 * Classe pour creer une viande
 */
public class concretecreatorViande implements creatorIngredient {
    /**
     * Creer une viande
     * @param nom
     * @param etat
     * @return
     * @throws IngredientException
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Viande(nom, etat);
    }

    /**
     * creer une viande
     * @param groupe
     * @param nom
     * @return
     * @throws IngredientException
     */
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Viande(groupe,nom);
    }
}
