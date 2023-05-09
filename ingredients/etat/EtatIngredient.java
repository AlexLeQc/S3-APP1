package ingredients.etat;
/**
 * Interface des etats des ingredients
 */

import ingredients.exceptions.IngredientException;

public interface EtatIngredient {
    /**
     * Methode virtuelle pour avoir la quantite
     * @return quantite
     */

    public double getQuantite();

    /**
     * methode virtuelle pour changer quantite
     * @param quantite a changer
     * @throws IngredientException
     */

    public void setQuantite(double quantite) throws IngredientException;

    /**
     * Methode virtuelle pour avoir letat
     * @return etat
     */
    public String getEtat();

    /**
     * Methode virtuelle pour voir si les ingredient sont egal
     * @param other
     * @return vrai si vrai, faux si faux
     */
    @Override
    public boolean equals(Object other);
}
