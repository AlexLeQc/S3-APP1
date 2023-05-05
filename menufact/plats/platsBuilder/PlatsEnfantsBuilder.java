package menufact.plats.platsBuilder;

import menufact.exceptions.MenuException;
import menufact.plats.PlatEnfant;
import menufact.plats.exceptions.PlatException;

public class PlatsEnfantsBuilder extends PlatsBuilder {

    public PlatsEnfantsBuilder(){
        plat = new PlatEnfant();

    }

    public PlatsEnfantsBuilder creeProportion(double proportion) throws PlatException {
        ((PlatEnfant) plat).setProportion(proportion);
        return this;
    }

    public PlatsBuilder clear(){
        plat = new PlatEnfant();
        return this;
    }

    public PlatEnfant getPlatEnfant(){
        return (PlatEnfant) plat;
    }
}
