package ingredients;
import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
public class Epice extends Ingredient{

    public Epice(String nom, EtatIngredient etat) throws  IngredientException{
        setNom(nom);
        if(etat != null){
            this.etat = etat;
        } else {
            throw new IngredientException("Un état null est impossible");
        }
    }
    public Epice(String nom, EtatIngredient etat, double quantite) throws  IngredientException{
        if(etat != null){
            this.etat = etat;
        } else {
            throw new IngredientException("Un état null est impossible");
        }
        this.etat.setQuantite(quantite);
    }
}
