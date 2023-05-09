package inventaire;

import ingredients.instanceIngredient.Ingredient;
import ingredients.exceptions.IngredientException;

import java.util.ArrayList;
import java.util.Arrays;

public class IngredientPlat {

    private ArrayList<Ingredient> recette;

    /**
     * Constructeur de recette qui prend un array d'ingredients
     * @param ingredients
     */
    public IngredientPlat(ArrayList<Ingredient> ingredients) {
        this.recette = ingredients;
    }

    /**
     * Constructeur de recette avec un tableau d'ingredients
     * @param ingredients
     */
    public IngredientPlat(Ingredient[] ingredients) {
        this.recette = new ArrayList<>(Arrays.asList(ingredients));
    }

    /**
     * Methode pour avoir les ingredients de la recette
     * @return la recette, donc un array des ingredients quelle contient
     */
    public ArrayList<Ingredient> getIngredients() {
        return recette;
    }

    /**
     * Methode pour set les ingredients de la recette avec un array d'ingredients
     * @param ingredients
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.recette = ingredients;
    }

    /**
     * Methode pour set les ingredients de la recette avec un tableau d'ingredients
     * @param ingredients
     */
    public void setIngredients(Ingredient[] ingredients) {
        this.recette = new ArrayList<>(Arrays.asList(ingredients));
    }

    /**
     * methode pour ajouter un ingredient a la recette
     * @param ingredient
     */
    public void addIngredients(Ingredient ingredient) {
        for (Ingredient ing : recette) {
            if (ing.getNom().equals(ingredient.getNom())) {
                try {
                    ing.setQuantite(ing.getQuantite() + ingredient.getQuantite());
                } catch (IngredientException ie) {

                }
            }
        }
        recette.add(ingredient);
    }

    /**
     * Methode pour afficher la recette
     * @return un string de la recette
     */
    public String toString() {
        return "Recette:" + recette;
    }

    /**
     * Permet de rendre 2 recette pareille
     * @param other un autre objet
     * @return true si ca marche, false sinon
     */

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof IngredientPlat) {
            IngredientPlat otherPlat = (IngredientPlat) other;
            return recette.equals(otherPlat.recette);
        }
        return false;
    }
}

