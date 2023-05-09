package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.Ingredient;

/**
 * Class des ingredients de type legume
 */
public class Legume extends Ingredient {
    /**
     * Constructeur de legume
     * @param nom legume
     * @param etat legume
     * @throws IngredientException
     */
    public Legume(String nom, EtatIngredient etat) throws IngredientException {
        if (etat == null){
            throw new IngredientException("Un état null est impossible");
        }
        setEtat(etat);
        setNom(nom);
    }

    /**
     * COnstructeur de type legume
     * @param nom legume
     * @param etat legume
     * @param quantite legume
     * @throws IngredientException
     */
    public Legume(String nom, EtatIngredient etat, double quantite) throws  IngredientException{
        setEtat(etat);
        setQuantite(quantite);
        setNom(nom);
    }

    /**
     * Constructeur de type legume
     * @param groupeingredient legume
     * @param nom legume
     * @throws IngredientException
     */
    public Legume(groupeIngredient groupeingredient, String nom) throws IngredientException{
        if (groupeingredient == null){
            throw new IngredientException("Un état null est impossible");
        }
        this.groupe = groupeingredient;
        this.nom = nom;
        this.setEtat(groupeingredient.getEtat());
    }
}
