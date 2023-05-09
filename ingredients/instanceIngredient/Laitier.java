package ingredients.instanceIngredient;
/**
 * Ingredient de type laitier
 */

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.Ingredient;

public class Laitier extends Ingredient {
    /**
     * Constructeur de laitier
     * @param nom
     * @param etat
     * @throws IngredientException
     */
    public Laitier(String nom, EtatIngredient etat) throws IngredientException {
        if (etat == null){
            throw new IngredientException("Un état null est impossible");
        }
        setEtat(etat);
        setNom(nom);
    }

    /**
     * Constructeur de produit laitier
     * @param nom
     * @param etat
     * @param quantite
     * @throws IngredientException
     */
    public Laitier(String nom, EtatIngredient etat, double quantite) throws  IngredientException{
        setEtat(etat);
        setQuantite(quantite);
        setNom(nom);
    }

    /**
     * Construcetur de produit laitier
     * @param groupeingredient
     * @param nom
     * @throws IngredientException
     */
    public Laitier(groupeIngredient groupeingredient, String nom) throws IngredientException{
        if (groupeingredient == null){
            throw new IngredientException("Un état null est impossible");
        }
        this.groupe = groupeingredient;
        this.nom = nom;
        this.setEtat(groupeingredient.getEtat());
    }
}
