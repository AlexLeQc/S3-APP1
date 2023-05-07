package ingredients.etat;

import ingredients.exceptions.IngredientException;

public class etatLiquide implements EtatIngredient{
    private double quantiteLiquide;
    private String etat = "liquide";
    public etatLiquide(double quantite) throws IngredientException{
        setQuantite(quantite);
    }
    public double getQuantite(){
        return quantiteLiquide;
    }
    public void setQuantite(double quantite) throws IngredientException{
        if (quantite < 0){
            throw new IngredientException("La quantité ne peux pas être négative");
        } else {
            quantiteLiquide = quantite;
        }
    }
    public String getEtat(){
        return etat;
    }
    public String toString() {
        return "'Liquide' :  {\n\t 'Qty (L)':" + quantiteLiquide + "\n}";
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
