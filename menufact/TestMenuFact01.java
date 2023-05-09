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


class TestIteration{

    public static void main(String[] args) throws IngredientException, PlatException, MenuException, FactureException {
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣠⣠⣶⣶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⢷⣟⡿⠿⣷⣤⣄⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⢀⣾⡟⢛⣽⣶⠿⠷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⣿⠁⠈⣿⣿⠲⣿⢨⣿⠤⢤⠀⠀⠀⠀⠀\n" +
                "⠀⠸⣏⡾⠉⣽⡀⣦⡀⠀⠈⠙⠶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⡟⠀⠀⠘⢿⠠⡏⣼⠃⠀⣸⡤⣤⠀⠀⠀\n" +
                "⢀⣴⠿⣧⡄⠙⠁⢸⡇⠀⠀⠀⠀⠈⠻⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⢇⡀⠀⠀⠀⠀⢰⠏⠀⣰⠋⢠⡿⣶⡄⠀\n" +
                "⠸⠟⢻⡇⠹⣦⣠⡿⠃⠀⠀⠀⠀⠀⣄⠀⠙⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠷⠚⠋⠀⠀⠀⡤⣾⡟⠀⠘⠁⢀⣿⣧⡟⢧⠀\n" +
                "⠀⠀⠸⣇⡀⣸⡏⠀⠀⠀⠀⠀⠀⠀⠘⢷⢿⣦⣙⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠛⢷⠀⠀⣴⣶⡿⠏⢡⣿⣷\n" +
                "⠀⠀⠀⠈⠉⣹⠁⠀⡄⠀⡀⠀⡀⠀⠰⡈⠋⢿⢿⡷⠽⣦⠀⠀⠀⠀⠀⠀⠀⣠⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠷⠤⡼⡁⣀⣴⣧⣀⡏\n" +
                "⠀⠀⠀⠀⢀⡿⠀⢰⢻⡀⣷⣠⣷⣤⠀⠹⡄⠀⠀⠁⢠⣬⣷⠶⠒⠒⠒⣲⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠻⣽⣷⣿⠹⣤\n" +
                "⠀⠀⠀⠀⢸⡇⠀⠘⡎⣧⠛⣇⠹⣿⣀⣠⣤⠴⠚⠉⠀⠀⠀⣀⡤⠔⠈⠉⠙⠒⠲⢤⣤⣀⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⢶⡄⠀⠀⠀⠉⣹⣷⠏\n" +
                "⠀⠀⠀⠀⣿⡇⡀⠀⣷⣿⠀⡼⠚⠻⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠓⠶⢤⣀⠰⠚⣽⠄⠀⣠⣾⡥⠀⠀⠀⠘⡏⠁⠀\n" +
                "⠀⠀⠀⠀⢿⡇⠉⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠈⠙⠆⣦⡀⠘⠋⠉⠀⠀⠀⠀⢰⠇⠀⠀\n" +
                "⠀⠀⠀⠀⢸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠀⠀⠀⣼⣀⣀⣀⣀⣀⣠⣿⣄⠀⢀⣰⣌⣻⠆⣀⡏⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢿⡔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣦⣀⡀⠀⠀⠀⠀⠀⠀⢴⡟⠀⠀⢴⡇⠀⣀⣀⣠⠤⠤⣴⠟⠀⠀⢡⠽⠁⣠⡟⠁⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠘⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⣻⣄⡀⠀⠀⠠⡶⣫⠀⢀⡤⠋⠀⣈⣭⡥⠤⠖⠋⠹⠦⣤⣤⣀⣠⡿⠋⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠘⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠚⠳⢤⣆⠉⠽⠋⠀⢀⣠⠴⠚⠉⠉⠀⠀⠀⠀⠀⠀⠉⢙⡿⠋⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠻⣦⣤⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⢠⣄⣠⡴⣺⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣨⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣦⡀⠀⠀⢀⣀⣀⣠⡤⠉⠉⠉⠉⠀⠀⠀⢷⠀⠀⠀⠀⠀⠀⠀⠘⣻⡭⢥⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣼⡆⢀⡀⠀⠀⠀⠀⠀⠀⣴⡾⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣟⣥⣳⣿⣷⣴⣖⣀⣐⣾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢛⣦⡽⣿⣻⣭⡽⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⡀⠀⠀⠀⠀⠀⠀⠀⣠⣶⠛⢛⣫⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠲⢶⡤⢤⣦⠤⠾⠋⠻⣟⣽⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⢀⡜⠁⠀⠀⠀⠀⠸⣾⡆⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣇⡿⠁⠀⠀⠀⠀⠀⠀⢻⣹⡀⢠⣴⡾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⡀⣀⣠⣿⢹⡀⠀⠀⠀⢀⣀⣀⣠⣤⣷⣙⡽⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣽⠯⠿⢷⣛⣫⡤⠬⢭⣷⡄⠀⢿⣯⠤⠶⠶⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠿⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠈⠃⠀⠀⠀⠀");
        System.out.println("------------DEBUT DES TESTS DE L ITERATION------------\n");

        System.out.println("--Creation d'un chef--");
        Chef chef = Chef.getInstance("gusteau");
        System.out.println(chef.toString() + "\n");

        System.out.println("--------Creation de l'inventaire--------");
        Inventaire congelo = Inventaire.getInstance();
        System.out.println(congelo.toString() + "\n");

        System.out.println("--Creation d'ingredients--");
        Ingredient creme = new Laitier("creme", new etatLiquide(4000));
        Ingredient poulet = new Viande("poulet", new etatSolide(20));
        Ingredient carrotte = new Legume("carrotte", new etatSolide(100));

        Ingredient croquette = new Viande("croquette", new etatSolide(200));
        Ingredient frite = new Legume("frite", new etatSolide(300));
        Ingredient ketchup = new Epice("ketchup", new etatLiquide(2000));

        Ingredient pomme = new Fruit("pomme", new etatSolide(200));
        System.out.println(creme.toString());
        System.out.println(poulet.toString());
        System.out.println(carrotte.toString());
        System.out.println(ketchup.toString() + "\n");

        System.out.println("--------Ajout des ingredients a l'inventaire--------");

        congelo.ajoutIngredient(new Ingredient[]{creme, poulet, carrotte, croquette, frite, ketchup,pomme});
        System.out.println(congelo.toString()+ "\n");

        System.out.println("--------Creation du menu--------");
        Menu menu = Menu.getInstance();
        menu.setDescription("Rotisserie poulet orange pour vous servir");
        System.out.println(menu.toString() + "\n");

        System.out.println("--------Creation de plat--------");
        PlatAuMenu soupe = new PlatAuMenu(1, "Soupe creme de poulet", 5.99);
        PlatAuMenu animalerie = new PlatAuMenu(2, "Croquette de poulet", 10.99);
        PlatAuMenu pommes = new PlatAuMenu(3, "Quartier de pomme", 2.99);
        System.out.println(soupe.toString() + "\n");

        System.out.println("--------Creation de recette--------");
        Ingredient creme1 = new Laitier("creme", new etatLiquide(40));
        Ingredient poulet1 = new Viande("poulet", new etatSolide(1));
        Ingredient carrotte1 = new Legume("carrotte", new etatSolide(5));

        Ingredient croquette1 = new Viande("croquette", new etatSolide(5));
        Ingredient frite1 = new Legume("frite", new etatSolide(10));
        Ingredient ketchup1 = new Epice("ketchup", new etatLiquide(20));

        Ingredient pomme1 = new Fruit("pomme", new etatSolide(200));

        ArrayList<Ingredient> ingredient1 = new ArrayList<>(Arrays.asList(creme1, poulet1, carrotte1));
        ArrayList<Ingredient> ingredient2 = new ArrayList<>(Arrays.asList(croquette1, frite1, ketchup1));
        ArrayList<Ingredient> ingredient3 = new ArrayList<>(Arrays.asList(pomme1));

        IngredientPlat soupeRecette = new IngredientPlat(ingredient1);
        IngredientPlat croquetteRecette = new IngredientPlat(ingredient2);
        IngredientPlat pommeRecette = new IngredientPlat(ingredient3);

        System.out.println(soupeRecette.toString() + "\n");

        System.out.println("--------Associer recette au plat--------");

        soupe.setRecette(soupeRecette);
        animalerie.setRecette(croquetteRecette);
        pommes.setRecette(pommeRecette);

        System.out.println(soupe.toString() + "\n");

        System.out.println("--------Ajout des plats au menu--------");

        menu.ajoute(soupe);
        menu.ajoute(animalerie);
        menu.ajoute(pommes);

        System.out.println(menu.toString() + "\n");

        System.out.println("--------Creation d'un client--------");

        Client Joe = new Client(69, "Sleepy Joe", "6536 4773 4848 4848");
        System.out.println(Joe.toString() + "\n");

        System.out.println("--------Naviger dans le menu--------");

        menu.positionSuivante();
        System.out.println(menu.toString() + "\n");


        System.out.println("--------Choisir plat--------");

        PlatChoisi manger = new PlatChoisi(animalerie, 1);
        System.out.println(manger.toString() + "\n");

        System.out.println("--------Cuisiner le plat--------");

        Facture facture = new Facture("Rotisserie Poulet");
        FactureView view = new FactureView();
        FactureController controller = new FactureController(facture, view);
        controller.associerClient(Joe);
        controller.Observer(chef);
        controller.ajoutPlat(manger);

        //chef.cuisiner(manger);

        System.out.println("Etat du plat" + manger.getEtat() + "\n\n");

        System.out.println("--------Creer la facture--------");


        System.out.println(controller.updateViewtoString() + "\n");

        System.out.println("--------Generer la facture--------");

        controller.payer();
        System.out.println(controller.updateViewGenererFacture() + "\n");

        System.out.println("------------FIN DE L ITERATION-------------");
        System.out.println("N'oublions pas que Domingo est le meilleur");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣶⣶⣦⠀⠀\n" +
                "⠀⠀⣠⣤⣤⣄⣀⣾⣿⠟⠛⠻⢿⣷⠀\n" +
                "⢰⣿⡿⠛⠙⠻⣿⣿⠁⠀⠀⠀⣶⢿⡇\n" +
                "⢿⣿⣇⠀⠀⠀⠈⠏⠀⠀⠀ Domingo\n" +
                "⠀⠻⣿⣷⣦⣤⣀⠀⠀⠀⠀⣾⡿⠃⠀\n" +
                "⠀⠀⠀⠀⠉⠉⠻⣿⣄⣴⣿⠟⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⡿⠟⠁⠀");





    }

}


//package menufact;
//
//import menufact.facture.Facture;
//import menufact.plats.PlatAuMenu;
//import menufact.plats.PlatChoisi;
//import menufact.plats.PlatSante;
//
//public class TestMenuFact01 {
//    public static void main(String[] args) {
//
//        try {
//            System.out.println("===menufact.plats.PlatAuMenu Constructeur 3 arguments");
//            PlatAuMenu p1 = new PlatAuMenu(0, "Frites sauce", 11.50);
//            System.out.println(p1);
//
//            System.out.println("===menufact.plats.PlatAuMenu Constructeur 3 arguments");
//            PlatAuMenu p2 = new PlatAuMenu(1, "Frites", 10.25);
//            System.out.println(p2);
//
//            System.out.println("===menufact.plats.PlatSante Constructeur 5 arguments");
//            PlatSante ps1 = new PlatSante(2, "Salade", 5.25, 100, 10, 1);
//            System.out.println(ps1);
//
//            System.out.println("===menufact.plats.PlatSante Constructeur 5 arguments");
//            PlatSante ps2 = new PlatSante(3, "Salade Cesar", 8.25, 100, 10, 1);
//            System.out.println(ps2);
//
//            System.out.println("===menufact.Menu ajout avec 4 plats");
//            Menu menu = new Menu("Menu1");
//            menu.ajoute(p1);
//            menu.ajoute(p2);
//            menu.ajoute(ps1);
//            menu.ajoute(ps2);
//            System.out.println(menu);
//
//            System.out.println("===menufact.Menu position 1, plat à la position 0");
//            menu.position(0);
//            System.out.println(menu.platCourant());
//
//            System.out.println("===menufact.Menu position 1, plat à la position suivante 1");
//            menu.positionSuivante();
//            System.out.println(menu.platCourant());
//
//            System.out.println("== Plat choisi");
//            PlatChoisi pch1 = new PlatChoisi(p1, 5);
//            System.out.println(pch1);
//
//            System.out.println("== New menufact.facture.Facture");
//            Facture facture = new Facture("Ma facture");
//            System.out.println(facture);
//
//            System.out.println("== Ajout d'un plat choisie à la facture");
//            facture.ajoutePlat(pch1);
//            System.out.println(facture);
//            System.out.println(facture.sousTotal());
//
//            System.out.println("== Ajout d'un plat choisie à la facture");
//            PlatChoisi pch2 = new PlatChoisi(p2, 10);
//            facture.ajoutePlat(pch2);
//            System.out.println(facture);
//            System.out.println(facture.sousTotal());
//
//            System.out.println(facture.total());
//            facture.ouvrir();
//            System.out.println(facture);
//            System.out.println("Etat = " + facture.getEtat());
//
//            facture.fermer();
//            System.out.println(facture);
//            System.out.println("Etat = " + facture.getEtat());
//
//            facture.ouvrir();
//            System.out.println(facture);
//            System.out.println("Etat = " + facture.getEtat());
//
//            facture.payer();
//            System.out.println(facture);
//            System.out.println("Etat = " + facture.getEtat());
//
//            facture.ouvrir();
//            System.out.println(facture);
//            System.out.println("Etat = " + facture.getEtat());
//        }catch (Exception fe)
//        {
//            System.out.println(fe.getMessage());
//        }
//
//    }
//}
