package ingredients.factory;

import ingredients.Epice;
import ingredients.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public class concretecreatorLaitier implements creatorIngredient{
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Epice(nom, etat);
    }
}
