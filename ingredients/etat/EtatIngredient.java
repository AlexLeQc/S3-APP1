package ingredients.etat;

import ingredients.exceptions.IngredientException;

public interface EtatIngredient {

    public double getQuantite();

    public void setQuantite(double quantite) throws IngredientException;


    @Override
    public boolean equals(Object other);
}
