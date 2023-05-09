package menufact;

import ingredients.etat.etatLiquide;
import ingredients.factory.*;
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
import menufact.facture.*;
import menufact.facture.exceptions.*;
import menufact.facture.state.*;
import ingredients.factory.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import menufact.plats.platsBuilder.PlatSanteBuilder;
import menufact.plats.platsBuilder.PlatsBuilder;
import menufact.plats.platsBuilder.PlatsEnfantsBuilder;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;


class InventaireTest {

    Inventaire Frigo;

    Ingredient moutarde = new Epice("moutarde", new etatLiquide(10));

    InventaireTest() throws IngredientException {
    }

    @Test
    void getInstance() throws IngredientException {
        Frigo = Inventaire.getInstance();
        Frigo.ajouter(TypeIngredient.EPICE, new etatSolide(200), "sel");
        Frigo.ajouter(TypeIngredient.LAITIER, new etatLiquide(1000), "lait");
        Inventaire congelo;
        congelo = Inventaire.getInstance();
        congelo.ajouter(TypeIngredient.LEGUME, new etatSolide(2), "salade");
        assertEquals(Frigo.toString(), congelo.toString(), "Erreur");
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
        Frigo = Inventaire.getInstance();
        assertEquals(5, Frigo.getSize(),"erreur");
    }

    @Test
    void getIngredientQuantite() throws IngredientException {
        Frigo = Inventaire.getInstance();
        Frigo.ajoutIngredient(moutarde);
        assertEquals(10, Frigo.getIngredientQuantite(moutarde));
    }

    @Test
    void consommerRecette() throws IngredientException {
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

        assertThrows(IngredientException.class, ()->{
            Frigo.consommerRecette(recetteSalade,10,1);
        });
    }

    @Test
    void vider() {
        Frigo = Inventaire.getInstance();
        Frigo.vider();
        assertEquals(0, Frigo.getSize());
    }

}

class ClientTest {

    public Client client1 = new Client(1, "Gab Pasbon", "1234 5678 9101 1121");

    @Test
    void getIdClient() {
        assertEquals(1, client1.getIdClient(), "Le test de getIdClient a echoue");
    }

    @Test
    void setIdClient() throws FactureException {
        client1.setIdClient(3);
        assertEquals(3, client1.getIdClient(), "Le test de setIdClient a echoue");
        assertThrows(FactureException.class, () -> {
            client1.setIdClient(-3);
        });
    }

    @Test
    void getNom() {
        assertEquals("Gab Pasbon", client1.getNom(), "Erreur");
    }

    @Test
    void setNom() {
        client1.setNom("Alex Meilleur");
        assertEquals("Alex Meilleur", client1.getNom(), "Erreur");
    }

    @Test
    void getNumeroCarteCredit() {
        assertEquals("1234 5678 9101 1121", client1.getNumeroCarteCredit(), "Erreur");
    }

    @Test
    void setNumeroCarteCredit() throws FactureException {
        client1.setNumeroCarteCredit("3576 8991 9348 8292");
        assertEquals("3576 8991 9348 8292", client1.getNumeroCarteCredit(), "Le test a echoue");
        assertThrows(FactureException.class, () -> {
            client1.setNumeroCarteCredit(null);
        });
    }

    @Test
    void testToString() {
        assertEquals("menufact.Client{idClient=1, nom='Gab Pasbon', numeroCarteCredit='1234 5678 9101 1121'}", client1.toString(), "Erreur dans le test toString");
    }
}

class ChefTest {

    Chef gusteau = Chef.getInstance("gusteau");
    Inventaire Frigo;

    Ingredient pain = new Fruit("pain", new etatSolide(4));
    Ingredient boulette = new Viande("boulette", new etatSolide(3));
    Ingredient ketchup = new Epice("ketchup", new etatLiquide(250));

    IngredientPlat burgerRecette = new IngredientPlat(new Ingredient[]{pain, boulette, ketchup});

    PlatAuMenu burgerMenu = new PlatAuMenu(69, "Burger de riche", 4.20);
    PlatChoisi burger = new PlatChoisi(burgerMenu,1);

    PlatChoisi burger2 = new PlatChoisi(burgerMenu, 2);


    ChefTest() throws IngredientException, PlatException{}

    @Test
    void getInstance() {
        gusteau = Chef.getInstance("gusteau");
        assertEquals("gusteau", gusteau.getNom());
        Chef remi = Chef.getInstance("remi");
        assertEquals("gusteau", gusteau.getNom());
    }

    @Test
    void getNom() {
        gusteau = Chef.getInstance("gusteau");
        assertEquals("gusteau", gusteau.getNom());
    }

    @Test
    void setNom() {
        gusteau = Chef.getInstance("gusteau");
        gusteau.setNom("gustette");
        assertEquals("gustette", gusteau.getNom());
    }


    @Test
    void cuisiner() throws PlatException, IngredientException {
        gusteau = Chef.getInstance("gusteau");
        Frigo = Inventaire.getInstance();
        burgerMenu.setRecette(burgerRecette);
        Frigo.ajoutIngredient(new Ingredient[]{pain, boulette,ketchup});
        assertThrows(PlatException.class, () ->{
            gusteau.cuisiner(burger2);
        });
        gusteau.cuisiner(burger);
        assertTrue(burger.getEtat() instanceof EtatServi);
    }

    @Test
    void testToString() {
        gusteau = Chef.getInstance("gusteau");
        assertEquals("Chef: {Nom: 'gusteau'}",gusteau.toString());
    }
}


class PlatAuMenuTest {
    PlatAuMenu platAuMenu = new PlatAuMenu(20,"Filet mignon",15.99);

    PlatAuMenuTest() throws PlatException {
    }
    @Test
    void testToString() {
        assertEquals("menufact.plats.PlatAuMenu{code=20, description='Filet mignon', prix=15.99}", platAuMenu.toString(), "Erreur dans le testtoString");
    }

    @Test
    void getCode() {
        assertEquals(20, platAuMenu.getCode(), "Erreur dans la fonction getCode");
    }

    @Test
    void setCode() {
        platAuMenu.setCode(21);
        assertEquals(21, platAuMenu.getCode(), "Erreur dans la fonction setCode");
    }

    @Test
    void getDescription() {
        assertEquals("Filet mignon", platAuMenu.getDescription(), "Erreur dans la fonction getDescription");
    }

    @Test
    void setDescription() {
        platAuMenu.setDescription("Bon Filet mignon");
        assertEquals("Bon Filet mignon", platAuMenu.getDescription(), "Erreur dans la fonction getDescription");
    }

    @Test
    void getPrix() {
        assertEquals(15.99, platAuMenu.getPrix());
    }

    @Test
    void setPrix() {
        platAuMenu.setPrix(19.99);
        assertEquals(19.99, platAuMenu.getPrix());
    }

    @Test
    void getProportion() {
        assertEquals(1, platAuMenu.getProportion());
    }

    @Test
    void getRecette() throws IngredientException, PlatException {

        Ingredient pain = new Legume("pain", new etatSolide(1));
        Ingredient tomate = new Legume("tomate", new etatSolide(1));
        Ingredient fromage = new Laitier("fromage", new etatSolide(1));
        IngredientPlat bruschetta = new IngredientPlat(new Ingredient[]{pain, fromage, tomate});
        platAuMenu.setRecette(bruschetta);
        assertEquals(bruschetta, platAuMenu.getRecette(), "Erreur");
    }

    @Test
    void setRecette() {
        platAuMenu.setPrix(19.99);
        assertEquals(19.99, platAuMenu.getPrix());
    }
}

class MenuTest {
    Menu menu;
    Menu menu2;

    @Test
    void getInstance() throws PlatException, MenuException {
        menu = Menu.getInstance();
        PlatAuMenu choucroute = new PlatAuMenu(1, "coucroute", 20);
        menu.ajoute(choucroute);

        menu2 = Menu.getInstance();
        PlatAuMenu ragout = new PlatAuMenu(1, "ragout", 10);
        menu2.ajoute(ragout);

        assertEquals(menu, menu2, "Erreur");
    }

    @Test
    void setDescription() {
        menu = Menu.getInstance();
        menu.setDescription("Menu du resto le meilleur en ville");
        assertEquals("Menu du resto le meilleur en ville", menu.getDescription());

    }

    @Test
    void getDescription() {
        menu = Menu.getInstance();
        assertEquals("Menu du resto le meilleur en ville", menu.getDescription());

    }



    @Test
    void ajouteEtNavigationTest() throws PlatException, MenuException {
        menu = Menu.getInstance();

        PlatAuMenu canardConfie = new PlatAuMenu(45, "Canard de riche", 69);
        PlatAuMenu crevette = new PlatAuMenu(45, "Crevette de riche", 69);
        PlatAuMenu crocodile = new PlatAuMenu(45, "Crocodile de riche", 69);
        menu.ajoute(canardConfie);
        menu.ajoute(crevette);
        menu.ajoute(crocodile);
        menu.position(2);
        assertEquals(menu.platCourant(), canardConfie);

        menu.position(4);
        assertEquals(menu.platCourant(), crocodile, "Erreur");

        menu.positionPrecedente();
        menu.positionPrecedente();
        assertEquals(menu.platCourant(),canardConfie, "Erreur");

        menu.positionSuivante();
        assertEquals(menu.platCourant(), crevette, "Erreur");
    }
    
}

class PlatEnfantTest {

    PlatEnfant platEnfant = new PlatEnfant(20, "Filet mignon", 9.99, 0.5);

    PlatEnfantTest() throws PlatException {
    }
    @Test
    void getProportion() {
        assertEquals(0.5, platEnfant.getProportion(), "Error dans la fonction getProportion");
    }

    @Test
    void setProportion() {
        platEnfant.setProportion(0.75);
        assertEquals(0.75, platEnfant.getProportion(), "Errror dans la fonction setProportion");
    }

    @Test
    void testToString() {
        assertEquals("PlatEnfant{proportion=0.5} menufact.plats.PlatAuMenu{code=20, description='Filet mignon', prix=9.99}", platEnfant.toString(), "Erreur dans la fonction toString");
    }
}

class PlatSanteTest {
    PlatSante platSante = new PlatSante(23, "Filet mignon", 43.99, 83.44, 23.11, 1.22);

    PlatSanteTest() throws PlatException {
    }
    @Test
    void testToString() {
        assertEquals("menufact.plats.PlatSante{kcal=83.44, chol=23.11, gras=1.22} menufact.plats.PlatAuMenu{code=23, description='Filet mignon', prix=43.99}", platSante.toString(), "Erreur fonction toString");
    }

    @Test
    void getKcal() {
        assertEquals(83.44, platSante.getKcal(), "Erreur fonction getKcal");
    }

    @Test
    void setKcal() {
        platSante.setKcal(42.01);
        assertEquals(42.01, platSante.getKcal(), "Erreur fonction setKcal");
    }

    @Test
    void getChol() {
        assertEquals(23.11, platSante.getChol(), "Erreur fonction getChol");
    }

    @Test
    void setChol() {
        platSante.setChol(3.03);
        assertEquals(3.03, platSante.getChol(), "Erreur fonction setKcal");
    }

    @Test
    void getGras() {
        assertEquals(1.22, platSante.getGras(), "Erreur fonction getChol");
    }

    @Test
    void setGras() {
        platSante.setGras(0.75);
        assertEquals(0.75, platSante.getGras(), "Erreur fonction setKcal");
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
        assertEquals(false, etatCommande.changerEtat(new EtatImpossible()));
        assertEquals(false, etatCommande.changerEtat(new EtatServi()));
        assertEquals(false, etatCommande.changerEtat(new EtatTerminer()));
    }

    @Test
    public void testEtatEnPreparation() {
        EtatPlat etatEnPreparation = new EtatEnPreparation();
        assertEquals(false, etatEnPreparation.changerEtat(new EtatServi()));
        assertEquals(true, etatEnPreparation.changerEtat(new EtatTerminer()));
        assertEquals(false, etatEnPreparation.changerEtat(new EtatImpossible()));
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
        assertEquals(false, etatServi.changerEtat(new EtatImpossible()));
        assertEquals(false, etatServi.changerEtat(new EtatCommande()));
        assertEquals(false, etatServi.changerEtat(new EtatEnPreparation()));
        assertEquals(false, etatServi.changerEtat(new EtatTerminer()));
    }

    @Test
    public void testEtatTerminer() {
        EtatPlat etatTerminer = new EtatTerminer();
        assertEquals(true, etatTerminer.changerEtat(new EtatServi()));
        assertEquals(false, etatTerminer.changerEtat(new EtatImpossible()));
        assertEquals(false, etatTerminer.changerEtat(new EtatCommande()));
        assertEquals(false, etatTerminer.changerEtat(new EtatEnPreparation()));
    }
}
class PlatChoisiTest {
    private PlatAuMenu plat;
    private PlatChoisi platChoisi;

    @BeforeEach
    public void setUp() throws PlatException {
        plat = new PlatAuMenu();
        plat.setDescription("Plat de test");
        plat.setPrix(9.99);
        platChoisi = new PlatChoisi(plat, 2);
    }

    @Test
    public void testToString() {
        String expectedString = "menufact.plats.PlatChoisi{quantite=2, plat=menufact.plats.PlatAuMenu{code=0, description='Plat de test', prix=9.99}}";
        assertEquals(expectedString, platChoisi.toString());
    }

    @Test
    public void testGetQuantite() {
        assertEquals(2, platChoisi.getQuantite());
    }

    @Test
    public void testSetQuantite() throws PlatException {
        platChoisi.setQuantite(5);
        assertEquals(5, platChoisi.getQuantite());
    }

    @Test
    public void testSetQuantiteNegative() {
        try {
            platChoisi.setQuantite(-1);
        } catch (PlatException e) {
            assertEquals("PlatException: La quantite est négative", e.getMessage());
        }
    }

    @Test
    public void testGetPlat() {
        assertEquals(plat, platChoisi.getPlat());
    }

    @Test
    public void testGetEtat() {
        assertEquals(null, platChoisi.getEtat());
    }

    @Test
    public void testSetEtat() throws PlatException {
        EtatCommande etatCommande = new EtatCommande();
        platChoisi.setEtat(etatCommande);
        assertEquals(etatCommande, platChoisi.getEtat());
    }

    public void testSetEtatIncompatible() {
        EtatEnPreparation etatEnPreparation = new EtatEnPreparation();
        try {
            platChoisi.setEtat(etatEnPreparation);
            fail("Une exception PlatException aurait dû être levée");
        } catch (PlatException e) {
            assertEquals("Impossible de changer l'état", e.getMessage());
        }
    }
}
class IngredientPlatTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private IngredientPlat ingredientPlat;

    @BeforeEach
    public void setUp() throws IngredientException {
        ingredient1 = new Viande("Ingredient 1", new etatSolide(10));
        ingredient2 = new Laitier("Ingredient 2", new etatLiquide(20));
        ingredient3 = new Fruit("Ingredient 3", new etatSolide(30));
        ingredientPlat = new IngredientPlat(new Ingredient[] {ingredient1, ingredient2});
    }

    @Test
    public void testGetIngredients() {
        assertEquals(Arrays.asList(ingredient1, ingredient2), ingredientPlat.getIngredients());
    }

    @Test
    public void testSetIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(ingredient1, ingredient2, ingredient3));
        ingredientPlat.setIngredients(ingredients);
        assertEquals(ingredients, ingredientPlat.getIngredients());
    }

    @Test
    public void testSetIngredientsArray() {
        ingredientPlat.setIngredients(new Ingredient[] {ingredient1, ingredient2, ingredient3});
        assertEquals(Arrays.asList(ingredient1, ingredient2, ingredient3), ingredientPlat.getIngredients());
    }

    @Test
    public void testAddIngredients() throws IngredientException {
        Ingredient ingredientToAdd = new Epice("Ingredient 2", new etatSolide(15));
        ingredientPlat.addIngredients(ingredientToAdd);
        assertEquals(35, ingredient2.getQuantite());
        String expectedString = "[Ingredient : Ingredient 1 Etat : 'Solide' :  {\n\t 'Qty (kg)':10.0\n}\n, Ingredient : Ingredient 2 Etat : 'Liquide' :  {\n\t 'Qty (L)':35.0\n}\n, Ingredient : Ingredient 2 Etat : 'Solide' :  {\n\t 'Qty (kg)':15.0\n}\n]";
        assertEquals(expectedString, ingredientPlat.getIngredients().toString());
    }

    @Test
    public void testToString() {
        String expectedString = "Recette:[Ingredient : Ingredient 1 Etat : 'Solide' :  {\n\t 'Qty (kg)':10.0\n}\n, Ingredient : Ingredient 2 Etat : 'Liquide' :  {\n\t 'Qty (L)':20.0\n}\n]";
        assertEquals(expectedString, ingredientPlat.toString());
    }

    @Test
    public void testEquals() {
        IngredientPlat otherPlat = new IngredientPlat(new Ingredient[] {ingredient1, ingredient2});
        assertEquals(true, ingredientPlat.equals(otherPlat));
    }
}

class EtatSolideTest {
    private etatSolide etat;

    @BeforeEach
    public void setUp() throws IngredientException {
        etat = new etatSolide(2.5);
    }

    @Test
    public void testGetQuantite() {
        assertEquals(2.5, etat.getQuantite(), 0.0);
    }

    @Test
    public void testSetQuantite() throws IngredientException {
        etat.setQuantite(3.0);
        assertEquals(3.0, etat.getQuantite(), 0.0);
    }

    @Test
    public void testSetQuantiteNegative() {
        Assertions.assertThrows(IngredientException.class, () -> {
            etat.setQuantite(-1.0);
        });
    }

    @Test
    public void testGetEtat() {
        assertEquals("solide", etat.getEtat());
    }

    @Test
    public void testToString() {
        String expectedString = "'Solide' :  {\n\t 'Qty (kg)':2.5\n}";
        assertEquals(expectedString, etat.toString());
    }

    @Test
    public void testEquals() throws IngredientException {
        etatSolide otherEtat = new etatSolide(2.5);
        assertEquals(true, etat.equals(otherEtat));
    }
}
class EtatLiquideTest {
    private etatLiquide etat;

    @BeforeEach
    public void setUp() throws IngredientException {
        etat = new etatLiquide(1.5);
    }

    @Test
    public void testGetQuantite() {
        assertEquals(1.5, etat.getQuantite(), 0.0);
    }

    @Test
    public void testSetQuantite() throws IngredientException {
        etat.setQuantite(2.0);
        assertEquals(2.0, etat.getQuantite(), 0.0);
    }

    @Test
    public void testSetQuantiteNegative() {
        Assertions.assertThrows(IngredientException.class, () -> {
            etat.setQuantite(-1.0);
        });
    }

    @Test
    public void testGetEtat() {
        assertEquals("liquide", etat.getEtat());
    }

    @Test
    public void testToString() {
        String expectedString = "'Liquide' :  {\n\t 'Qty (L)':1.5\n}";
        assertEquals(expectedString, etat.toString());
    }

    @Test
    public void testEquals() throws IngredientException {
        etatLiquide otherEtat = new etatLiquide(1.5);
        assertEquals(true, etat.equals(otherEtat));
    }
}

class ConcreteCreatorTest {
    private concretecreatorEpice creator;
    private concretecreatorLegume creator2;
    private concretecreatorFruit creator3;
    private concretecreatorViande creator4;
    private concretecreatorLaitier creator5;
    TypeIngredient type;
    private groupeIngredient groupe = new groupeIngredient(TypeIngredient.EPICE, new etatSolide(10));
    private groupeIngredient groupe2 = new groupeIngredient(TypeIngredient.VIANDE, new etatSolide(10));
    private groupeIngredient groupe3 = new groupeIngredient(TypeIngredient.FRUIT, new etatSolide(10));
    private groupeIngredient groupe4 = new groupeIngredient(TypeIngredient.LAITIER, new etatSolide(10));
    private groupeIngredient groupe5 = new groupeIngredient(TypeIngredient.LEGUME, new etatSolide(10));

    ConcreteCreatorTest() throws IngredientException {
    }

    @BeforeEach
    public void setUp() {
        creator = new concretecreatorEpice();
        creator2 = new concretecreatorLegume();
        creator3 = new concretecreatorFruit();
        creator4 = new concretecreatorViande();
        creator5 = new concretecreatorLaitier();

    }

    @Test
    public void testCreer() throws IngredientException {
        etatSolide etat = new etatSolide(20);
        Ingredient ingredient = creator.creer("Cumin", etat);
        assertEquals("Cumin", ingredient.getNom());
        assertEquals(etat, ingredient.getEtat());
        assertEquals(Epice.class, ingredient.getClass());

        etatSolide etat2 = new etatSolide(20);
        Ingredient ingredient2 = creator2.creer("Ingredient 2", etat2);
        assertEquals("Ingredient 2", ingredient2.getNom());
        assertEquals(etat2, ingredient2.getEtat());
        assertEquals(Legume.class, ingredient2.getClass());

        etatSolide etat3 = new etatSolide(20);
        Ingredient ingredient3 = creator3.creer("Ingredient 3", etat3);
        assertEquals("Ingredient 3", ingredient3.getNom());
        assertEquals(etat3, ingredient3.getEtat());
        assertEquals(Fruit.class, ingredient3.getClass());

        etatSolide etat4 = new etatSolide(20);
        Ingredient ingredient4 = creator4.creer("Ingredient 4", etat4);
        assertEquals("Ingredient 4", ingredient4.getNom());
        assertEquals(etat4, ingredient4.getEtat());
        assertEquals(Viande.class, ingredient4.getClass());

        etatSolide etat5 = new etatSolide(20);
        Ingredient ingredient5 = creator5.creer("Cumin", etat5);
        assertEquals("Cumin", ingredient5.getNom());
        assertEquals(etat5, ingredient5.getEtat());
        assertEquals(Laitier.class, ingredient5.getClass());
    }

    @Test
    public void testCreerWithGroupe() throws IngredientException {
        Ingredient ingredient = concretecreatorEpice.creer(groupe, "Cumin");
        assertEquals("Cumin", ingredient.getNom());
        assertEquals(groupe, ingredient.getGroupe());
        assertEquals(Epice.class, ingredient.getClass());

        Ingredient ingredient2 = concretecreatorLegume.creer(groupe2, "Groupe2");
        assertEquals("Groupe2", ingredient2.getNom());
        assertEquals(groupe2, ingredient2.getGroupe());
        assertEquals(Legume.class, ingredient2.getClass());

        Ingredient ingredient3 = concretecreatorFruit.creer(groupe3, "Groupe3");
        assertEquals("Groupe3", ingredient3.getNom());
        assertEquals(groupe3, ingredient3.getGroupe());
        assertEquals(Fruit.class, ingredient3.getClass());

        Ingredient ingredient4 = concretecreatorViande.creer(groupe4, "Groupe4");
        assertEquals("Groupe4", ingredient4.getNom());
        assertEquals(groupe4, ingredient4.getGroupe());
        assertEquals(Viande.class, ingredient4.getClass());

        Ingredient ingredient5 = concretecreatorLaitier.creer(groupe5, "Groupe5");
        assertEquals("Groupe5", ingredient5.getNom());
        assertEquals(groupe5, ingredient5.getGroupe());
        assertEquals(Laitier.class, ingredient5.getClass());
    }

    @Test
    public void testCreerWithNullGroupe() {
        assertThrows(IngredientException.class, () -> {
            concretecreatorEpice.creer(null, "Cumin");
        });

        assertThrows(IngredientException.class, () -> {
            concretecreatorLegume.creer(null, "Groupe2");
        });

        assertThrows(IngredientException.class, () -> {
            concretecreatorFruit.creer(null, "Groupe3");
        });

        assertThrows(IngredientException.class, () -> {
            concretecreatorViande.creer(null, "Groupe4");
        });

        assertThrows(IngredientException.class, () -> {
            concretecreatorLaitier.creer(null, "Groupe5");
        });
    }
}

class GroupeIngredientTest {

    @Test
    public void testGetEtat() throws IngredientException {
        etatSolide etat = new etatSolide(20);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.FRUIT, etat);
        assertEquals(etat, groupe.getEtat());
    }

    @Test
    public void testGetType() throws IngredientException {
        etatSolide etat = new etatSolide(20);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.FRUIT, etat);
        assertEquals(TypeIngredient.FRUIT, groupe.getType());
    }
}
class EpiceTest {

    @Test
    public void testConstructorWithNomAndEtat() throws IngredientException {
        etatSolide etat = new etatSolide(13.5);
        Epice epice = new Epice("Cumin", etat);
        assertEquals("Cumin", epice.getNom());
        assertEquals(etat, epice.getEtat());
    }

    @Test
    public void testConstructorWithNomEtatAndQuantite() throws IngredientException {
        etatSolide etat = new etatSolide(1);
        Epice epice = new Epice("Cumin", etat, 0.5);
        assertEquals("Cumin", epice.getNom());
        assertEquals(etat, epice.getEtat());
        assertEquals(0.5, epice.getQuantite(), 0.001);
    }

    @Test
    public void testConstructorWithGroupeIngredientAndNom() throws IngredientException {
        etatSolide etat = new etatSolide(2);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.EPICE, etat);
        Epice epice = new Epice(groupe, "Cumin");
        assertEquals("Cumin", epice.getNom());
        assertEquals(groupe, epice.getGroupe());
        assertEquals(etat, epice.getEtat());
    }
}

class FruitTest {

    @Test
    public void testConstructorWithNomAndEtat() throws IngredientException {
        etatSolide etat = new etatSolide(3);
        Fruit fruit = new Fruit("Banane", etat);
        assertEquals("Banane", fruit.getNom());
        assertEquals(etat, fruit.getEtat());
    }

    @Test
    public void testConstructorWithNomEtatAndQuantite() throws IngredientException {
        etatSolide etat = new etatSolide(5);
        Fruit fruit = new Fruit("Banane", etat, 0.5);
        assertEquals("Banane", fruit.getNom());
        assertEquals(etat, fruit.getEtat());
        assertEquals(0.5, fruit.getQuantite(), 0.001);
    }

    @Test
    public void testConstructorWithGroupeIngredientAndNom() throws IngredientException {
        etatSolide etat = new etatSolide(7);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.FRUIT, etat);
        Fruit fruit = new Fruit(groupe, "Banane");
        assertEquals("Banane", fruit.getNom());
        assertEquals(groupe, fruit.getGroupe());
        assertEquals(etat, fruit.getEtat());
    }
}
class LaitierTest {

    @Test
    public void testConstructorWithNomAndEtat() throws IngredientException {
        etatLiquide etat = new etatLiquide(3);
        Laitier laitier = new Laitier("Lait", etat);
        assertEquals("Lait", laitier.getNom());
        assertEquals(etat, laitier.getEtat());
    }

    @Test
    public void testConstructorWithNomEtatAndQuantite() throws IngredientException {
        etatLiquide etat = new etatLiquide(6);
        Laitier laitier = new Laitier("Lait", etat, 1.5);
        assertEquals("Lait", laitier.getNom());
        assertEquals(etat, laitier.getEtat());
        assertEquals(1.5, laitier.getQuantite(), 0.001);
    }

    @Test
    public void testConstructorWithGroupeIngredientAndNom() throws IngredientException {
        etatLiquide etat = new etatLiquide(99);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.LAITIER, etat);
        Laitier laitier = new Laitier(groupe, "Lait");
        assertEquals("Lait", laitier.getNom());
        assertEquals(groupe, laitier.getGroupe());
        assertEquals(etat, laitier.getEtat());
    }
}

class LegumeTest {

    @Test
    public void testConstructorWithNomAndEtat() throws IngredientException {
        etatSolide etat = new etatSolide(14);
        Legume legume = new Legume("Carotte", etat);
        assertEquals("Carotte", legume.getNom());
        assertEquals(etat, legume.getEtat());
    }

    @Test
    public void testConstructorWithNomEtatAndQuantite() throws IngredientException {
        etatSolide etat = new etatSolide(0.5);
        Legume legume = new Legume("Carotte", etat, 0.5);
        assertEquals("Carotte", legume.getNom());
        assertEquals(etat, legume.getEtat());
        assertEquals(0.5, legume.getQuantite(), 0.001);
    }

    @Test
    public void testConstructorWithGroupeIngredientAndNom() throws IngredientException {
        etatSolide etat = new etatSolide(44);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.LEGUME, etat);
        Legume legume = new Legume(groupe, "Carotte");
        assertEquals("Carotte", legume.getNom());
        assertEquals(groupe, legume.getGroupe());
        assertEquals(etat, legume.getEtat());
    }
}

class ViandeTest {

    @Test
    public void testConstructorWithNomAndEtat() throws IngredientException {
        etatSolide etat = new etatSolide(0.1);
        Viande viande = new Viande("Boeuf", etat);
        assertEquals("Boeuf", viande.getNom());
        assertEquals(etat, viande.getEtat());
    }

    @Test
    public void testConstructorWithNomEtatAndQuantite() throws IngredientException {
        etatSolide etat = new etatSolide(5);
        Viande viande = new Viande("Boeuf", etat, 0.5);
        assertEquals("Boeuf", viande.getNom());
        assertEquals(etat, viande.getEtat());
        assertEquals(0.5, viande.getQuantite(), 0.001);
    }

    @Test
    public void testConstructorWithGroupeIngredientAndNom() throws IngredientException {
        etatSolide etat = new etatSolide(22);
        groupeIngredient groupe = new groupeIngredient(TypeIngredient.VIANDE, etat);
        Viande viande = new Viande(groupe, "Boeuf");
        assertEquals("Boeuf", viande.getNom());
        assertEquals(groupe, viande.getGroupe());
        assertEquals(etat, viande.getEtat());
    }
}

class FactureControllerTest {

    private Inventaire inventaire;
    ArrayList<Ingredient> ingredientsRecette=new ArrayList<>();
    groupeIngredient groupebanane;
    Ingredient banane;
    IngredientPlat recette;
    PlatAuMenu platAuMenu;
    PlatChoisi platChoisi;
    Facture facture;
    FactureView view;
    FactureController controller;
    Client Snitch;
    Chef gustau;

    @BeforeEach
    void setUp() throws IngredientException, PlatException {
        groupebanane=new groupeIngredient(TypeIngredient.FRUIT, new etatSolide(5));
        banane= concretecreatorFruit.creer(groupebanane, "banane");

        ingredientsRecette= new ArrayList<>();
        ingredientsRecette.add(banane);

        inventaire = Inventaire.getInstance();
        inventaire.ajouter(TypeIngredient.FRUIT, new etatSolide(50), "banane");



        recette= new IngredientPlat(ingredientsRecette);

        platAuMenu= new PlatAuMenu(1, "menoum plat aux fruits", 10.0);
        platAuMenu.setRecette(recette);

        platChoisi= new PlatChoisi(platAuMenu, 2);

        facture = new Facture("Ma facture");
        view= new FactureView();
        controller= new FactureController(facture,view);

        Snitch= new Client(01,"Snitch", "abcdef");
        gustau= Chef.getInstance("gustau");

    }

    @Test
    void afficheFacture() throws PlatException, IngredientException, FactureException {
        controller.associerClient(Snitch);
        controller.Observer(gustau);

        String expectedString= "menufact.facture.Facture{date="+ facture.getDate() + ", description='Ma facture', etat=Facture etat ouverte, platchoisi=[], courant=-1, client=menufact.Client{idClient=1, nom='Snitch', numeroCarteCredit='abcdef'}, TPS=0.05, TVQ=0.095}";


        assertEquals(expectedString, controller.updateViewtoString());


        String expectedString2= "menufact.facture.Facture{date="+ facture.getDate() + ", description='Ma facture', etat=Facture etat ouverte, platchoisi=[menufact.plats.PlatChoisi{quantite=2, plat=menufact.plats.PlatAuMenu{code=1, description='menoum plat aux fruits', prix=10.0}}], courant=-1, client=menufact.Client{idClient=1, nom='Snitch', numeroCarteCredit='abcdef'}, TPS=0.05, TVQ=0.095}";
        controller.ajoutPlat(platChoisi);
        assertEquals(expectedString2, controller.updateViewtoString());

    }

    @Test
    void genereFacture() throws IngredientException, PlatException, MenuException, FactureException {
        controller.associerClient(Snitch);
        controller.Observer(gustau);

        controller.ajoutPlat(platChoisi);
        controller.payer();
        String expectedString="Facture generee.\n" +
                "Date:"+ facture.getDate()+ "\n" +
                "Description: Ma facture\n" +
                "Client:Snitch\n" +
                "Les plats commandes:\n" +
                "Seq   Plat         Prix   Quantite\n" +
                "1     menoum plat aux fruits  10.0      2\n" +
                "          TPS:               1.0\n" +
                "          TVQ:               1.9\n" +
                "          Le total est de:   22.9\n";
        assertEquals(expectedString,controller.updateViewGenererFacture());


    }

    @Test
    void associerClient(){
        controller.associerClient(Snitch);
        assertEquals(Snitch,facture.getClient());
    }

    @Test
    void observer(){
        controller.Observer(gustau);
        assertEquals(gustau,facture.getChef());

    }

    @Test
    void payer() throws FactureException {
        controller.payer();
        assertTrue(facture.getEtat() instanceof FactureEtatPayee);
    }
    @Test
    void fermer() throws FactureException {
        controller.fermer();
        assertTrue(facture.getEtat() instanceof FactureEtatFermee);
    }

    @Test
    void ouvrir() throws FactureException {
        controller.fermer();
        controller.ouvrir();
        assertTrue(facture.getEtat() instanceof FactureEtatOuverte);
    }

    @Test
    void ajoutePlat() throws PlatException, FactureException {
        controller.Observer(gustau);
        controller.ajoutPlat(platChoisi);
        ArrayList<PlatChoisi> expectedPlats = new ArrayList<>();
        expectedPlats.add(platChoisi);
        assertEquals(expectedPlats, facture.getPlatChoisi() );

    }

    @Test
    void getSousTotal() throws PlatException, FactureException {
        controller.Observer(gustau);
        controller.ajoutPlat(platChoisi);
        double expectedSousTotal= 20.0;
        assertEquals(expectedSousTotal, controller.getSousTotal() );

    }

    @Test
    void getTps() throws PlatException, FactureException {
        controller.Observer(gustau);
        controller.ajoutPlat(platChoisi);
        double expectedTps= 20.0*0.05;
        assertEquals(expectedTps, controller.getTps() );

    }

    @Test
    void getTvq() throws PlatException, FactureException {
        controller.Observer(gustau);
        controller.ajoutPlat(platChoisi);
        double expectedTvq= 20.0*0.095;
        assertEquals(expectedTvq, controller.getTvq());
    }

    void getTotal() throws PlatException, FactureException {
        controller.Observer(gustau);
        controller.ajoutPlat(platChoisi);
        double expectedTotal= 20.0*0.095+20.0*0.05+20.0;
        assertEquals(expectedTotal,controller.getTotal());

    }
}

class FactureEtatFermeeTest {

    @Test
    public void testChangerEtat() {
        FactureEtatFermee etatFermee = new FactureEtatFermee();

        // Test avec un état valide pour changer en FactureEtatPayee
        boolean expected = true;
        boolean actual = etatFermee.changerEtat(new FactureEtatPayee());
        assertEquals(expected, actual);

        // Test avec un état valide pour changer en FactureEtatOuverte
        expected = true;
        actual = etatFermee.changerEtat(new FactureEtatOuverte());
        assertEquals(expected, actual);

        // Test avec un état invalide pour changer en FactureEtatFermee
        expected = false;
        actual = etatFermee.changerEtat(new FactureEtatFermee());
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        FactureEtatFermee etatFermee = new FactureEtatFermee();

        // Test du contenu de la chaîne de caractères renvoyée
        String expected = "Facture etat fermee";
        String actual = etatFermee.toString();
        assertEquals(expected, actual);
    }
}

class FactureEtatOuverteTest {

    @Test
    public void testChangerEtat() {
        FactureEtatOuverte etatOuverte = new FactureEtatOuverte();

        // Test avec un état valide pour changer en FactureEtatFermee
        boolean expected = true;
        boolean actual = etatOuverte.changerEtat(new FactureEtatFermee());
        assertEquals(expected, actual);

        // Test avec un état valide pour changer en FactureEtatPayee
        expected = true;
        actual = etatOuverte.changerEtat(new FactureEtatPayee());
        assertEquals(expected, actual);

        // Test avec un état invalide pour changer en FactureEtatOuverte
        expected = false;
        actual = etatOuverte.changerEtat(new FactureEtatOuverte());
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        FactureEtatOuverte etatOuverte = new FactureEtatOuverte();

        // Test du contenu de la chaîne de caractères renvoyée
        String expected = "Facture etat ouverte";
        String actual = etatOuverte.toString();
        assertEquals(expected, actual);
    }
}

class FactureEtatPayeeTest {

    @Test
    public void testChangerEtat() {
        FactureEtatPayee etatPayee = new FactureEtatPayee();

        // Test que l'état ne peut pas être changé en FactureEtatOuverte
        boolean expected = false;
        boolean actual = etatPayee.changerEtat(new FactureEtatOuverte());
        assertEquals(expected, actual);

        // Test que l'état ne peut pas être changé en FactureEtatFermee
        expected = false;
        actual = etatPayee.changerEtat(new FactureEtatFermee());
        assertEquals(expected, actual);

        // Test que l'état ne peut pas être changé en FactureEtatPayee
        expected = false;
        actual = etatPayee.changerEtat(new FactureEtatPayee());
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        FactureEtatPayee etatPayee = new FactureEtatPayee();

        // Test du contenu de la chaîne de caractères renvoyée
        String expected = "Facture etat payee";
        String actual = etatPayee.toString();
        assertEquals(expected, actual);
    }
}
//public class TestMenuFact02 {
//
//
//    public static void main(String[] args) throws PlatException {
//        boolean trace = true;
//
//        TestMenuFact02 t = new TestMenuFact02();
//
//        PlatAuMenu p1 = new PlatAuMenu(0,"PlatAuMenu0",10);
//        PlatAuMenu p2 = new PlatAuMenu(1,"PlatAuMenu1",20);
//        PlatAuMenu p3 = new PlatAuMenu(2,"PlatAuMenu2",30);
//        PlatAuMenu p4 = new PlatAuMenu(3,"PlatAuMenu3",40);
//        PlatAuMenu p5 = new PlatAuMenu(4,"PlatAuMenu4",50);
//
//
//        PlatSante ps1 = new PlatSante(10,"PlatSante0",10,11,11,11);
//        PlatSante ps2 = new PlatSante(11,"PlatSante1",20,11,11,11);
//        PlatSante ps3 = new PlatSante(12,"PlatSante2",30,11,11,11);
//        PlatSante ps4 = new PlatSante(13,"PlatSante3",40,11,11,11);
//        PlatSante ps5 = new PlatSante(14,"PlatSante4",50,11,11,11);
//
//
//        Menu m1 = new Menu("menufact.Menu 1");
//        Menu m2 = new Menu("menufact.Menu 2");
//
//        Facture f1 = new Facture("Ma facture");
//
//        Client c1 = new Client(1,"Mr Client","1234567890");
//
//
//        t.test1_AffichePlatsAuMenu(trace, p1,p2,p3,p4,p5);
//        t. test2_AffichePlatsSante(trace, ps1,ps2,ps3,ps4,ps5);
//
//        t.test4_AjoutPlatsAuMenu(trace, m1, p1, p2, ps1, ps2, m2, p3, p4, ps3, ps4);
//
//
//        try {
//            t.test5_DeplacementMenuAvancer(m1);
//        } catch (MenuException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            t.test6_DeplacementMenuReculer(m1);
//        } catch (MenuException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            t.test7_CreerFacture(f1, m1);
//        } catch (FactureException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        t.test8_AjouterClientFacture(f1, c1);
//
//        try {
//            t.test8_AjouterPlatsFacture(f1, m1,1);
//        } catch (FactureException fe)
//        {
//            System.out.println(fe.getMessage());
//        }
//        catch (MenuException me)
//        {
//            System.out.println(me);
//        }
//
//        t.test9_PayerFacture(f1);
//
//        try {
//            t.test8_AjouterPlatsFacture(f1, m1,1);
//        } catch (FactureException fe)
//        {
//            System.out.println(fe.getMessage());
//        }
//        catch (MenuException me)
//        {
//            System.out.println(me);
//        }
//
//        try {
//            f1.ouvrir();
//        } catch (FactureException fe)
//        {
//            System.out.println(fe.getMessage());
//        }
//
//
//
//
//
//
//        System.out.println("FIN DE TOUS LES TESTS...");
//
//        System.out.println(f1.genererFacture());
//    }
//
//    private void test1_AffichePlatsAuMenu(boolean trace, PlatAuMenu p1, PlatAuMenu p2,
//                                                 PlatAuMenu p3, PlatAuMenu p4, PlatAuMenu p5)
//    {
//        System.out.println("=== test1_AffichePlatsAuMenu");
//        if(trace)
//        {
//            System.out.println(p1);
//            System.out.println(p2);
//            System.out.println(p3);
//            System.out.println(p4);
//            System.out.println(p5);
//        }
//    }
//
//
//   private void test2_AffichePlatsSante(boolean trace, PlatSante ps1, PlatSante ps2,
//                                               PlatSante ps3, PlatSante ps4, PlatSante ps5)
//    {
//        System.out.println("=== test2_AffichePlatsSante");
//
//        if(trace)
//        {
//            System.out.println(ps1);
//            System.out.println(ps2);
//            System.out.println(ps3);
//            System.out.println(ps4);
//            System.out.println(ps5);
//        }
//    }
//
//
//    private static void test3_AjoutMenu(boolean trace, Menu m1, Menu m2)
//    {
//
//        System.out.println("=== test3_AjoutMenu");
//
//        if(trace) {
//            System.out.println(m1);
//            System.out.println(m2);
//        }
//    }
//
//
//    private void test4_AjoutPlatsAuMenu(boolean trace, Menu m1,
//                                        PlatAuMenu p1, PlatAuMenu p2,
//                                        PlatSante ps1, PlatSante ps2,
//                                        Menu m2,
//                                        PlatAuMenu p3, PlatAuMenu p4,
//                                        PlatSante ps3, PlatSante ps4)
//    {
//        System.out.println("=== test4_AjoutPlatsAuMenu");
//        System.out.println("=== Ajout de plats au menu 1");
//        m1.ajoute(p1);
//        m1.ajoute(p2);
//        m1.ajoute(ps1);
//        m1.ajoute(ps2);
//
//
//        System.out.println("=== Ajout de plats au menu 2");
//        m2.ajoute(p3);
//        m2.ajoute(p4);
//        m2.ajoute(ps3);
//        m2.ajoute(ps4);
//
//        if(trace) {
//            System.out.println(m1);
//            System.out.println(m2);
//        }
//    }
//
//
//    private void test5_DeplacementMenuAvancer(Menu m1) throws MenuException
//    {
//        System.out.println("=== test5_DeplacementMenuAvancer");
//
//        System.out.println("===Selectionner un plat du menu 0");
//        m1.position(0);
//
//        System.out.println("=== Afficher le plat courant");
//        System.out.println(m1.platCourant());
//        try {
//
//            System.out.println("=== Avancer le plat courant");
//            System.out.println("1.");
//            m1.positionSuivante();
//            System.out.println("2.");
//            m1.positionSuivante();
//            System.out.println("3.");
//            m1.positionSuivante();
//            System.out.println("4.");
//            m1.positionSuivante();
//            System.out.println("5.");
//            m1.positionSuivante();
//        }
//        catch (MenuException me)
//        {
//                throw me;
//        }
//    }
//
//
//    private void test6_DeplacementMenuReculer(Menu m1) throws MenuException
//    {
//        System.out.println("===test6_DeplacementMenuReculer");
//
//        System.out.println("===Selectionner un plat du menu 3");
//        m1.position(3);
//
//        System.out.println("=== Afficher le plat courant");
//        System.out.println(m1.platCourant());
//        try {
//
//            System.out.println("=== Reculer le plat courant");
//            System.out.println("2.");
//            m1.positionPrecedente();
//            System.out.println("1.");
//            m1.positionPrecedente();
//            System.out.println("0.");
//            m1.positionPrecedente();
//            System.out.println("-1.");
//            m1.positionPrecedente();
//            System.out.println("-2.");
//            m1.positionPrecedente();
//        }
//        catch (MenuException me)
//        {
//            throw me;
//        }
//    }
//
//    private void test7_CreerFacture(Facture f1, Menu m1) throws FactureException
//    {
//        System.out.println("===test7_CreerFacture");
//
//        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(),5);
//        try
//        {
//            f1.ajoutePlat(platChoisi);
//        }
//        catch (FactureException fe)
//        {
//            throw fe;
//        }
//        System.out.println(f1);
//    }
//
//
//    private void test8_AjouterClientFacture(Facture f1,Client c1) {
//        System.out.println("===test8_AjouterClientFacture");
//        f1.associerClient(c1);
//        System.out.println(f1);
//    }
//    private void test8_AjouterPlatsFacture(Facture f1, Menu m1, int pos) throws MenuException,FactureException
//    {
//        System.out.println("===test8_AjouterPlatsFacture");
//
//        try{
//            for (int i=0; i< pos; i++)
//                m1.positionSuivante();
//        }
//        catch (MenuException me)
//        {
//            throw me;
//        }
//
//        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(),5);
//        try
//        {
//            f1.ajoutePlat(platChoisi);
//        }
//        catch (FactureException fe)
//        {
//            throw fe;
//        }
//        System.out.println(f1);
//    }
//
//    private void test9_PayerFacture(Facture f1)
//    {
//        System.out.println("===test9_PayerFacture");
//
//        System.out.println("Avant payer la facture");
//        System.out.println(f1);
//        f1.payer();
//        System.out.println("Apres avoir paye la facture");
//        System.out.println(f1);
//    }
//}
