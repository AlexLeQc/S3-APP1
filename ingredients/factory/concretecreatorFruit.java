package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Fruit;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

public class concretecreatorFruit implements creatorIngredient{
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Fruit(nom, etat);
    }
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Fruit(groupe,nom);
    }
}
