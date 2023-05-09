package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.Ingredient;

/**
 * Class d'ingredient de type viande
 */
public class Viande extends Ingredient {
    /**
     * Constructeur de viande
     * @param nom viande
     * @param etat viande
     * @throws IngredientException
     */
    public Viande(String nom, EtatIngredient etat) throws IngredientException {
        if (etat == null){
            throw new IngredientException("Un état null est impossible");
        }
        setEtat(etat);
        setNom(nom);
    }

    /**
     * Constructeur de viande
     * @param nom viande
     * @param etat viande
     * @param quantite viande
     * @throws IngredientException
     */
    public Viande(String nom, EtatIngredient etat, double quantite) throws  IngredientException{
        setEtat(etat);
        setQuantite(quantite);
        setNom(nom);
    }

    /**
     * Constructeur de viande
     * @param groupeingredient viande
     * @param nom viande
     * @throws IngredientException
     */
    public Viande(groupeIngredient groupeingredient, String nom) throws IngredientException{
        if (groupeingredient == null){
            throw new IngredientException("Un état null est impossible");
        }
        this.groupe = groupeingredient;
        this.nom = nom;
        this.setEtat(groupeingredient.getEtat());
    }
}
