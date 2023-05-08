package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;
import ingredients.exceptions.IngredientException;
public abstract class Ingredient {
    protected EtatIngredient etat;
    protected String nom;
    protected groupeIngredient groupe;
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
    public void setEtat(EtatIngredient etat){
        this.etat = etat;
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

    public groupeIngredient getGroupe(){
        return groupe;
    }

    public String toString(){
        return "Ingredient : " + this.getNom() + " Etat : " + etat + "\n";
    }
}