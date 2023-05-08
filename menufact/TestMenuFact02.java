package menufact;

import ingredients.etat.etatLiquide;
import ingredients.factory.concretecreatorLegume;
import ingredients.instanceIngredient.*;
import ingredients.etat.EtatIngredient;
import ingredients.etat.etatSolide;
import ingredients.exceptions.IngredientException;
import menufact.exceptions.MenuException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import ingredients.etat.*;
//import ingredients.*;
import inventaire.*;
import menufact.plats.PlatEnfant;
import menufact.plats.PlatEtat.*;
import menufact.plats.PlatSante;
import menufact.plats.exceptions.PlatException;


import menufact.plats.platsBuilder.PlatSanteBuilder;
import menufact.plats.platsBuilder.PlatsBuilder;
import menufact.plats.platsBuilder.PlatsEnfantsBuilder;
import org.junit.Before;
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
        System.out.println("Test setDescrition valeur voulu: Bon Filet mignon");
        platAuMenu.setDescription("Bon Filet mignon");
        System.out.println("Valeur recu: " + platAuMenu.getDescription());
        assertEquals("Bon Filet mignon", platAuMenu.getDescription(), "Erreur dans la fonction getDescription");
        System.out.println("Test reussi!\n");

    }

    @Test
    void getPrix() {
        System.out.println("Test getPrix valeur voulu: 15.99");
        assertEquals(15.99, platAuMenu.getPrix());
        System.out.println("Valeur recu: " + platAuMenu.getPrix());
        System.out.println("Test reussi!\n");

    }

    @Test
    void setPrix() {
        System.out.println("Test setPrix valeur voulu: 19.99");
        platAuMenu.setPrix(19.99);
        assertEquals(19.99, platAuMenu.getPrix());
        System.out.println("Valeur recu: " + platAuMenu.getPrix());
        System.out.println("Test reussi!\n");
    }

    @Test
    void getProportion() {
        System.out.println("Test getProportion valeur voulu: 1");
        assertEquals(1, platAuMenu.getProportion());
        System.out.println("Valeur recu: " + platAuMenu.getProportion());
        System.out.println("Test reussi!\n");
    }

    @Test
    void getRecette() throws IngredientException, PlatException {

        Ingredient pain = new Legume("pain", new etatSolide(1));
        Ingredient tomate = new Legume("tomate", new etatSolide(1));
        Ingredient fromage = new Laitier("fromage", new etatSolide(1));
        IngredientPlat bruschetta = new IngredientPlat(new Ingredient[]{pain, fromage, tomate});
        platAuMenu.setRecette(bruschetta);
        System.out.println("Test getRecette valeur voulu: " + bruschetta);
        assertEquals(bruschetta, platAuMenu.getRecette(), "Erreur");
        System.out.println("Valeur recu: " + platAuMenu.getRecette());
        System.out.println("Test reussi!\n");
    }

    @Test
    void setRecette() {
        System.out.println("Test setPrix valeur voulu: 19.99");
        platAuMenu.setPrix(19.99);
        assertEquals(19.99, platAuMenu.getPrix());
        System.out.println("Valeur recu: " + platAuMenu.getPrix());
        System.out.println("Test reussi!\n");
    }

    @AfterAll
    public static void messageFin(){
        System.out.println("----FIN DES TEST DE PLATAUMENU----\n\n");
    }
}

class MenuTest {

    @BeforeAll
    public static void setUpClass() {
        System.out.println("----DEBUT DES TESTS UNITAIRES POUR Menu----\n");
    }


    Menu menu;
    Menu menu2;

    @Test
    void getInstance() throws PlatException, MenuException {
        System.out.println("Test de getInstance");
        menu = Menu.getInstance();
        PlatAuMenu choucroute = new PlatAuMenu(1, "coucroute", 20);
        menu.ajoute(choucroute);

        menu2 = Menu.getInstance();
        PlatAuMenu ragout = new PlatAuMenu(1, "ragout", 10);
        menu2.ajoute(ragout);

        assertEquals(menu, menu2, "Erreur");
        System.out.println("Test de getInstance reussi!\n");

    }

    @Test
    void setDescription() {
        menu = Menu.getInstance();
        System.out.println("Test de setDescription");
        menu.setDescription("Menu du resto le meilleur en ville");
        assertEquals("Menu du resto le meilleur en ville", menu.getDescription());

    }

    @Test
    void getDescription() {
        menu = Menu.getInstance();
        assertEquals("Menu du resto le meilleur en ville", menu.getDescription());

    }



    @Test
    void ajoute() throws PlatException, MenuException {
        menu = Menu.getInstance();
        PlatAuMenu canardConfie = new PlatAuMenu(45, "Canard de riche", 69);
        menu.ajoute(canardConfie);
        menu.position(2);
        assertEquals(menu.platCourant(), canardConfie);
        System.out.println(menu.toString());


    }

    @Test
    void position() {
        menu = Menu.getInstance();
        
    }

    @Test
    void platCourant() {
    }

    @Test
    void positionSuivante() {
    }

    @Test
    void positionPrecedente() {
    }

    @Test
    void testToString() {
    }


}

class PlatEnfantTest {

    PlatEnfant platEnfant = new PlatEnfant(20, "Filet mignon", 9.99, 0.5);

    PlatEnfantTest() throws PlatException {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("----DEBUT DES TESTS UNITAIRES POUR PlatEnfant----\n");
    }
    @Test
    void getProportion() {
        System.out.println("Test getProportion valeur voulu: 0.5");
        System.out.println("Valeur recu: " + platEnfant.getProportion());
        assertEquals(0.5, platEnfant.getProportion(), "Error dans la fonction getProportion");
        System.out.println("Test reussi!\n");
    }

    @Test
    void setProportion() {
        System.out.println("Test setProportion valeur voulu: 0.75");
        platEnfant.setProportion(0.75);
        System.out.println("Valeur recu: " + platEnfant.getProportion());
        assertEquals(0.75, platEnfant.getProportion(), "Errror dans la fonction setProportion");
        System.out.println("Test reussi!\n");
    }

    @Test
    void testToString() {
        System.out.println("Test toString valeur voulu: PlatEnfant{proportion=0.5} menufact.plats.PlatAuMenu{code=20, description='Filet mignon', prix=9.99}");
        System.out.println("Valeur recu: " + platEnfant.toString());
        assertEquals("PlatEnfant{proportion=0.5} menufact.plats.PlatAuMenu{code=20, description='Filet mignon', prix=9.99}", platEnfant.toString(), "Erreur dans la fonction toString");
        System.out.println("Test reussi!\n");
    }
}

class PlatSanteTest {
    @BeforeAll
    public static void setUpClass() {
        System.out.println("----DEBUT DES TESTS UNITAIRES POUR PlatSante----\n");
    }
    PlatSante platSante = new PlatSante(23, "Filet mignon", 43.99, 83.44, 23.11, 1.22);

    PlatSanteTest() throws PlatException {
    }
    @Test
    void testToString() {
        System.out.println("Test toString valeur voulu: menufact.plats.PlatSante{kcal=83.44, chol=23.11, gras=1.22} menufact.plats.PlatAuMenu{code=23, description='Filet mignon', prix=43.99} ");
        System.out.println("Valeur recu: " + platSante.toString());
        assertEquals("menufact.plats.PlatSante{kcal=83.44, chol=23.11, gras=1.22} menufact.plats.PlatAuMenu{code=23, description='Filet mignon', prix=43.99}", platSante.toString(), "Erreur fonction toString");
        System.out.println("Test reussi!\n");
    }

    @Test
    void getKcal() {
        System.out.println("Test getKcal valeur voulu: 83.44");
        System.out.println("Valeur recu: " + platSante.getKcal());
        assertEquals(83.44, platSante.getKcal(), "Erreur fonction getKcal");
        System.out.println("Test reussi!\n");
    }

    @Test
    void setKcal() {
        System.out.println("Test setKcal valeur voulu: 42.01");
        platSante.setKcal(42.01);
        System.out.println("Valeur recu: " + platSante.getKcal());
        assertEquals(42.01, platSante.getKcal(), "Erreur fonction setKcal");
        System.out.println("Test reussi!\n");
    }

    @Test
    void getChol() {
        System.out.println("Test getChol valeur voulu: 23.11");
        System.out.println("Valeur recu: " + platSante.getChol());
        assertEquals(23.11, platSante.getChol(), "Erreur fonction getChol");
        System.out.println("Test reussi!\n");
    }

    @Test
    void setChol() {
        System.out.println("Test setChol valeur voulu: 3.03");
        platSante.setChol(3.03);
        System.out.println("Valeur recu: " + platSante.getChol());
        assertEquals(3.03, platSante.getChol(), "Erreur fonction setKcal");
        System.out.println("Test reussi!\n");
    }

    @Test
    void getGras() {
        System.out.println("Test getGras valeur voulu: 1.22");
        System.out.println("Valeur recu: " + platSante.getGras());
        assertEquals(1.22, platSante.getGras(), "Erreur fonction getChol");
        System.out.println("Test reussi!\n");
    }

    @Test
    void setGras() {
        System.out.println("Test setGras valeur voulu: 0.75");
        platSante.setGras(0.75);
        System.out.println("Valeur recu: " + platSante.getGras());
        assertEquals(0.75, platSante.getGras(), "Erreur fonction setKcal");
        System.out.println("Test reussi!\n");
    }
}
class PlatSanteBuilderTest {
    private PlatSanteBuilder builder = new PlatSanteBuilder();

    @Test
    public void testBuildKcal() throws PlatException {
        double expectedKcal = 500.0;
        PlatSante plat = builder.buildKcal(expectedKcal).getPlatSante();
        double actualKcal = plat.getKcal();
        assertEquals(expectedKcal, actualKcal);
    }

    @Test
    public void testBuildChol() throws PlatException {
        double expectedChol = 10.0;
        PlatSante plat = builder.buildChol(expectedChol).getPlatSante();
        double actualChol = plat.getChol();
        assertEquals(expectedChol, actualChol);
    }

    @Test
    public void testBuildGras() throws PlatException {
        double expectedGras = 20.0;
        PlatSante plat = builder.buildGras(expectedGras).getPlatSante();
        double actualGras = plat.getGras();
        assertEquals(expectedGras, actualGras);
    }

    @Test
    public void testClear() throws PlatException {
        PlatSante plat = builder.buildKcal(500.0)
                .buildChol(10.0)
                .buildGras(20.0)
                .clear()
                .getPlatSante();
        assertEquals(0.0, plat.getKcal());
        assertEquals(0.0, plat.getChol());
        assertEquals(0.0, plat.getGras());
    }
}
class PlatsBuilderTest {
    private PlatsBuilder platsBuilder = new PlatsBuilder();

    @Test
    public void testDescriptionBuild() {
        String expectedDescription = "Plat de test";
        PlatAuMenu plat = platsBuilder.descriptionBuild(expectedDescription).getPlat();
        assertEquals(expectedDescription, plat.getDescription());
    }

    @Test
    public void testPrixBuild() throws PlatException {
        double expectedPrix = 9.99;
        PlatAuMenu plat = platsBuilder.prixBuild(expectedPrix).getPlat();
        assertEquals(expectedPrix, plat.getPrix());
    }

    @Test
    public void testIngreBuildWithIngredientPlat() throws PlatException, IngredientException {
        Ingredient[] ingredients = {new Laitier("Ingredient 1", new etatLiquide(20)), new Fruit("Ingredient 2", new etatSolide(400))};
        IngredientPlat expectedRecette = new IngredientPlat(ingredients);

        PlatAuMenu plat = platsBuilder.IngreBuild(expectedRecette).getPlat();
        assertEquals(expectedRecette, plat.getRecette());
    }

    @Test
    public void testIngreBuildWithIngredientArray() throws PlatException, IngredientException {
        Ingredient[] ingredients = {new Legume("Ingredient 1", new etatSolide(100)), new Viande("Ingredient 2", new etatSolide(200))};

        PlatAuMenu plat = platsBuilder.IngreBuild(ingredients).getPlat();
        IngredientPlat expectedRecette = new IngredientPlat(ingredients);
        assertEquals(expectedRecette, plat.getRecette());
    }
}
class PlatsEnfantsBuilderTest {
    private PlatsEnfantsBuilder platsEnfantsBuilder = new PlatsEnfantsBuilder();

    @Test
    public void testCreeProportion() throws PlatException {
        double expectedProportion = 0.5;
        PlatAuMenu plat = platsEnfantsBuilder.creeProportion(expectedProportion).getPlat();
        PlatEnfant platEnfant = (PlatEnfant) plat;
        assertEquals(expectedProportion, platEnfant.getProportion());
    }

    @Test
    public void testClear() {
        PlatAuMenu plat = platsEnfantsBuilder.clear().getPlat();
        PlatEnfant platEnfant = (PlatEnfant) plat;
        assertEquals(0.0, platEnfant.getProportion());
    }
}
class EtatPlatTest {
    @Test
    public void testEtatCommande() {
        EtatPlat etatCommande = new EtatCommande();
        assertEquals(true, etatCommande.changerEtat(new EtatEnPreparation()));
        assertEquals(true, etatCommande.changerEtat(new EtatImpossible()));
        assertEquals(false, etatCommande.changerEtat(new EtatServi()));
        assertEquals(false, etatCommande.changerEtat(new EtatTerminer()));
    }

    @Test
    public void testEtatEnPreparation() {
        EtatPlat etatEnPreparation = new EtatEnPreparation();
        assertEquals(true, etatEnPreparation.changerEtat(new EtatServi()));
        assertEquals(true, etatEnPreparation.changerEtat(new EtatTerminer()));
        assertEquals(true, etatEnPreparation.changerEtat(new EtatImpossible()));
        assertEquals(false, etatEnPreparation.changerEtat(new EtatCommande()));
    }

    @Test
    public void testEtatImpossible() {
        EtatPlat etatImpossible = new EtatImpossible();
        assertEquals(false, etatImpossible.changerEtat(new EtatCommande()));
        assertEquals(false, etatImpossible.changerEtat(new EtatEnPreparation()));
        assertEquals(false, etatImpossible.changerEtat(new EtatServi()));
        assertEquals(false, etatImpossible.changerEtat(new EtatTerminer()));
    }

    @Test
    public void testEtatServi() {
        EtatPlat etatServi = new EtatServi();
        assertEquals(true, etatServi.changerEtat(new EtatImpossible()));
        assertEquals(false, etatServi.changerEtat(new EtatCommande()));
        assertEquals(false, etatServi.changerEtat(new EtatEnPreparation()));
        assertEquals(false, etatServi.changerEtat(new EtatTerminer()));
    }

    @Test
    public void testEtatTerminer() {
        EtatPlat etatTerminer = new EtatTerminer();
        assertEquals(true, etatTerminer.changerEtat(new EtatServi()));
        assertEquals(true, etatTerminer.changerEtat(new EtatImpossible()));
        assertEquals(false, etatTerminer.changerEtat(new EtatCommande()));
        assertEquals(false, etatTerminer.changerEtat(new EtatEnPreparation()));
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
