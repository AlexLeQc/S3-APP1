package menufact.facture.state;

public class FactureEtatPayee implements FactureEtat{
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return etat2 instanceof FactureEtatFermee || etat2 instanceof FactureEtatOuverte;
    }
}
