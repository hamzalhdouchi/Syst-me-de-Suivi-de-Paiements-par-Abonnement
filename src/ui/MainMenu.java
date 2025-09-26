package ui;

import java.util.Scanner;

import service.PaiementServiceImpl;
import ui.MenuAbonnements

public class Main {

    private final static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean exit = false;
        
        MenuAbonnements menuAbonnements = new MenuAbonnements();
        MenuPaiements menuPaiements = new MenuPaiements();

        while (!exit) {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Gestion des abonnements");
            System.out.println("2. Gestion des paiements");
            System.out.println("3. Rapports financiers");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    MenuAbonnements.menuAbonnements();
                    break;
                case 2:
                    MenuPaiements();
                    break;
                case 3:
                    menuRapports();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        }
    }
}