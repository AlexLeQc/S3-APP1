package menufact.facture;

import menufact.Client;
import menufact.facture.state.FactureEtat;
import menufact.plats.PlatChoisi;

import java.util.ArrayList;
import java.util.Date;

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

    public void setDescription(String description){
        this.description = description;
    }

    public void setEtat(FactureEtat etat){
        this.etat = etat;
    }

    public void setPlatchoisi(ArrayList<PlatChoisi> platchoisi){
        this.platchoisi = platchoisi;
    }

    public void setCourant(int courant){
        this.courant = courant;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public void setTPSplat(double tpsplat){
        TPSplat = tpsplat;
    }

    public void setTVQplat(double tvqplat){
        TVQplat = tvqplat;
    }

    public void setTotal(double total){
        this.total = total;
    }
}
