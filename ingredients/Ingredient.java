package ingredients;

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
public abstract class Ingredient {
    protected EtatIngredient etat;
    private String nom;
    public Ingredient() {
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        }
    }
    public double getQuantite(){
        return etat.getQuantite();
    }
    public void setQuantite(double quantite) throws IngredientException {
        etat.setQuantite(quantite);
    }
    public EtatIngredient getEtat(){
        return etat;
    }
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

    public String toString(){
        return "Ingredient : " + this.getNom() + " Etat : " + etat + "\n";
    }
}