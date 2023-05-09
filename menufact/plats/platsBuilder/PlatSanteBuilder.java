package menufact.plats.platsBuilder;

import menufact.plats.PlatSante;
import menufact.exceptions.MenuException;
import menufact.plats.exceptions.PlatException;

public class PlatSanteBuilder extends PlatsBuilder{
    /**
     * Creer un builder platSante
     */

    public PlatSanteBuilder(){
        plat = new PlatSante();
    }

    /**
     * Changer les kcal du platsante
     * @param kcal
     * @return
     * @throws PlatException
     */

    public PlatSanteBuilder buildKcal(double kcal) throws PlatException{
        ((PlatSante) plat).setKcal(kcal);
        return this;
    }

    /**
     * Changer les Chols du plat sante
     * @param chol a changer
     * @return le plat
     * @throws PlatException
     */

    public PlatSanteBuilder buildChol(double chol) throws PlatException {
        ((PlatSante) plat).setChol(chol);
        return this;
    }

    /**
     * changer le gras du plat
     * @param gras a changer
     * @return le plat
     * @throws PlatException
     */

    public PlatSanteBuilder buildGras(double gras) throws PlatException {
        ((PlatSante) plat).setGras(gras);
        return this;
    }

    /**
     * Efface le platSante
     * @return
     */
    @Override
    public PlatSanteBuilder clear()
    {
        plat = new PlatSante();
        return this;
    }

    /**
     * avoir le plat
     * @return le plat
     */

    public PlatSante getPlatSante() {
        return (PlatSante) plat;
    }

}
