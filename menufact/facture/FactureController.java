package menufact.facture;

import menufact.Chef;
import menufact.Client;
import menufact.facture.exceptions.FactureException;
import menufact.facture.state.FactureEtat;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

/**
 * Class controller du MVC Facture
 */
public class FactureController {
    private Facture model;
    private FactureView view;

    /**
     * Methode pour associer le client a la facture
     * @param client Object de la classe client
     */
    public void associerClient(Client client){
        model.associerClient(client);
    }

    /**
     * Constructeur de la classe FactureController
     * @param model Object de la class Facture
     * @param view Object de la class FactureView
     */
    public FactureController(Facture model, FactureView view){
        this.model = model;
        this.view = view;
    }

    /**
     * Methode pour obtenir le soustotal de la facture
     * @return soustotal de la facture
     */
    public double getSousTotal(){
        return model.sousTotal();
    }

    /**
     * Methode pour obtenir le total de la facture
     * @return total de la facture
     */
    public double getTotal(){
        return model.total();
    }

    /**
     * Methode pour obtenir TPS de la facture
     * @return TPS de la facture
     */
    public double getTps(){
        return model.tps();
    }

    /**
     * Methode pour obtenir TVQ de la facture
     * @return TVQ de la facture
     */
    public double getTvq(){
        return model.tvq();
    }

    /**
     * Methode pour payer la facture
     * @throws FactureException
     */
    public void payer() throws FactureException {
        model.payer();
    }

    /**
     * Methode pour fermer la facture
     * @throws FactureException
     */
    public void fermer() throws FactureException{
        model.fermer();
    }

    /**
     * Methode pour ouvrir la facture
     * @throws FactureException
     */
    public void ouvrir() throws FactureException{
        model.ouvrir();
    }

    /**
     * Methode pour obtenir l etat de la facture
     * @return l etat de la facture
     */
    public FactureEtat getEtat() {
        return model.getEtat();
    }

    /**
     * Methode pour ajouter un plat a la facture
     * @param p Object de la class PlatChoisi
     * @throws FactureException
     * @throws PlatException
     */
    public void ajoutPlat(PlatChoisi p) throws FactureException, PlatException {
        model.ajoutePlat(p);
    }

    /**
     * Methode pour faire observer chef
     * @param chef2 Object de la class chef
     */
    public void Observer(Chef chef2){
        model.Observer(chef2);
    }

    /**
     * Methode pour mettre a jour la facture
     * @return le string de la facture generer mise a jour
     */
    public String updateViewGenererFacture(){
        view.setDescription(model.getDescription());
        view.setEtat(model.getEtat());
        view.setClient(model.getClient());
        view.setCourant(model.getCourant());
        view.setPlatchoisi(model.getPlatChoisi());
        view.setTPSplat(model.tps());
        view.setTVQplat(model.tvq());
        view.setTotal(model.total());
        view.setDate(model.getDate());
        return view.printFactureGenerer();
    }

    /**
     * Methode pour mettre a jour les informations de la facture
     * @return le String des informations mise a jour
     */
    public String updateViewtoString(){
        view.setDescription(model.getDescription());
        view.setEtat(model.getEtat());
        view.setClient(model.getClient());
        view.setCourant(model.getCourant());
        view.setPlatchoisi(model.getPlatChoisi());
        view.setDate(model.getDate());
        return view.printtoString();
    }

}
