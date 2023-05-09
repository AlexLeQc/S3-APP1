package menufact;

import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;

import java.util.ArrayList;

/**
 * Menu du restaurant
 */
public class Menu {
    private static Menu instance;
    private String description;
    private int courant;

    private ArrayList<PlatAuMenu> plat = new ArrayList<PlatAuMenu>();

    /**
     * Constructeur prive pour Singleton
     * @param description du menu
     */

    private Menu(String description) {
        this.description = description;
    }

    /**
     * Creer un menu ou renvoie celui deja cree
     * @return l'instance du menu
     */
    public static synchronized Menu getInstance(){
        if (instance == null){
            instance = new Menu(null);
        }
        return instance;
    }

    /**
     * avoir la descripion du menu
     * @return la description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Changer la description du menu
     * @param description a changer
     */
    public void setDescription(String description){
        if (description != null){
            this.description = description;
        }
    }

    /**
     * ajouter un plat au menu
     * @param p nouveau plat
     * @throws MenuException
     */
    void ajoute (PlatAuMenu p) throws MenuException {
        if (p != null){
            plat.add(p);
        }
    }

    /**
     * changer la position dans le menu
     * @param i la position souhaitee
     */

    public void position(int i) {
        courant = i;
    }

    /**
     * afficher le plat courant du menu
     * @return le plat courant
     */

    public PlatAuMenu platCourant() {
        return plat.get(courant);
    }

    /**
     * aller a au plat suivant dans le menu
     * @throws MenuException si on depasse le nombre de plat
     */

    public void positionSuivante() throws MenuException {
        if (courant+1 >= plat.size())
            throw new MenuException("On depasse le nombre maximale de plats.");
        else
            courant++;
    }

    /**
     * aller au plat precedent dans le menu
     * @throws MenuException si on est au debut du menu
     */

    public void positionPrecedente() throws MenuException {
        if (courant-1 < 0)
            throw new MenuException("On depasse le nombre minimale de plats");
        else
            courant--;
    }

    /**
     * affiche les informations du menu
     * @return une String du menu
     */
    @Override
    public String toString() {
        return "menufact.Menu{" +
                "description='" + description + '\'' +
                ", courant=" + courant +
                ", plat=" + "\n" + plat +
                '}';
    }
}
