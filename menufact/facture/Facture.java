package menufact.facture;

import ingredients.exceptions.IngredientException;
import menufact.Chef;
import menufact.Client;
import menufact.facture.exceptions.FactureException;
import menufact.facture.state.FactureEtat;
import menufact.facture.state.FactureEtatFermee;
import menufact.facture.state.FactureEtatOuverte;
import menufact.facture.state.FactureEtatPayee;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Une facture du systeme Menufact
 * @author Domingo Palao Munoz
 * @version 1.0
 */
public class Facture {
    private Date date;
    private String description;
    private FactureEtat etat;
    private ArrayList<PlatChoisi> platchoisi = new ArrayList<PlatChoisi>();
    private int courant;
    private Client client;
    private Chef chef;

    /**********************Constantes ************/
    private final double TPS = 0.05;
    private final double TVQ = 0.095;

    /**
     * Methode pour associer un client a la facture
     * @param client le client de la facture
     */
    public void associerClient (Client client) {
        this.client = client;
    }

    /**
     * Calcul du sous total de la facture
     * @return le sous total
     */

    public double sousTotal() {
        double soustotal=0;
         for (PlatChoisi p : platchoisi)
             soustotal += p.getQuantite() * p.getPlat().getPrix();
        return soustotal;
    }

    /**
     * Methode pour obtenir le total de la facture
     * @return le total de la facture
     */
    public double total(){
        return sousTotal()+tps()+tvq();
    }

    /**
     * Methode pour obtenir la TPS
     * @return la valeur de la TPS
     */
    public double tps(){
        return TPS*sousTotal();
    }

    /**
     * Methode pour obtenir la TVQ
     * @return la valeur de la TVQ
     */
    public  double tvq(){
        return TVQ*sousTotal();
    }

    /**
     * Methode pour payer la facture
     * @throws FactureException
     */
    public void payer() throws FactureException{
        if(etat.changerEtat(new FactureEtatPayee())) {
            etat = new FactureEtatPayee();
        } else {
            throw new FactureException("La facture ne peut pas être payée.");
        }
    }

    /**
     * Methode pour fermer la facture
     * @throws FactureException
     */
    public void fermer() throws FactureException{
        if(etat.changerEtat(new FactureEtatFermee())){
            etat = new FactureEtatFermee();
        } else{
            throw new FactureException("La facture ne peut pas être fermée");
        }
    }
    /**
     * Permet de changer l'état de la facture à OUVERTE
     * @throws FactureException en cas que la facture soit PAYEE
     */
    public void ouvrir() throws FactureException
    {
        if (etat.changerEtat(new FactureEtatOuverte())){
            etat = new FactureEtatOuverte();
        } else {
            throw new FactureException("La facture ne peut pas être reouverte.");
        }
    }

    /**
     * Methode pour obtenir l etat de la facture
     * @return l'état de la facture
     */
    public FactureEtat getEtat() {
        return etat;
    }

    /**
     * Constructor de la facture
     * @param description la description de la Facture
     */
    public Facture(String description) {
        date = new Date();
        etat = new FactureEtatOuverte();
        courant = -1;
        this.description = description;
    }

    /**
     * Methode qui ajoute un plat a la facture
     * @param p un plat choisi
     * @throws FactureException Seulement si la facture est OUVERTE
     */
    public void ajoutePlat(PlatChoisi p) throws FactureException, PlatException {
        if (etat instanceof FactureEtatOuverte) {
            if(p == null){
                throw new PlatException("Il est impossible d ajouter un plan null à la facture");
            }
            if (chef == null){
                throw new FactureException("Il n'y a pas de chef");
            } else {
                try {
                    chef.cuisiner(p);
                    platchoisi.add(p);
                } catch (IngredientException ie) {
                    System.out.println("Le nombre d'ingrédient est insuffisant" + ie.getMessage());
                }
            }
        } else {
            throw new FactureException("L'ajout de plat se fait seulement sur une facture ouverte");
        }
    }

    /**
     * Methode pour faire observer chef
     * @param chef2 Object de la class Chef
     */
    public void Observer(Chef chef2) {
        chef = chef2;
    }

    /**
     * Methode pour obtenir la date
     * @return date date de la journee
     */
    public Date getDate(){
        return date;
    }

    /**
     * Methode pour obtenir la description
     * @return description description du produit
     */
    public String getDescription(){
        return description;
    }

    /**
     * Methode pour obtenir le platchoisi
     * @return platchoisi return le plat qui est choisi
     */
    public ArrayList<PlatChoisi> getPlatChoisi(){
        return platchoisi;
    }

    /**
     * Methode pour obtenir le courant
     * @return courant return le courant de la facture
     */
    public int getCourant(){
        return courant;
    }

    /**
     * Methode pour obtenir le client de la facture
     * @return client return le client ayant la facture
     */
    public Client getClient(){
        return client;
    }

    /**
     * Methode pour obtenir le chef
     * @return chef return le chef du restaurant
     */
    public Chef getChef(){
        return chef;
    }

}
