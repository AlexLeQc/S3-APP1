package menufact;

import menufact.facture.exceptions.FactureException;

/**
 * Classe client du restaurant
 */
public class Client {
    private int idClient;
    private String nom;
    private String numeroCarteCredit;

    /**
     * Constructeur de client
     * @param idClient
     * @param nom du client
     * @param numeroCarteCredit
     */

    public Client(int idClient, String nom, String numeroCarteCredit) {
        this.idClient = idClient;
        this.nom = nom;
        this.numeroCarteCredit = numeroCarteCredit;
    }

    /**
     * avoir le id du client
     * @return le id du client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * methode pour set le idclient
     * @param idClient nouveau id
     * @throws FactureException si le id est negatif
     */
    public void setIdClient(int idClient) throws FactureException {

        if (idClient < 0)
        {
            throw new FactureException("un id ne peut pas etre negatif");
        }
        this.idClient = idClient;
    }

    /**
     * Avoir le nom du client
     * @return le nom
     */

    public String getNom() {
        return nom;
    }

    /**
     * Changer le nom du client
     * @param nom nom a changer
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Avoir le numero de carte de credit du client
     * @return le numero de carte de credit
     */

    public String getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    /**
     * set le numero de carte
     * @param numeroCarteCredit nouveau numero
     * @throws FactureException si le numero est null
     */

    public void setNumeroCarteCredit(String numeroCarteCredit) throws FactureException {
        if (numeroCarteCredit == null)
        {
            throw new FactureException("Un numero de carte ne peut pas etre nul");
        }

        this.numeroCarteCredit = numeroCarteCredit;
    }

    /**
     * Avoir les informations du client
     * @return une string des informations du client
     */

    @Override
    public String toString() {
        return "menufact.Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", numeroCarteCredit='" + numeroCarteCredit + '\'' +
                '}';
    }
}
/*
@startuml
class menufact.Client{}
* */