package menufact.plats;

import inventaire.IngredientPlat;
import menufact.plats.exceptions.PlatException;

/**
 * Class du plat au menu
 */
public class PlatAuMenu {
    private int code;
    private String description;
    private double prix;

    private IngredientPlat recette;

    /**
     * constructeur du plat au menu
     * @param code code de reference
     * @param description du plat
     * @param prix du plat
     * @throws PlatException si le prix est negative
     */

    public PlatAuMenu(int code, String description, double prix) throws PlatException {
        this.code = code;
        this.description = description;
        if (prix >=0){
            this.prix = prix;
        } else {
           throw new PlatException("Le prix est négative");
        }

    }

    /**
     * Constructeur vide
     */

    public PlatAuMenu() {

    }

    /**
     * Affiche les infos du menu
     * @return string du menu
     */

    @Override
    public String toString() {
        return "menufact.plats.PlatAuMenu{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                "}";
    }

    /**
     * avoir le code du plataumenu
     * @return le code
     */

    public int getCode() {
        return code;
    }

    /**
     * Changer le code du plat au menu
     * @param code a changer
     */

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * avoir la descritpion du plat
     * @return la description
     */

    public String getDescription() {
        return description;
    }

    /**
     * Changer la descritpion
     * @param description voulue
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * avoir le prix du plat du menu
     * @return le prix
     */

    public double getPrix() {
        return prix;
    }

    /**
     * changer le prix du plat
     * @param prix voulu
     */

    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * avoir la proportion du plat
     * @return proportion
     */
    public double getProportion(){
        return 1.0;
    }

    /**
     * avoir la recette du plat
     * @return la recette
     */
    public IngredientPlat getRecette(){
        return recette;
    }

    /**
     * set la recette du plat au menu
     * @param recette a associer au plat
     * @throws PlatException si la recette est null
     */

    public void setRecette(IngredientPlat recette) throws PlatException {
        if (recette == null){
            throw new PlatException("Une recette ne peux pas etre null");
        }
        this.recette = recette;

    }
}
