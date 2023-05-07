package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.Laitier;
import ingredients.instanceIngredient.groupeIngredient;

public class concretecreatorLaitier implements creatorIngredient{
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Laitier(nom, etat);
    }
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Laitier(groupe, nom);
    }
}
