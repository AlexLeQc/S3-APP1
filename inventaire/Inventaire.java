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
    public void consommerRecette(IngredientPlat recette, int quantitePlat, double proportion) throws IngredientException {
        if (recette == null) {
            throw new IngredientException("Recette ne peut pas être null");
        }
        if (quantitePlat < 0) {
            throw new IngredientException("Impossible de consommer une quantité négative de plats");
        }
        if (proportion < 0) {
            throw new IngredientException("Impossible de consommer des plats à proportion négative");
        }
        if (proportion > 1) {
            throw new IngredientException("Proportion de la recette ne peut pas être > 1");
        }

        for (Ingredient ingredientRecette : recette.getIngredients()) {
            Ingredient ingredientCongelateur = entrepot.get(ingredientRecette.getNom());

            if (ingredientCongelateur == null) {
                throw new IngredientException("Ingrédient n'existe pas dans l'inventaire");
            }
            double qtyRecette = ingredientRecette.getQuantite() * quantitePlat * proportion;
            double qtyInventaire = ingredientCongelateur.getQuantite();

            if (qtyInventaire < qtyRecette) {
                throw new IngredientException("Ingrédients manquants dans l'inventaire");
            }
            ingredientCongelateur.setQuantite(qtyInventaire - qtyRecette);
        }
    }
    public static void vider() {
        if (instance != null) {
            instance.entrepot.clear();
            instance = null;
        }
    }


    public String toString() {
        return "Inventaire: " + entrepot;
    }
}

