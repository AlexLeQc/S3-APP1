package menufact.facture.state;

/**
 * Changer etat facture
 */
public class FactureEtatPayee implements FactureEtat{
    /**
     * Verifie si on peut changer d'etat
     * @param etat2 nouvelle etat
     * @return Vrai si on peut, faux sinon
     */
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return false;
    }

    /**
     * Methode pour obtenir le string de l etat de la facture
     * @return le string de l etat de la facture
     */
    public String toString(){
        return "Facture etat payee";
    }
}
