package menufact.plats.PlatEtat;

public class EtatCommande implements EtatPlat{
    /**
     * Fonction pour verifier si on peut changer d'etat
     * @param etatPlat etat a changer
     * @return true si on peut changer letat, faux sinon
     */
    @Override
    public boolean changerEtat(EtatPlat etatPlat){
        return etatPlat instanceof EtatEnPreparation || etatPlat instanceof EtatImpossible;
    }

    /**
     * Fonction pour ecrire l'etat du plat dans le fenetre de dialogue
     * @return retourne l'etat commande
     */
    public String toString(){
        return "Commande";
    }
}
