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

    public static void main(String[] args){
        System.out.println("----DEBUT DES TESTS DE L ITERATION----\n");
        System.out.println("--Creation d'un chef--");
        Chef chef = Chef.getInstance("gusteau");
        System.out.println(chef.toString() + "\n");
        System.out.println("--Creation de l'inventaire--");






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
