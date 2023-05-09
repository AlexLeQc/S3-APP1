package inventaire;

import ingredients.etat.EtatIngredient;
import ingredients.factory.*;
import ingredients.instanceIngredient.Ingredient;
import ingredients.exceptions.IngredientException;
import ingredients.instanceIngredient.IngredientFactory;
import ingredients.instanceIngredient.TypeIngredient;
import ingredients.instanceIngredient.groupeIngredient;

import java.util.HashMap;

/**
 * Class inventaire singleton
 */
public class Inventaire {

    private static Inventaire instance = null;
    private HashMap<String, Ingredient> entrepot;
    private IngredientFactory ingredientFactory;
    private int size = 0;

    /**
     * Constructeur singleton pour avoir seulement un inventaire
     */
    private Inventaire(){
        entrepot = new HashMap<>();
        ingredientFactory = new IngredientFactory();
    }

    /**
     * Permet de verifier sil y a un inventaire deja creer
     * @return l'instance de l'inventaire sil est deja creer, sinon il en creer un
     */
    public static synchronized Inventaire getInstance(){
        if (instance == null){
            instance = new Inventaire();
        }
        return instance;
    }

    /**
     * Methode pour ajouter un tableau dingredient a l'inventaire
     * @param ingredients tableau d'ingredients
     * @throws IngredientException sil l'ingredient est null
     */

    public void ajoutIngredient(Ingredient[] ingredients) throws IngredientException {
        if(ingredients == null){
            throw new IngredientException("Impossible d'ajouter une liste null à l'inventaire");
        }
        for (Ingredient ingredient : ingredients) {
            ajoutIngredient(ingredient);
            size++;
        }
    }

    /**
     * Methode pour ajouter un ingredient dans l'inventaire
     * @param ingredient un ingredient
     * @throws IngredientException si l'ingredient est null
     */
    public void ajoutIngredient(Ingredient ingredient) throws IngredientException{
        if (ingredient == null) {
            throw new IngredientException("Impossible d'ajouter un ingredient à l'inventaire");
        } else {
            if (entrepot.containsKey(ingredient.getNom())){
                Ingredient ingredient2 = entrepot.get(ingredient.getNom());
                ingredient2.setQuantite(ingredient2.getQuantite() + ingredient.getQuantite()) ;
            } else {
                entrepot.put(ingredient.getNom(), ingredient);
                size++;
            }
        }
    }

    /**
     * Ajouter un ingredient directement dans l'inventaire
     * @param typeIngredient type d'ingredient a ajouter dans l'inventaire
     * @param etat liquide ou solide
     * @param nom nom de l'integredient
     * @throws IngredientException sil y a une erreur dans l'ingredient
     */
    public void ajouter(TypeIngredient typeIngredient, EtatIngredient etat, String nom) throws IngredientException {
        groupeIngredient groupeIng = ingredientFactory.getGroupeIngredient(typeIngredient, etat);
        String typeIngredientString = typeIngredient.toString();
        switch (typeIngredientString) {
            case "FRUIT":
                Ingredient fruit = concretecreatorFruit.creer(groupeIng, nom);
                if (entrepot.containsKey(fruit.getNom())) {
                    Ingredient ingred = entrepot.get(fruit.getNom());
                    ingred.setQuantite(ingred.getQuantite() + fruit.getQuantite());

                } else {
                    entrepot.put(fruit.getNom(), fruit);
                }
                break;
            case "LEGUME":
                Ingredient legume = concretecreatorLegume.creer(groupeIng, nom);
                if (entrepot.containsKey(legume.getNom())) {
                    Ingredient ingred = entrepot.get(legume.getNom());
                    ingred.setQuantite(ingred.getQuantite() + legume.getQuantite());

                } else {
                    entrepot.put(legume.getNom(), legume);
                }
                break;
            case "VIANDE":
                Ingredient viande = concretecreatorViande.creer(groupeIng, nom);
                if (entrepot.containsKey(viande.getNom())) {
                    Ingredient ingred = entrepot.get(viande.getNom());
                    ingred.setQuantite(ingred.getQuantite() + viande.getQuantite());
                } else {
                    entrepot.put(viande.getNom(), viande);
                }
                break;
            case "LAITIER":
                Ingredient laitier = concretecreatorLaitier.creer(groupeIng, nom);
                if (entrepot.containsKey(laitier.getNom())) {
                    Ingredient ingred = entrepot.get(laitier.getNom());
                    ingred.setQuantite(ingred.getQuantite() + laitier.getQuantite());

                } else {
                    entrepot.put(laitier.getNom(), laitier);
                }
                break;
            case "EPICE":
                Ingredient epice = concretecreatorEpice.creer(groupeIng, nom);
                if (entrepot.containsKey(epice.getNom())) {
                    Ingredient ingred = entrepot.get(epice.getNom());
                    ingred.setQuantite(ingred.getQuantite() + epice.getQuantite());

                } else {
                    entrepot.put(epice.getNom(), epice);
                }
                break;
        }
        size++;
    }

    /**
     * Methode pour avoir un ingredient dans l'inventaire
     * @param ingre ingredient a verifier
     * @return le nom de l'ingredient
     */
    public Ingredient getIngredient(Ingredient ingre){
        return entrepot.get(ingre.getNom());
    }

    /**
     * Methode pour avoir la longeur des ingredients
     * @return la quantie d'ingredient dans l'inventaire
     */
    public int getSize(){
        return size;
    }

    /**
     * Methode pour avoir la quantite de ingredient
     * @param ingredient a verifier
     * @return quantite restante de l'ingredient
     */
    public double getIngredientQuantite(Ingredient ingredient){
        if (entrepot.get(ingredient.getNom()) != null) {
            return entrepot.get(ingredient.getNom()).getQuantite();
        } else{
            return 0;
        }
    }

    /**
     * Methode pour enlever la quantite d'ingredients utilisee pour un plat
     * @param recette recette a consommee
     * @param quantitePlat nombre de plat a faire
     * @param proportion proportion du plat
     * @throws IngredientException Si la recette est null, il manque d'ingredients ou autre
     */
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

    /**
     * Methode pour vider l'inventaire
     */
    public void vider() {
        if (instance != null) {
            entrepot.clear();
            instance = null;
            size = 0;
        }
    }

    /**
     * Methode pour afficher l'inventaire
     * @return une String de l'inventaire
     */
    public String toString() {
        return "Inventaire: " + entrepot;
    }
}

