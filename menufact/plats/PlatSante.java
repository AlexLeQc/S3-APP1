package menufact.plats;

import menufact.plats.PlatAuMenu;
import menufact.plats.exceptions.PlatException;

/**
 * Classe du plat sante
 */
public class PlatSante extends PlatAuMenu {
    private double kcal;
    private double chol;
    private double gras;

    /**
     * Constructeur du plat sante
     * @param code du plat
     * @param description du plat
     * @param prix du plat
     * @param kcal du plat
     * @param chol du plat
     * @param gras du plat
     * @throws PlatException
     */

    public PlatSante(int code, String description, double prix, double kcal, double chol, double gras) throws PlatException {
        super(code, description, prix);
        this.kcal = kcal;
        this.chol = chol;
        this.gras = gras;
    }

    /**
     * Constructeur vide
     */

    public PlatSante() {
    }

    /**
     * Infos de plat sante
     * @return String des infos
     */

    @Override
    public String toString() {
        return "menufact.plats.PlatSante{" +
                "kcal=" + kcal +
                ", chol=" + chol +
                ", gras=" + gras +
                "} " + super.toString();
    }

    /**
     * Avoir les kcal du plat
     * @return kcal
     */

    public double getKcal() {
        return kcal;
    }

    /**
     * Changer les kcal
     * @param kcal voulu
     */

    public void setKcal(double kcal){
        this.kcal = kcal;
    }

    /**
     * Changer Chol du plat
     * @return chol
     */

    public double getChol() {
        return chol;
    }

    /**
     * changer le chol du plat
     * @param chol voulu
     */

    public void setChol(double chol){
        this.chol = chol;
    }

    /**
     * avoir le gras du plat
     * @return gras du plat
     */

    public double getGras() {
        return gras;
    }

    /**
     * Changer le gras du plat sante
     * @param gras voulu
     */

    public void setGras(double gras) {
        this.gras = gras;
    }
}
