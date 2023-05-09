package ingredients.factory;

import ingredients.instanceIngredient.Epice;
import ingredients.instanceIngredient.Ingredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.Laitier;
import ingredients.instanceIngredient.groupeIngredient;

/**
 * Classe pour instance de produit laitier
 */
public class concretecreatorLaitier implements creatorIngredient{
    /**
     * Creer un produit laitier
     * @param nom
     * @param etat
     * @return
     * @throws IngredientException
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Laitier(nom, etat);
    }

    /**
     * Creer un produit laitier
     * @param groupe
     * @param nom
     * @return
     * @throws IngredientException
     */
    public static Ingredient creer(groupeIngredient groupe, String nom) throws IngredientException{
        if (groupe == null){
            throw new IngredientException("Le groupe d'ingredient ne peut pas etre null");
        }
        return new Laitier(groupe, nom);
    }
}
