package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

/**
 * Ingredient de type fruit
 */
public class Fruit extends Ingredient {
    /**
     * Constructeur de fruit
     * @param nom fruit
     * @param etat fruit
     * @throws IngredientException
     */
    public Fruit(String nom, EtatIngredient etat) throws IngredientException {
        if (etat == null){
            throw new IngredientException("Un état null est impossible");
        }
        setEtat(etat);
        setNom(nom);
    }

    /**
     * Constructeur de fruit
     * @param nom fruit
     * @param etat fruit
     * @param quantite fruit
     * @throws IngredientException
     */
    public Fruit(String nom, EtatIngredient etat, double quantite) throws  IngredientException{
        setEtat(etat);
        setQuantite(quantite);
        setNom(nom);
    }

    /**
     * Constructeur fruit
     * @param groupeingredient fruit
     * @param nom fruit
     * @throws IngredientException
     */
    public Fruit(groupeIngredient groupeingredient, String nom) throws IngredientException{
        if (groupeingredient == null){
            throw new IngredientException("Un état null est impossible");
        }
        this.groupe = groupeingredient;
        this.nom = nom;
        this.setEtat(groupeingredient.getEtat());
    }
}
