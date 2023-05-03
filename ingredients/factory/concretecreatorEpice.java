package ingredients.factory;

import ingredients.Laitier;
import ingredients.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public class concretecreatorEpice implements creatorIngredient{
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Laitier(nom, etat);
    }
}
