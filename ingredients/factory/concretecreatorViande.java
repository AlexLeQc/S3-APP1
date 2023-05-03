package ingredients.factory;

import ingredients.Viande;
import ingredients.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public class concretecreatorViande implements creatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Viande(nom, etat);
    }
}
