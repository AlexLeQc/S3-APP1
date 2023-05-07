package ingredients.instanceIngredient;

import ingredients.etat.EtatIngredient;

import java.util.HashMap;
import java.util.Map;

public class IngredientFactory {
    private Map<String, groupeIngredient> groupeIngredientMap = new HashMap<>();
    public groupeIngredient getGroupeIngredient(TypeIngredient typeIngredient, EtatIngredient etatIngredient){
        String key = createCompositeKey(typeIngredient, etatIngredient.getEtat());
        groupeIngredient groupeingredient = groupeIngredientMap.get(key);
        if (groupeingredient == null){
            groupeingredient = new groupeIngredient(typeIngredient,etatIngredient);
            groupeIngredientMap.put(key,groupeingredient);
        }
        return groupeingredient;
    }
    public String createCompositeKey(TypeIngredient type, String etat){
        return type.toString()+ "-" + etat;
    }
}
