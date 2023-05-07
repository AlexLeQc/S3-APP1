package menufact;

import ingredients.etat.etatLiquide;
import ingredients.instanceIngredient.*;
import ingredients.etat.EtatIngredient;
import ingredients.etat.etatSolide;
import ingredients.exceptions.IngredientException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import ingredients.etat.*;
//import ingredients.*;
import inventaire.*;
import menufact.plats.PlatEtat.EtatServi;
import menufact.plats.exceptions.PlatException;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




    class TestIngredient {

        @BeforeAll
        public static void setupClass() {

            System.out.println("----DEBUT DES TESTS POUR INGREDIENTS----");

        }


        @Test
        void getNom() throws IngredientException {
            EtatIngredient solide = new etatSolide(10);
            Ingredient i1 = new Viande("boeuf", solide);
            assertEquals("boeuf", i1.getNom(), "Le test Ingredient Nom a échoué");
            System.out.println("Le test Ingredient Nom est Reussi");
        }

        @Test
        void getEtat() throws IngredientException {
            EtatIngredient solide = new etatSolide(10);
            Ingredient i1 = new Viande("boeuf", solide);
            assertEquals(solide, i1.getEtat(), "Le test Ingredient Etat a échoué");
            System.out.println("Le test Ingredient Etat est Reussi");
        }

        @Test
        void getQuantite() throws IngredientException {
            EtatIngredient solide = new etatSolide(10);
            Ingredient i1 = new Viande("boeuf", solide);
            assertEquals(10, i1.getQuantite(), "Le test Ingredient Quantite a échoué");
            System.out.println("Le test Ingredient Quantite est reussi");
        }
    }

class InventaireTest {

    Inventaire Frigo;

    Ingredient moutarde = new Epice("moutarde", new etatLiquide(10));

    @BeforeAll
    public static void setClass(){
        System.out.println("----TESTS INVENTAIRE----\n");
    }



    InventaireTest() throws IngredientException {
    }

    @Test
    void getInstance() throws IngredientException {
        System.out.println("Test de getInstance");
        Frigo = Inventaire.getInstance();
        Frigo.ajouter(TypeIngredient.EPICE, new etatSolide(200), "sel");
        Frigo.ajouter(TypeIngredient.LAITIER, new etatLiquide(1000), "lait");
        Inventaire congelo;
        congelo = Inventaire.getInstance();
        congelo.ajouter(TypeIngredient.LEGUME, new etatSolide(2), "salade");
        assertEquals(Frigo.toString(), congelo.toString(), "Erreur");
        System.out.println("Test de getInstance reussi!\n");
    }


    @Test
    void ajouter() throws IngredientException {
        Frigo = Inventaire.getInstance();
        Frigo.ajouter(TypeIngredient.FRUIT, new etatSolide(10), "salade");

    }

    @Test
    void getIngredient() throws IngredientException {
        Frigo = Inventaire.getInstance();
        Frigo.ajoutIngredient(moutarde);
        assertEquals(moutarde, Frigo.getIngredient(moutarde), "Erreur");

    }

    @Test
    void getSize() {
        System.out.println("test getSize valeur attendu: 5");
        Frigo = Inventaire.getInstance();
        System.out.println("valeur recu: " + Frigo.getSize());
        assertEquals(5, Frigo.getSize(),"erreur");
        System.out.println("Test reussi! \n");
    }

    @Test
    void getIngredientQuantite() throws IngredientException {
        Frigo = Inventaire.getInstance();
        Frigo.ajoutIngredient(moutarde);
        System.out.println("test getIngredientQuantite valeur attendu: 10");
        assertEquals(10, Frigo.getIngredientQuantite(moutarde));
        System.out.println("Valeur recu: " + Frigo.getIngredientQuantite(moutarde));
        System.out.println("Test reussi! \n");

    }

    @Test
    void consommerRecette() throws IngredientException {
        System.out.println("Test consommerIngredient valeur voulu: olive: 9, radis 7");
        Frigo = Inventaire.getInstance();
        Frigo.vider();
        Ingredient olive = new Legume("olive",new etatSolide(1));
        Ingredient radis = new Legume("radis", new etatSolide(1));
        IngredientPlat recetteSalade = new IngredientPlat(new Ingredient[]{olive, radis});
        Ingredient olive1 = new Legume("olive",new etatSolide(10));
        Ingredient radis1 = new Legume("radis", new etatSolide(8));
        Frigo.ajoutIngredient(new Ingredient[]{olive1, radis1});
        Frigo.consommerRecette(recetteSalade, 1, 1 );
        assertEquals(9, Frigo.getIngredientQuantite(olive1));
        assertEquals(7, Frigo.getIngredientQuantite(radis1));
        System.out.println("Valeur recu: Olive: " + Frigo.getIngredientQuantite(olive1) + ", Radis: " + Frigo.getIngredientQuantite(radis1));
        System.out.println("Test reussi!\n");

        System.out.println("Test sil ny a pas assez d'ingredient");
        assertThrows(IngredientException.class, ()->{
            Frigo.consommerRecette(recetteSalade,10,1);
        });
        System.out.println("Test reussi!\n");


    }

    @Test
    void vider() {
        System.out.println("test vider valeur attendu: 0");
        Frigo = Inventaire.getInstance();
        Frigo.vider();
        assertEquals(0, Frigo.getSize());
        System.out.println("Valeur recu: " + Frigo.getSize());
        System.out.println("Test reussi!\n");
    }

}

class ClientTest {

    public Client client1 = new Client(1, "Gab Pasbon", "1234 5678 9101 1121");


    @BeforeAll
    public static void setUpClass() {
        System.out.println("----DEBUT DES TESTS UNITAIRES POUR CLIENT----\n");
    }


    @Test
    void getIdClient() {
        System.out.println(client1.toString());
        System.out.println("Test getIdClient valeur de retour attendu: 1");
        System.out.println("Valeur recu: " + client1.getIdClient());
        assertEquals(1, client1.getIdClient(), "Le test de getIdClient a echoue");
        System.out.println("Le test de getIdClient est reussi!\n");


    }

    @Test
    void setIdClient() throws FactureException {
        System.out.println("Test setIdClient valeur de retour attendu: 3");
        client1.setIdClient(3);
        System.out.println("Valeur recu: " + client1.getIdClient());
        assertEquals(3, client1.getIdClient(), "Le test de setIdClient a echoue");
        System.out.println("Le test de setIdClient est reussi!");

        System.out.println("Test setIdClient exception");
        assertThrows(FactureException.class, () -> {
            client1.setIdClient(-3);
        });
        System.out.println("Test reussi!\n");
    }

    @Test
    void getNom() {
        System.out.println("Test setNom valeur retour attendu: Gab Pasbon");
        System.out.println("Valeur recu: " + client1.getNom());
        assertEquals("Gab Pasbon", client1.getNom(), "Erreur");
        System.out.println("Le test est reussi!\n");

    }

    @Test
    void setNom() {
        System.out.println("Test setNom valeur retour attendu: Alex Meilleur");
        client1.setNom("Alex Meilleur");
        System.out.println("Valeur recu: " + client1.getNom());
        assertEquals("Alex Meilleur", client1.getNom(), "Erreur");
        System.out.println("Le test est reussi!\n");

    }

    @Test
    void getNumeroCarteCredit() {
        System.out.println("Test getNumeroCarteCredit valeur retour attendu: 1234 5678 9101 1121");
        System.out.println("Valeur recu: " + client1.getNumeroCarteCredit());
        assertEquals("1234 5678 9101 1121", client1.getNumeroCarteCredit(), "Erreur");
        System.out.println("Le test est reussi!\n");


    }

    @Test
    void setNumeroCarteCredit() throws FactureException {
        System.out.println("Test setNureroCarteCredit valeur attendu: 3576 8991 9348 8292");
        client1.setNumeroCarteCredit("3576 8991 9348 8292");
        System.out.println("Valeur recu: " + client1.getNumeroCarteCredit());
        assertEquals("3576 8991 9348 8292", client1.getNumeroCarteCredit(), "Le test a echoue");
        System.out.println("Le test est reussi!\n");

        System.out.println("Test setNumeroCarteCredit exception");
        assertThrows(FactureException.class, () -> {
            client1.setNumeroCarteCredit(null);
        });
        System.out.println("Le test est reussi!\n");

    }

    @Test
    void testToString() {
        System.out.println("Valeur du client: " + client1.toString());
        assertEquals("menufact.Client{idClient=1, nom='Gab Pasbon', numeroCarteCredit='1234 5678 9101 1121'}", client1.toString(), "Erreur dans le test toString");
        System.out.println("Le test toString est reussi!\n");
    }


    @AfterAll
    public static void AfficheLaFin() {
        System.out.println("----FIN DES TESTS UNITAIRES DE LA CLASSE CLIENT----\n\n");
    }
}

class ChefTest {

    Chef gusteau;
    Inventaire Frigo;

    Ingredient pain = new Fruit("pain", new etatSolide(4));
    Ingredient boulette = new Viande("boulette", new etatSolide(3));
    Ingredient ketchup = new Epice("ketchup", new etatLiquide(250));

    IngredientPlat burgerRecette = new IngredientPlat(new Ingredient[]{pain, boulette, ketchup});

    PlatAuMenu burgerMenu = new PlatAuMenu(69, "Burger de riche", 4.20);
    PlatChoisi burger = new PlatChoisi(burgerMenu,1);

    PlatChoisi burger2 = new PlatChoisi(burgerMenu, 2);


    ChefTest() throws IngredientException, PlatException{}



    @BeforeAll
    public static void setUpClass()
    {
        System.out.println("----DEBUT DES TESTS UNITAIRES POUR CHEF----\n");
    }

    @Test
    void getInstance() {
        gusteau = Chef.getInstance("gusteau");
        System.out.println("Test getInstance valeur voulue: gusteau");
        assertEquals("gusteau", gusteau.getNom());
        System.out.println("Valeur recu: " + gusteau.getNom());
        System.out.println("Test reussi!\n");

        Chef remi = Chef.getInstance("remi");
        System.out.println("Test ajout chef valeur voulue: gusteau");
        assertEquals("gusteau", gusteau.getNom());
        System.out.println("Valeur recu: " + gusteau.getNom());
        System.out.println("Test reussi!\n");
    }

    @Test
    void getNom() {
        System.out.println("Test getNom valeur voulue: gusteau");
        gusteau = Chef.getInstance("gusteau");
        assertEquals("gusteau", gusteau.getNom());
        System.out.println("Valeur recu: " + gusteau.getNom());
        System.out.println("Test reussi!\n");
    }

    @Test
    void setNom() {
        System.out.println("Test setNom valeur voulue: gustette");
        gusteau = Chef.getInstance("gusteau");
        gusteau.setNom("gustette");
        assertEquals("gustette", gusteau.getNom());
        System.out.println("Valeur recu: " + gusteau.getNom());
        System.out.println("Test reussi!\n");

    }


    @Test
    void cuisiner() throws PlatException, IngredientException {


        gusteau = Chef.getInstance("gusteau");
        Frigo = Inventaire.getInstance();
        burgerMenu.setRecette(burgerRecette);
        Frigo.ajoutIngredient(new Ingredient[]{pain, boulette,ketchup});

        System.out.println("Test manque d'ingredient");
        assertThrows(IngredientException.class, () ->{
            gusteau.cuisiner(burger2);
        });
        System.out.println("Test reussi! Il manque des ingredient dans l'exception est lancee\n");

        System.out.println("Test cuisiner valeur souahite: Servi");
        gusteau.cuisiner(burger);
        assertTrue(burger.getEtat() instanceof EtatServi);
        System.out.println("Valeur recu: " + burger.getEtat());
        System.out.println("Test reussi!\n");


    }

    @Test
    void testToString() {
        System.out.println("Test toString valeur voulu: Chef: {Nom: 'gusteau'}");
        gusteau = Chef.getInstance("gusteau");
        assertEquals("Chef: {Nom: 'gusteau'}",gusteau.toString());
        System.out.println("Valeur recu" + gusteau.toString());
        System.out.println("Test reussi!\n");

    }

    @AfterAll
    public static void messageFin(){
        System.out.println("----FIN DES TESTS POUR CHEF----\n\n");
    }
}

class PlatAuMenuTest {
    PlatAuMenu platAuMenu = new PlatAuMenu(20,"Filet mignon",15.99);

    PlatAuMenuTest() throws PlatException {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("----DEBUT DES TESTS UNITAIRES POUR PlatAuMenu----\n");
    }

    @Test
    void testToString() {
        System.out.println("Test toString valeur voulu: " + platAuMenu.toString());
        System.out.println("Valeur recu: " + platAuMenu.toString());
        assertEquals("menufact.plats.PlatAuMenu{code=20, description='Filet mignon', prix=15.99}", platAuMenu.toString(), "Erreur dans le testtoString");
        System.out.println("Test reussi!\n");
    }

    @Test
    void getCode() {
        System.out.println("Test getCode valeur voulu: 20");
        System.out.println("Valeur recu: " + platAuMenu.getCode());
        assertEquals(20, platAuMenu.getCode(), "Erreur dans la fonction getCode");
        System.out.println("Test reussi!\n");
    }

    @Test
    void setCode() {
        System.out.println("Test setCode valeur voulu: 21");
        platAuMenu.setCode(21);
        System.out.println("Valeur recu: " + platAuMenu.getCode());
        assertEquals(21, platAuMenu.getCode(), "Erreur dans la fonction setCode");
        System.out.println("Test reussi!\n");
    }

    @Test
    void getDescription() {
        System.out.println("Test getDescription valeur voulu: 'Filet mignon'");
        System.out.println("Valeur recu: " + platAuMenu.getDescription());
        assertEquals("Filet mignon", platAuMenu.getDescription(), "Erreur dans la fonction getDescription");
        System.out.println("Test reussi!\n");
    }

    @Test
    void setDescription() {
        System.out.println("Test setDescrition");
    }

    @Test
    void getPrix() {
    }

    @Test
    void setPrix() {
    }

    @Test
    void getProportion() {
    }

    @Test
    void getRecette() {
    }

    @Test
    void setRecette() {
    }
}



//public class TestMenuFact02 {
//
//
////    public static void main(String[] args) {
////        boolean trace = true;
////
////        TestMenuFact02 t = new TestMenuFact02();
////
////        PlatAuMenu p1 = new PlatAuMenu(0,"PlatAuMenu0",10);
////        PlatAuMenu p2 = new PlatAuMenu(1,"PlatAuMenu1",20);
////        PlatAuMenu p3 = new PlatAuMenu(2,"PlatAuMenu2",30);
////        PlatAuMenu p4 = new PlatAuMenu(3,"PlatAuMenu3",40);
////        PlatAuMenu p5 = new PlatAuMenu(4,"PlatAuMenu4",50);
////
////
////        PlatSante ps1 = new PlatSante(10,"PlatSante0",10,11,11,11);
////        PlatSante ps2 = new PlatSante(11,"PlatSante1",20,11,11,11);
////        PlatSante ps3 = new PlatSante(12,"PlatSante2",30,11,11,11);
////        PlatSante ps4 = new PlatSante(13,"PlatSante3",40,11,11,11);
////        PlatSante ps5 = new PlatSante(14,"PlatSante4",50,11,11,11);
////
////
////        Menu m1 = new Menu("menufact.Menu 1");
////        Menu m2 = new Menu("menufact.Menu 2");
////
////        Facture f1 = new Facture("Ma facture");
////
////        Client c1 = new Client(1,"Mr Client","1234567890");
////
////
////        t.test1_AffichePlatsAuMenu(trace, p1,p2,p3,p4,p5);
////        t. test2_AffichePlatsSante(trace, ps1,ps2,ps3,ps4,ps5);
////
////        t.test4_AjoutPlatsAuMenu(trace, m1, p1, p2, ps1, ps2, m2, p3, p4, ps3, ps4);
////
////
////        try {
////            t.test5_DeplacementMenuAvancer(m1);
////        } catch (MenuException e) {
////            System.out.println(e.getMessage());
////        }
////
////        try {
////            t.test6_DeplacementMenuReculer(m1);
////        } catch (MenuException e) {
////            System.out.println(e.getMessage());
////        }
////
////        try {
////            t.test7_CreerFacture(f1, m1);
////        } catch (FactureException e) {
////            System.out.println(e.getMessage());
////        }
////
////
////        t.test8_AjouterClientFacture(f1, c1);
////
////        try {
////            t.test8_AjouterPlatsFacture(f1, m1,1);
////        } catch (FactureException fe)
////        {
////            System.out.println(fe.getMessage());
////        }
////        catch (MenuException me)
////        {
////            System.out.println(me);
////        }
////
////        t.test9_PayerFacture(f1);
////
////        try {
////            t.test8_AjouterPlatsFacture(f1, m1,1);
////        } catch (FactureException fe)
////        {
////            System.out.println(fe.getMessage());
////        }
////        catch (MenuException me)
////        {
////            System.out.println(me);
////        }
////
////        try {
////            f1.ouvrir();
////        } catch (FactureException fe)
////        {
////            System.out.println(fe.getMessage());
////        }
////
////
////
////
////
////
////        System.out.println("FIN DE TOUS LES TESTS...");
////
////        System.out.println(f1.genererFacture());
////    }
////
////    private void test1_AffichePlatsAuMenu(boolean trace, PlatAuMenu p1, PlatAuMenu p2,
////                                                 PlatAuMenu p3, PlatAuMenu p4, PlatAuMenu p5)
////    {
////        System.out.println("=== test1_AffichePlatsAuMenu");
////        if(trace)
////        {
////            System.out.println(p1);
////            System.out.println(p2);
////            System.out.println(p3);
////            System.out.println(p4);
////            System.out.println(p5);
////        }
////    }
////
////
////   private void test2_AffichePlatsSante(boolean trace, PlatSante ps1, PlatSante ps2,
////                                               PlatSante ps3, PlatSante ps4, PlatSante ps5)
////    {
////        System.out.println("=== test2_AffichePlatsSante");
////
////        if(trace)
////        {
////            System.out.println(ps1);
////            System.out.println(ps2);
////            System.out.println(ps3);
////            System.out.println(ps4);
////            System.out.println(ps5);
////        }
////    }
////
////
////    private static void test3_AjoutMenu(boolean trace, Menu m1, Menu m2)
////    {
////
////        System.out.println("=== test3_AjoutMenu");
////
////        if(trace) {
////            System.out.println(m1);
////            System.out.println(m2);
////        }
////    }
////
////
////    private void test4_AjoutPlatsAuMenu(boolean trace, Menu m1,
////                                        PlatAuMenu p1, PlatAuMenu p2,
////                                        PlatSante ps1, PlatSante ps2,
////                                        Menu m2,
////                                        PlatAuMenu p3, PlatAuMenu p4,
////                                        PlatSante ps3, PlatSante ps4)
////    {
////        System.out.println("=== test4_AjoutPlatsAuMenu");
////        System.out.println("=== Ajout de plats au menu 1");
////        m1.ajoute(p1);
////        m1.ajoute(p2);
////        m1.ajoute(ps1);
////        m1.ajoute(ps2);
////
////
////        System.out.println("=== Ajout de plats au menu 2");
////        m2.ajoute(p3);
////        m2.ajoute(p4);
////        m2.ajoute(ps3);
////        m2.ajoute(ps4);
////
////        if(trace) {
////            System.out.println(m1);
////            System.out.println(m2);
////        }
////    }
////
////
////    private void test5_DeplacementMenuAvancer(Menu m1) throws MenuException
////    {
////        System.out.println("=== test5_DeplacementMenuAvancer");
////
////        System.out.println("===Selectionner un plat du menu 0");
////        m1.position(0);
////
////        System.out.println("=== Afficher le plat courant");
////        System.out.println(m1.platCourant());
////        try {
////
////            System.out.println("=== Avancer le plat courant");
////            System.out.println("1.");
////            m1.positionSuivante();
////            System.out.println("2.");
////            m1.positionSuivante();
////            System.out.println("3.");
////            m1.positionSuivante();
////            System.out.println("4.");
////            m1.positionSuivante();
////            System.out.println("5.");
////            m1.positionSuivante();
////        }
////        catch (MenuException me)
////        {
////                throw me;
////        }
////    }
////
////
////    private void test6_DeplacementMenuReculer(Menu m1) throws MenuException
////    {
////        System.out.println("===test6_DeplacementMenuReculer");
////
////        System.out.println("===Selectionner un plat du menu 3");
////        m1.position(3);
////
////        System.out.println("=== Afficher le plat courant");
////        System.out.println(m1.platCourant());
////        try {
////
////            System.out.println("=== Reculer le plat courant");
////            System.out.println("2.");
////            m1.positionPrecedente();
////            System.out.println("1.");
////            m1.positionPrecedente();
////            System.out.println("0.");
////            m1.positionPrecedente();
////            System.out.println("-1.");
////            m1.positionPrecedente();
////            System.out.println("-2.");
////            m1.positionPrecedente();
////        }
////        catch (MenuException me)
////        {
////            throw me;
////        }
////    }
////
////    private void test7_CreerFacture(Facture f1, Menu m1) throws FactureException
////    {
////        System.out.println("===test7_CreerFacture");
////
////        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(),5);
////        try
////        {
////            f1.ajoutePlat(platChoisi);
////        }
////        catch (FactureException fe)
////        {
////            throw fe;
////        }
////        System.out.println(f1);
////    }
////
////
////    private void test8_AjouterClientFacture(Facture f1,Client c1) {
////        System.out.println("===test8_AjouterClientFacture");
////        f1.associerClient(c1);
////        System.out.println(f1);
////    }
////    private void test8_AjouterPlatsFacture(Facture f1, Menu m1, int pos) throws MenuException,FactureException
////    {
////        System.out.println("===test8_AjouterPlatsFacture");
////
////        try{
////            for (int i=0; i< pos; i++)
////                m1.positionSuivante();
////        }
////        catch (MenuException me)
////        {
////            throw me;
////        }
////
////        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(),5);
////        try
////        {
////            f1.ajoutePlat(platChoisi);
////        }
////        catch (FactureException fe)
////        {
////            throw fe;
////        }
////        System.out.println(f1);
////    }
////
////    private void test9_PayerFacture(Facture f1)
////    {
////        System.out.println("===test9_PayerFacture");
////
////        System.out.println("Avant payer la facture");
////        System.out.println(f1);
////        f1.payer();
////        System.out.println("Apres avoir paye la facture");
////        System.out.println(f1);
////    }
//}
