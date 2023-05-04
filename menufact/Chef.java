package menufact;

public class Chef {
    private static Chef instance;
    private String nom;
    private Chef() {
    }
    public static synchronized Chef getInstance(){
        if (instance == null){
            instance = new Chef();
        }
        return instance;
    }
    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        if (nom != null){
            this.nom = nom;
        }
    }
}
