package menufact.facture.state;
/**
 * Changer etat facture
 */
public class FactureEtatFermee implements FactureEtat {
    /**
     * Verifie si on peut changer d'etat
     * @param etat2
     * @return
     */
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return etat2 instanceof FactureEtatPayee || etat2 instanceof FactureEtatOuverte;
    }

    /**
     * Methode pour obtenir le string de l etat de la facture
     * @return le string de l etat de la facture
     */
    public String toString(){
        return "Facture etat fermee";
    }
}
