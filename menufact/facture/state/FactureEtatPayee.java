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

    public String toString(){
        return "Facture etat payee";
    }
}
