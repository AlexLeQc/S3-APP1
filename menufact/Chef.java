package menufact;

import ingredients.Ingredient;
import ingredients.exceptions.IngredientException;
import inventaire.IngredientPlat;
import inventaire.Inventaire;
import menufact.plats.PlatChoisi;
import menufact.plats.PlatEtat.*;
import menufact.plats.exceptions.PlatException;

public class Chef {
    private static Chef instance;
    private String nom;
    private Chef(String nom) {
        this.nom = nom;
    }
    public static synchronized Chef getInstance(String nom){
        if (instance == null){
            instance = new Chef(nom);
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
    private boolean verifIngredient(PlatChoisi verifPlat) throws PlatException, IngredientException{
        Inventaire inventaire = Inventaire.getInstance();
        IngredientPlat recette = verifPlat.getPlat().getRecette();
        for (Ingredient ingredient : recette.getIngredients()){
            double quantiterequise = verifPlat.getQuantite() * verifPlat.getPlat().getProportion() * ingredient.getQuantite();
            double quantitedispo = inventaire.getIngredient(ingredient.getNom()).getQuantite();
            if (quantitedispo < quantiterequise){
                verifPlat.setEtat(new EtatImpossible());
                throw new IngredientException("Il manque les ingredients : " + ingredient.getNom());
            }
        }
        return true;
    }
    private void preparer(PlatChoisi plataPreparer) throws PlatException, IngredientException{
        plataPreparer.setEtat(new EtatEnPreparation());
        Inventaire inventaire = Inventaire.getInstance();
        IngredientPlat recette = plataPreparer.getPlat().getRecette();
        inventaire.consommerRecette(recette, plataPreparer.getQuantite(), plataPreparer.getPlat().getProportion());
    }
    private void terminer(PlatChoisi plataTerminer) throws PlatException {
        plataTerminer.setEtat(new EtatTerminer());
    }
    private PlatChoisi servir(PlatChoisi plataServir) throws PlatException {
        plataServir.setEtat(new EtatServi());
        return plataServir;
    }
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
    public String toString(){
        return "Chef: {\n\t" +
                "Nom: '" + nom + "'\n" +
                "\n}";
    }
}
