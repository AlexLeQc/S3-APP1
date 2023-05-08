package menufact.facture;

import menufact.Chef;
import menufact.Client;
import menufact.facture.exceptions.FactureException;
import menufact.facture.state.FactureEtat;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

public class FactureController {
    private Facture model;
    private FactureView view;

    public void associerClient(Client client){
        model.associerClient(client);
    }

    public void associerChef(Chef chef){
        model.associerChef(chef);
    }

    public FactureController(Facture model, FactureView view){
        this.model = model;
        this.view = view;
    }

    public double getSousTotal(){
        return model.sousTotal();
    }

    public double getTotal(){
        return model.total();
    }

    public double getTps(){
        return model.tps();
    }

    public double getTvq(){
        return model.tvq();
    }

    public void payer() throws FactureException {
        model.payer();
    }

    public void fermer() throws FactureException{
        model.fermer();
    }

    public void ouvrir() throws FactureException{
        model.ouvrir();
    }

    public FactureEtat getEtat() {
        return model.getEtat();
    }

    public void ajoutPlat(PlatChoisi p) throws FactureException, PlatException {
        model.ajoutePlat(p);
    }

    public void Observer(Chef chef2){
        model.Observer(chef2);
    }

    public String updateViewGenererFacture(){
        view.setDescription(model.getDescription());
        view.setEtat(model.getEtat());
        view.setClient(model.getClient());
        view.setCourant(model.getCourant());
        view.setPlatchoisi(model.getPlatChoisi());
        view.setTPSplat(model.tps());
        view.setTVQplat(model.tvq());
        view.setTotal(model.total());
        return view.printFactureGenerer();
    }

    public String updateViewtoString(){
        view.setDescription(model.getDescription());
        view.setEtat(model.getEtat());
        view.setClient(model.getClient());
        view.setCourant(model.getCourant());
        view.setPlatchoisi(model.getPlatChoisi());
        return view.printtoString();
    }

}
