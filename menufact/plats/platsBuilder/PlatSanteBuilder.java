package menufact.plats.platsBuilder;

import menufact.plats.PlatSante;
import menufact.exceptions.MenuException;
import menufact.plats.exceptions.PlatException;

public class PlatSanteBuilder extends PlatsBuilder{

    public PlatSanteBuilder(){
        plat = new PlatSante();
    }

    public PlatSanteBuilder buildKcal(double kcal) throws PlatException{
        ((PlatSante) plat).setKcal(kcal);
        return this;
    }

    public PlatSanteBuilder buildChol(double chol) throws PlatException {
        ((PlatSante) plat).setChol(chol);
        return this;
    }

    public PlatSanteBuilder buildGras(double gras) throws PlatException {
        ((PlatSante) plat).setGras(gras);
        return this;
    }

    @Override
    public PlatSanteBuilder clear()
    {
        plat = new PlatSante();
        return this;
    }

    public PlatSante getPlatSante() {
        return (PlatSante) plat;
    }

}
