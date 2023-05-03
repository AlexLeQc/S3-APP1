package ingredients.factory;

import ingredients.Legume;
import ingredients.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public class concretecreatorLegume implements creatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Legume(nom, etat);
    }
}
