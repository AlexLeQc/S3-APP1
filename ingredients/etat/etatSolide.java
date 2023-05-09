package ingredients.etat;
/**
 * Classe des ingredients solide
 */

import ingredients.exceptions.IngredientException;

public class etatSolide implements EtatIngredient{
    private double quantiteSolide;
    private String etat = "solide";

    /**
     * Constructeur des ingredients solides
     * @param quantite
     * @throws IngredientException
     */
    public etatSolide(double quantite) throws IngredientException {
        setQuantite(quantite);
    }

    /**
     * avoir la quantite de ingredients
     * @return quantite
     */
    public double getQuantite(){
        return quantiteSolide;
    }

    /**
     * Changer la quantite d'Ingredient
     * @param quantite a changer
     * @throws IngredientException
     */
    public void setQuantite(double quantite) throws IngredientException{
        if (quantite < 0){
            throw new IngredientException("La quantité ne peux pas être négative");
        } else {
            quantiteSolide = quantite;
        }
    }

    /**
     * Avoir letat de l'ingredient
     * @return etat
     */
    public String getEtat(){
        return etat;
    }

    /**
     * Afficher les infos de l'ingredient solide
     * @return String des infos
     */
    public String toString() {
        return "'Solide' :  {\n\t 'Qty (kg)':" + quantiteSolide + "\n}";
    }

    /**
     * Voir si les ingredients sont pareil
     * @param other
     * @return vrai si oui, faux sinon
     */
    public boolean equals(Object other){
        if (other == this){
            return true;
        }
        if (other instanceof etatSolide){
            return this.getQuantite() == ((EtatIngredient) other).getQuantite();
        } else {
            return false;
        }
    }
}
