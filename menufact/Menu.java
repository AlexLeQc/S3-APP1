package menufact;

import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;

import java.util.ArrayList;

public class Menu {
    private static Menu instance;
    private String description;
    private int courant;
    private ArrayList<PlatAuMenu> plat = new ArrayList<PlatAuMenu>();

    private Menu(String description) {
        this.description = description;
    }
    public static synchronized Menu getInstance(){
        if (instance == null){
            instance = new Menu(null);
        }
        return instance;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        if (description != null){
            this.description = description;
        }
    }
    void ajoute (PlatAuMenu p) throws MenuException {
        if (p != null){
            plat.add(p);
        }
    }

    public void position(int i) {
        courant = i;
    }

    public PlatAuMenu platCourant() {
        return plat.get(courant);
    }

    public void positionSuivante() throws MenuException {
        if (courant+1 >= plat.size())
            throw new MenuException("On depasse le nombre maximale de plats.");
        else
            courant++;
    }

    public void positionPrecedente() throws MenuException {
        if (courant-1 < 0)
            throw new MenuException("On depasse le nombre minimale de plats");
        else
            courant--;
    }


    @Override
    public String toString() {
        return "menufact.Menu{" +
                "description='" + description + '\'' +
                ", courant=" + courant +
                ", plat=" + "\n" + plat +
                '}';
    }
}
