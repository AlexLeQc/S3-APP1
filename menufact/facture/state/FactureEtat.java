package menufact.facture.state;
/**
 * Class interface pour l'etat des facture
 */
public interface FactureEtat {
    /**
     * Methode interface pour changer l'etat
     * @param etat2
     * @return
     */
    public boolean changerEtat(FactureEtat etat2);
}
