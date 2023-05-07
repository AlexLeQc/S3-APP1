package ingredients.etat;

import ingredients.exceptions.IngredientException;

public class etatSolide implements EtatIngredient{
    private double quantiteSolide;
    private String etat = "solide";
    public etatSolide(double quantite) throws IngredientException {
        setQuantite(quantite);
    }
    public double getQuantite(){
        return quantiteSolide;
    }
    public void setQuantite(double quantite) throws IngredientException{
        if (quantite < 0){
            throw new IngredientException("La quantité ne peux pas être négative");
        } else {
            quantiteSolide = quantite;
        }
    }
    public String getEtat(){
        return etat;
    }
    public String toString() {
        return "'Solide' :  {\n\t 'Qty (kg)':" + quantiteSolide + "\n}";
    }
    public boolean equals(Object other){
        if (other == this){
            return true;
        }
        if (other instanceof etatLiquide){
            return this.getQuantite() == ((EtatIngredient) other).getQuantite();
        } else {
            return false;
        }
    }
}
