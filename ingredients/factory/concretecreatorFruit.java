package ingredients.factory;

import ingredients.Epice;
import ingredients.Fruit;
import ingredients.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public class concretecreatorFruit implements creatorIngredient{
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Fruit(nom, etat);
    }
}
