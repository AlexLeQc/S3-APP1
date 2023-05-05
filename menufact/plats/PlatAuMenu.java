package menufact.plats;

import inventaire.IngredientPlat;
import menufact.plats.exceptions.PlatException;

public class PlatAuMenu {
    private int code;
    private String description;
    private double prix;

    private IngredientPlat recette;

    public PlatAuMenu(int code, String description, double prix) {
        this.code = code;
        this.description = description;
        this.prix = prix;
    }

    public PlatAuMenu() {
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatAuMenu{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                "}\n";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public IngredientPlat getRecette(){
        return recette;
    }

    public void setRecette(IngredientPlat recette) throws PlatException {
        if (recette == null){
            throw new PlatException("Une recette ne peux pas etre null");
        }
        this.recette = recette;

    }
}
