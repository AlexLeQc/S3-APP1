package menufact.plats.PlatEtat;

public interface EtatPlat {
    /**
     * Fonction abstraite pour le changement d etat
     * @param etatPlat
     * @return booleen true si l'etat change est possible, faux sinon
     */
    public boolean changerEtat(EtatPlat etatPlat);
}
