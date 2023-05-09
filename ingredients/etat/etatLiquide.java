package ingredients.etat;

import ingredients.exceptions.IngredientException;

/**
 * Classe des ingredients liquide
 */
public class etatLiquide implements EtatIngredient{

    private double quantiteLiquide;
    private String etat = "liquide";

    /**
     * Constructeur de etatliquide
     * @param quantite
     * @throws IngredientException
     */
    public etatLiquide(double quantite) throws IngredientException{
        setQuantite(quantite);
    }

    /**
     * Avoir la quantite de liquide
     * @return quantite en mL
     */
    public double getQuantite(){
        return quantiteLiquide;
    }

    /**
     * changer la quantite
     * @param quantite a changer
     * @throws IngredientException
     */
    public void setQuantite(double quantite) throws IngredientException{
        if (quantite < 0){
            throw new IngredientException("La quantité ne peux pas être négative");
        } else {
            quantiteLiquide = quantite;
        }
    }

    /**
     * avoir letat de lingredient
     * @return etat
     */
    public String getEtat(){
        return etat;
    }

    /**
     * Affiche les infos des ingredient
     * @return String des infos
     */
    public String toString() {
        return "'Liquide' :  {\n\t 'Qty (L)':" + quantiteLiquide + "\n}";
    }

    /**
     * Regarder si les ingredients au volant
     * @param other 
     * @return vrai si oui, faux sinon
     */
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
