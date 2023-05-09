package menufact.plats.PlatEtat;

public class EtatEnPreparation implements EtatPlat{
    /**
     * Fonction pour verifier si on peut changer d'etat
     * @param etatPlat etat a changer
     * @return true si on peut changer letat, faux sinon
     */
    @Override
    public boolean changerEtat(EtatPlat etatPlat){
        return etatPlat instanceof EtatTerminer;
    }

}
