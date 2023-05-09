package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Fruit;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

/**
 * Classe pour instance de fruit
 */
public class concretecreatorFruit implements creatorIngredient{
    /**
     * Creer un fruit
     * @param nom
     * @param etat
     * @return
     * @throws IngredientException
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Fruit(nom, etat);
    }

    /**
     * creer un fruit
     * @param groupe
     * @param nom
     * @return
     * @throws IngredientException
     */
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Fruit(groupe,nom);
    }
}
