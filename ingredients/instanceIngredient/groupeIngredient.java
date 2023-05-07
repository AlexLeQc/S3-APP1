package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;

public class groupeIngredient {
    private TypeIngredient type;
    private EtatIngredient etat;
    public groupeIngredient(TypeIngredient typeIngredient, EtatIngredient etatIngredient){
        this.type = typeIngredient;
        this.etat = etatIngredient;
    }
    public EtatIngredient getEtat(){
        return etat;
    }

    public TypeIngredient getType() {
        return type;
    }
}
