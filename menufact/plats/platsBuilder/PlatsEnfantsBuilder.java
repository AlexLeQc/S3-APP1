package menufact.plats.platsBuilder;

import menufact.exceptions.MenuException;
import menufact.plats.PlatEnfant;
import menufact.plats.exceptions.PlatException;

public class PlatsEnfantsBuilder extends PlatsBuilder {
    /**
     * Builder dun plat enfant
     */

    public PlatsEnfantsBuilder(){
        plat = new PlatEnfant();

    }

    /**
     * Changer les proportions du plat enfant
     * @param proportion a changer
     * @return platEnfant
     * @throws PlatException
     */

    public PlatsEnfantsBuilder creeProportion(double proportion) throws PlatException {
        ((PlatEnfant) plat).setProportion(proportion);
        return this;
    }

    /**
     * Efface le plat enfant
     * @return plat null
     */

    public PlatsBuilder clear(){
        plat = new PlatEnfant();
        return this;
    }

    /**
     * Avoir le plat
     * @return le plat
     */

    public PlatEnfant getPlatEnfant(){
        return (PlatEnfant) plat;
    }
}
