package menufact.plats.platsBuilder;

import ingredients.Ingredient;
import inventaire.IngredientPlat;
import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;
import menufact.plats.exceptions.PlatException;

public class PlatsBuilder {
    protected PlatAuMenu plat;

    public PlatsBuilder(){
        plat = new PlatAuMenu();
    }

    public PlatAuMenu getPlat(){
        return plat;
    }

    public PlatsBuilder clear(){
        plat = new PlatAuMenu();
        return this;
    }

    public PlatsBuilder descriptionBuild(String description){
        plat.setDescription(description);
        return this;
    }

    public PlatsBuilder prixBuild(double prix) throws PlatException {
        plat.setPrix(prix);
        return this;
    }

    public PlatsBuilder IngreBuild(IngredientPlat recette) throws PlatException {
        return this;

    }

    public PlatsBuilder IngreBuild(Ingredient[] recette) throws PlatException {
        plat.setRecette(new IngredientPlat(recette));
        return this;

    }


}
