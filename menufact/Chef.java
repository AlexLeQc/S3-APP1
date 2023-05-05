package menufact;

import ingredients.exceptions.IngredientException;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

public class Chef {
    private static Chef instance;
    private String nom;
    private Chef() {
    }
    public static synchronized Chef getInstance(){
        if (instance == null){
            instance = new Chef();
        }
        return instance;
    }
    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        if (nom != null){
            this.nom = nom;
        }
    }
    public PlatChoisi cuisiner(PlatChoisi plataCuisiner)throws IngredientException, PlatException {
        //plataCuisiner.setEtat(new Commande);
        return plataCuisiner;
    }
}
