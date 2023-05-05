package menufact.facture.state;

public class FactureEtatFermee implements FactureEtat {
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return etat2 instanceof FactureEtatPayee || etat2 instanceof FactureEtatOuverte;
    }
}
