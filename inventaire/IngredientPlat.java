package inventaire;

import ingredients.instanceIngredient.Ingredient;
import ingredients.exceptions.IngredientException;

import java.util.ArrayList;
import java.util.Arrays;

public class IngredientPlat {
    private ArrayList<Ingredient> recette;

    public IngredientPlat(ArrayList<Ingredient> ingredients) {
        this.recette = ingredients;
    }

    public IngredientPlat(Ingredient[] ingredients) {
        this.recette = new ArrayList<>(Arrays.asList(ingredients));
    }

    public ArrayList<Ingredient> getIngredients() {
        return recette;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.recette = ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.recette = new ArrayList<>(Arrays.asList(ingredients));
    }

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

    public String toString() {
        return "Recette:" + recette;
    }
}