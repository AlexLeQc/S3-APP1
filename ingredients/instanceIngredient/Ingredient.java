package ingredients.instanceIngredient;
/**
 * Class abstraite des ingredients
 */

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
public abstract class Ingredient {
    protected EtatIngredient etat;
    protected String nom;
    protected groupeIngredient groupe;

    /**
     * Constructeur
     */
    public Ingredient() {
    }

    /**
     * Avoir le nom
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Changer le nom
     * @param nom
     */
    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        }
    }

    /**
     * Avoir la quantite
     * @return
     */
    public double getQuantite(){
        return etat.getQuantite();
    }

    /**
     * Changer la quantite
     * @param quantite
     * @throws IngredientException
     */
    public void setQuantite(double quantite) throws IngredientException {
        etat.setQuantite(quantite);
    }

    /**
     * Changer l'Etat
     * @param etat
     */
    public void setEtat(EtatIngredient etat){
        this.etat = etat;
    }

    /**
     * Avoir l'etat
     * @return
     */
    public EtatIngredient getEtat(){
        return etat;
    }

    /**
     * Comparaison
     * @param other
     * @return
     */
    @Override
    public  boolean equals(Object other){
        if (other == this){
           return true;
        }
        if (other instanceof Ingredient){
            return etat.equals(((Ingredient)other).getEtat()) && nom.equals(((Ingredient) other).getNom());
        } else {
            return false;
        }
    }

    /**
     * Methode pour obtenir le groupe
     * @return le groupe
     */
    public groupeIngredient getGroupe(){
        return groupe;
    }

    /**
     * Methode pour obtenir le String de l ingredient
     * @return le string de l ingredient
     */
    public String toString(){
        return "Ingredient : " + this.getNom() + " Etat : " + etat + "\n";
    }
}