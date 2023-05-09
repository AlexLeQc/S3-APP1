package ingredients.instanceIngredient;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

/**
 * Ingredient de type epice
 */
public class Epice extends Ingredient {
    /**
     * Constructeur d'epice
     * @param nom de lepice
     * @param etat de lepice
     * @throws IngredientException
     */

    public Epice(String nom, EtatIngredient etat) throws IngredientException {
        if (etat == null){
            throw new IngredientException("Un état null est impossible");
        }
        setEtat(etat);
        setNom(nom);
    }

    /**
     * Constructeur de lepice
     * @param nom epice
     * @param etat epice
     * @param quantite epice
     * @throws IngredientException
     */
    public Epice(String nom, EtatIngredient etat, double quantite) throws  IngredientException{
        setEtat(etat);
        setQuantite(quantite);
        setNom(nom);
    }

    /**
     * Contructeur d'epice
     * @param groupeingredient
     * @param nom
     * @throws IngredientException
     */
    public Epice(groupeIngredient groupeingredient, String nom) throws IngredientException{
        if (groupeingredient == null){
            throw new IngredientException("Un état null est impossible");
        }
        this.groupe = groupeingredient;
        this.nom = nom;
        this.setEtat(groupeingredient.getEtat());
    }
}
