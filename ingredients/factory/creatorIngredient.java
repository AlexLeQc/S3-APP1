package ingredients.factory;

import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

public interface creatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException;

    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException {
        return null;
    }
}
