package ingredients.factory;

import ingredients.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public interface creatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException;
}
