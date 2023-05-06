package menufact.plats;

import menufact.plats.exceptions.PlatException;

public class PlatEnfant extends PlatAuMenu{
    private double proportion;

    public PlatEnfant() {
    }

    public PlatEnfant(int code, String description, double prix, double proportion) throws PlatException {
        super(code, description, prix);
        this.proportion = proportion;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion){
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }
}
