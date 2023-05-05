package menufact.plats.platsBuilder;

import ingredients.Ingredient;
import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;

public class PlatsBuilder {
    protected PlatAuMenu plat;

    public PlatsBuilder(){
        plat = new PlatAuMenu();
    }

    public PlatAuMenu getResult(){
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

    public PlatsBuilder prixBuild(double prix) throws MenuException {
        plat.setPrix(prix);
        return this;
    }

    public PlatsBuilder IngreBuild(){
        return this;
    }





}
