package menufact.plats;

import menufact.plats.PlatAuMenu;
import menufact.plats.PlatEtat.EtatCommande;
import menufact.plats.PlatEtat.EtatPlat;
import menufact.plats.exceptions.PlatException;

/**
 * Class du plat choisi
 */
public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;

    private EtatPlat etat;

    /**
     * constructeur du plat
     * @param plat plat du menu choisi
     * @param quantite nombre de plat
     * @throws PlatException si la quantite est negative
     */

    public PlatChoisi(PlatAuMenu plat, int quantite) throws PlatException {
        this.plat = plat;
        if (quantite >= 0){
            this.quantite = quantite;
        } else {
            throw new PlatException("La quantite est négative");
        }
        this.etat = null;
    }

    /**
     * afficher les info du plat choisi
     * @return string des informations du plat
     */
    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    /**
     * Avoir la quantite de plat
     * @return quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Changer la quantite de plat
     * @param quantite voulu
     * @throws PlatException si la quantite est negative
     */

    public void setQuantite(int quantite) throws PlatException {
        if (quantite >= 0){
            this.quantite = quantite;
        } else {
            throw new PlatException("La quantite est négative");
        }
    }

    /**
     * avoir le plat
     * @return le plat
     */

    public PlatAuMenu getPlat() {
        return plat;
    }

    /**
     * avoir l'etat du plat
     * @return etat
     */
    public EtatPlat getEtat(){
        return etat;
    }

    /**
     * changer letat du plat
     * @param etat2 nouvelle etat
     * @throws PlatException si on peux pas changer letat
     */
    public void setEtat(EtatPlat etat2) throws PlatException{
        if (etat == null){
            etat = etat2;
        } else if (etat.changerEtat(etat2)){
            this.etat = etat2;
        } else {
            throw new PlatException("Impossible de changer l'état");
        }
    }
}
