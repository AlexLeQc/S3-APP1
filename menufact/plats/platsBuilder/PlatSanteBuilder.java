package menufact.plats.platsBuilder;

import menufact.plats.PlatSante;
import menufact.exceptions.MenuException;

public class PlatSanteBuilder extends PlatsBuilder{

    public PlatSanteBuilder(){
        plat = new PlatSante();
    }

    public PlatSanteBuilder buildKcal(double kcal) throws MenuException{
        ((PlatSante) plat).setKcal(kcal);
        return this;
    }

    public PlatSanteBuilder buildChol(double chol) throws MenuException {
        ((PlatSante) plat).setChol(chol);
        return this;
    }

    public PlatSanteBuilder buildGras(double gras) throws MenuException {
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
