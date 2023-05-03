package ingredients;

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;

public class Laitier extends Ingredient{
    public Laitier(String nom, EtatIngredient etat) throws IngredientException {
        setNom(nom);
        if(etat != null){
            this.etat = etat;
        } else {
            throw new IngredientException("Un état null est impossible");
        }
    }
    public Laitier(String nom, EtatIngredient etat, double quantite) throws IngredientException{
        this.etat = etat;
        this.etat.setQuantite(quantite);
    }
}
