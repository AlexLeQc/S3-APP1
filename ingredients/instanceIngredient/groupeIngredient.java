package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;

public class groupeIngredient {
    private TypeIngredient type;
    private EtatIngredient etat;

    /**
     * Avoir letat
     * @param typeIngredient
     * @param etatIngredient
     */
    public groupeIngredient(TypeIngredient typeIngredient, EtatIngredient etatIngredient){
        this.type = typeIngredient;
        this.etat = etatIngredient;
    }

    /**
     * Avoir l'Etat de l'ingredient
     * @return l'etat de l'ingredient
     */
    public EtatIngredient getEtat(){
        return etat;
    }

    /**
     * Avoir le type d'ingredient
     * @return le type de l'ingredient
     */

    public TypeIngredient getType() {
        return type;
    }
}
