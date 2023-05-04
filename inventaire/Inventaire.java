package inventaire;

import ingredients.Ingredient;
import ingredients.exceptions.IngredientException;

import java.util.HashMap;

public class Inventaire {
    private static Inventaire instance;
    private HashMap<String, Ingredient> entrepot;
    public static synchronized Inventaire getInstance(){
        if (instance == null){
            instance = new Inventaire();
        }
        return instance;
    }
    private Inventaire(){
        entrepot = new HashMap<>();
    }
    public void ajoutIngredient(Ingredient[] ingredients) throws IngredientException {
        if(ingredients == null){
            throw new IngredientException("Impossible d'ajouter une liste null à l'inventaire");
        }
        for (Ingredient ingredient : ingredients) {
            ajoutIngredient(ingredients);
        }
    }
    public void ajoutIngredient(Ingredient ingredient) throws IngredientException{
        if (ingredient == null) {
            throw new IngredientException("Impossible d'ajouter un ingredient à l'inventaire");
        } else {
            if (entrepot.containsKey(ingredient.getNom())){
                Ingredient ingredient2 = entrepot.get(ingredient.getNom());
                ingredient2.setQuantite(ingredient2.getQuantite() + ingredient.getQuantite()) ;
            } else {
                entrepot.put(ingredient.getNom(), ingredient);
            }
        }
    }
    public Ingredient getIngredient(String ingredientNom){
        return entrepot.get(ingredientNom);
    }
    public int getQuantiteIngredient(){
        return entrepot.size();
    }
}
