package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory des ingredients
 */
public class IngredientFactory {
    private Map<String, groupeIngredient> groupeIngredientMap = new HashMap<>();

    /**
     * Avoir le groupe de l'ingredient
     * @param typeIngredient le type de l'ingredient
     * @param etatIngredient l'etat de l'ingredient
     * @return
     */
    public groupeIngredient getGroupeIngredient(TypeIngredient typeIngredient, EtatIngredient etatIngredient){
        String key = createCompositeKey(typeIngredient, etatIngredient.getEtat());
        groupeIngredient groupeingredient = groupeIngredientMap.get(key);
        if (groupeingredient == null){
            groupeingredient = new groupeIngredient(typeIngredient,etatIngredient);
            groupeIngredientMap.put(key,groupeingredient);
        }
        return groupeingredient;
    }

    /**
     * Methode pour creer la key
     * @param type Object de la classe TypeIngredient
     * @param etat String de l etat
     * @return la compositekey creer
     */
    public String createCompositeKey(TypeIngredient type, String etat){
        return type.toString()+ "-" + etat;
    }
}
