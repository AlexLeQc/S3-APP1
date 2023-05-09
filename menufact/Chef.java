package menufact;

import ingredients.instanceIngredient.Ingredient;
import ingredients.exceptions.IngredientException;
import inventaire.IngredientPlat;
import inventaire.Inventaire;
import menufact.plats.PlatChoisi;
import menufact.plats.PlatEtat.*;
import menufact.plats.exceptions.PlatException;

/**
 * Class chef observer et singleton
 */

public class Chef {
    private static Chef instance;
    private String nom;

    /**
     * Constructeur prive pour Singleton
     * @param nom du chef
     */
    private Chef(String nom) {
        this.nom = nom;
    }

    /**
     * Methode pour creer un chef ou retourner celui existent
     * @param nom nom du chef
     * @return l'instance du chef
     */
    public static synchronized Chef getInstance(String nom){
        if (instance == null){
            instance = new Chef(nom);
        }
        return instance;
    }

    /**
     * methode pour voir le nom du chef
     * @return le nom du chef
     */
    public String getNom(){
        return nom;
    }

    /**
     * Methode pour changer le nom du chef
     * @param nom du chef
     */

    public void setNom(String nom){
        if (nom != null){
            this.nom = nom;
        }
    }

    /**
     * Methode pour verifier sil y a assez d'ingredients pour faire la recette
     * @param verifPlat plat a verifier
     * @return true si on peut faire la recette, faux sinon
     * @throws PlatException si le plat ne peux pas etre cuisiner
     * @throws IngredientException S'il manque dingredients
     */
    private boolean verifIngredient(PlatChoisi verifPlat) throws PlatException, IngredientException{
        Inventaire inventaire = Inventaire.getInstance();
        IngredientPlat recette = verifPlat.getPlat().getRecette();
        for (Ingredient ingredient : recette.getIngredients()){
            double quantiterequise = verifPlat.getQuantite() * verifPlat.getPlat().getProportion() * ingredient.getQuantite();
            double quantitedispo = inventaire.getIngredientQuantite(ingredient);
            if (quantitedispo < quantiterequise){
                verifPlat.setEtat(new EtatImpossible());
                throw new IngredientException("Il manque les ingredients : " + ingredient.getNom());
            }
        }
        return true;
    }

    /**
     * Changer letat du plat a preparer
     * @param plataPreparer
     * @throws PlatException
     * @throws IngredientException
     */
    private void preparer(PlatChoisi plataPreparer) throws PlatException, IngredientException{
        plataPreparer.setEtat(new EtatEnPreparation());
        Inventaire inventaire = Inventaire.getInstance();
        IngredientPlat recette = plataPreparer.getPlat().getRecette();
        inventaire.consommerRecette(recette, plataPreparer.getQuantite(), plataPreparer.getPlat().getProportion());
    }

    /**
     * changer l'eta du plat a terminer
     * @param plataTerminer
     * @throws PlatException
     */
    private void terminer(PlatChoisi plataTerminer) throws PlatException {
        plataTerminer.setEtat(new EtatTerminer());
    }

    /**
     * changer letat du plat a servi
     * @param plataServir
     * @return le plat a servir
     * @throws PlatException
     */
    private PlatChoisi servir(PlatChoisi plataServir) throws PlatException {
        plataServir.setEtat(new EtatServi());
        return plataServir;
    }

    /**
     * Changer letat du plat a commande et cuisiner le plat
     * @param plataCuisiner
     * @return
     * @throws IngredientException
     * @throws PlatException
     */
    public PlatChoisi cuisiner(PlatChoisi plataCuisiner)throws IngredientException, PlatException {
        plataCuisiner.setEtat(new EtatCommande());
        if(verifIngredient(plataCuisiner)){
            preparer(plataCuisiner);
            terminer(plataCuisiner);
            return servir(plataCuisiner);
        } else {
            return plataCuisiner;
        }
    }

    /**
     * Retourne les informations du chef
     * @return une String des informations du chef
     */
    public String toString(){
        return "Chef: {Nom: '" + nom + "'" +
                "}";
    }
}
