package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Viande;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.groupeIngredient;

public class concretecreatorViande implements creatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Viande(nom, etat);
    }
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Viande(groupe,nom);
    }
}
