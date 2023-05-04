package menufact.plats.PlatEtat;

public class EtatImpossible implements EtatPlat{
    /**
     * Fonction pour verifier si on peut changer d'etat
     * @param etatPlat etat a changer
     * @return true si on peut changer letat, faux sinon
     */
    @Override
    public boolean changerEtat(EtatPlat etatPlat){
        return false;
    }

    /**
     * Fonction pour ecrire l'etat du plat dans le fenetre de dialogue
     * @return retourne l'etat commande
     */
    @Override
    public String toString() {
        return "Impossible de servir";
    }
}
