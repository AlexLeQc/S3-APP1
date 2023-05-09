package menufact.plats.platsBuilder;

import ingredients.instanceIngredient.Ingredient;
import inventaire.IngredientPlat;
import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;
import menufact.plats.exceptions.PlatException;

public class PlatsBuilder {
    protected PlatAuMenu plat;

    /**
     * Creer un plat builder
     */
    public PlatsBuilder(){
        plat = new PlatAuMenu();
    }

    /**
     * Avoir le plat
     * @return plat
     */

    public PlatAuMenu getPlat(){
        return plat;
    }

    /**
     * Efface le plat
     * @return
     */

    public PlatsBuilder clear(){
        plat = new PlatAuMenu();
        return this;
    }

    /**
     * Changer la description du plat
     * @param description a changer
     * @return
     */

    public PlatsBuilder descriptionBuild(String description){
        plat.setDescription(description);
        return this;
    }

    /**
     * Changer le prix du plat
     * @param prix a changer
     * @return
     * @throws PlatException
     */

    public PlatsBuilder prixBuild(double prix) throws PlatException {
        plat.setPrix(prix);
        return this;
    }

    /**
     * changer la recette
     * @param recette a changer
     * @return
     * @throws PlatException
     */

    public PlatsBuilder IngreBuild(IngredientPlat recette) throws PlatException {
        plat.setRecette(recette);
        return this;
    }

    /**
     * Changer la recette avec un tableau d'ingredients
     * @param recette
     * @return
     * @throws PlatException
     */

    public PlatsBuilder IngreBuild(Ingredient[] recette) throws PlatException {
        plat.setRecette(new IngredientPlat(recette));
        return this;

    }


}
