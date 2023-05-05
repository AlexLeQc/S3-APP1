package menufact.plats;

import menufact.plats.PlatAuMenu;
import menufact.plats.PlatEtat.EtatCommande;
import menufact.plats.exceptions.PlatException;

public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;

    private EtatCommande etat;

    public PlatChoisi(PlatAuMenu plat, int quantite) throws PlatException {
        this.plat = plat;
        if (quantite >= 0){
            this.quantite = quantite;
        } else {
            throw new PlatException("La quantite est négative");
        }
        this.etat = null;
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws PlatException {
        if (quantite >= 0){
            this.quantite = quantite;
        } else {
            throw new PlatException("La quantite est négative");
        }
    }

    public PlatAuMenu getPlat() {
        return plat;
    }

    public EtatCommande getEtat(){
        return etat;
    }

    public void setEtat(EtatCommande etat2) throws PlatException{
        if (etat == null){
            etat = etat2;
        } else if (etat.changerEtat(etat2)){
            this.etat = etat2;
        } else {
            throw new PlatException("Impossible de changer l'état");
        }
    }
}
