package menufact.facture;

import menufact.Client;
import menufact.facture.state.FactureEtat;
import menufact.plats.PlatChoisi;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class view du MVC Facture
 */
public class FactureView {
    private Date date;
    private String description;
    private Client client;
    private ArrayList<PlatChoisi> platchoisi;
    private FactureEtat etat;
    private int courant;
    private double TPS = 0.05;
    private double TVQ = 0.095;
    private double TPSplat;
    private double TVQplat;
    private double total;

    /**
     * Methode pour creer la facture
     * @return factureGenere return le string de la facture genere
     */
    public String printFactureGenerer(){
        String lesPlats = new String();
        String factureGenere = new String();

        int i =1;


        factureGenere =   "Facture generee.\n" +
                "Date:" + date + "\n" +
                "Description: " + description + "\n" +
                "Client:" + client.getNom() + "\n" +
                "Les plats commandes:" + "\n" + lesPlats;

        factureGenere += "Seq   Plat         Prix   Quantite\n";
        for (PlatChoisi plat : platchoisi)
        {
            factureGenere +=  i + "     " + plat.getPlat().getDescription() +  "  " + plat.getPlat().getPrix() +  "      " + plat.getQuantite() + "\n";
            i++;
        }

        factureGenere += "          TPS:               " + TPSplat + "\n";
        factureGenere += "          TVQ:               " + TVQplat + "\n";
        factureGenere += "          Le total est de:   " + total + "\n";

        return factureGenere;
    }

    /**
     * Methode pour generer les informations de la facture
     * @return String return le string des informations de la facture
     */
    public String printtoString(){
        return "menufact.facture.Facture{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", etat=" + etat +
                ", platchoisi=" + platchoisi +
                ", courant=" + courant +
                ", client=" + client +
                ", TPS=" + TPS +
                ", TVQ=" + TVQ +
                '}';
    }

    /**
     * Methode pour definir la description de la facture
     * @param description String de la description de la facture
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Methode pour definir l etat de la facture
     * @param etat Object de FactureEtat pour definir l etat de la facture
     */
    public void setEtat(FactureEtat etat){
        this.etat = etat;
    }

    /**
     * Methode pour definir le plat choisi de la facture
     * @param platchoisi ArrayList de platchoisi
     */
    public void setPlatchoisi(ArrayList<PlatChoisi> platchoisi){
        this.platchoisi = platchoisi;
    }

    /**
     * Methode pour defenir le courant de la facture
     * @param courant Courant de la facture
     */
    public void setCourant(int courant){
        this.courant = courant;
    }

    /**
     * Methode pour definir le client de la facture
     * @param client Object de la classe client pour la facture
     */
    public void setClient(Client client){
        this.client = client;
    }

    /**
     * Methode pour definir la TPS du plat
     * @param tpsplat TPS du plat
     */
    public void setTPSplat(double tpsplat){
        TPSplat = tpsplat;
    }

    /**
     * Methode pour definir la TVQ du plat
     * @param tvqplat TVQ du plat
     */
    public void setTVQplat(double tvqplat){
        TVQplat = tvqplat;
    }

    /**
     * Methode pour definir le total de la facture
     * @param total Total de la facture
     */
    public void setTotal(double total){
        this.total = total;
    }

    /**
     * Methode pour defenir la date de l impression de la facture
     * @param date Object de la class Date de l impression de la facture
     */
    public void setDate(Date date){
        this.date = date;
    }
}
