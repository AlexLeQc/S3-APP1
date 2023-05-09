package menufact.plats;

import menufact.plats.exceptions.PlatException;

/**
 * Class du plat enfant
 */
public class PlatEnfant extends PlatAuMenu{
    private double proportion;

    /**
     * COnstructeur vide
     */
    public PlatEnfant() {
    }

    /**
     * Constructeur dun plat enfant
     * @param code du plat
     * @param description du plat
     * @param prix du plat
     * @param proportion du plaat
     * @throws PlatException
     */

    public PlatEnfant(int code, String description, double prix, double proportion) throws PlatException {
        super(code, description, prix);
        this.proportion = proportion;
    }

    /**
     * Avoir la proportion
     * @return proportion
     */

    public double getProportion() {
        return proportion;
    }

    /**
     * Changer la proportion de plat
     * @param proportion voulue
     */

    public void setProportion(double proportion){
        this.proportion = proportion;
    }

    /**
     * Afficher les infos du plat enfant
     * @return string des infos
     */

    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }
}
