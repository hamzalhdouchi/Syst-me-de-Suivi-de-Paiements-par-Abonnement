package ui;

import java.util.Scanner;

import static ui.menuAbonnements.menuAbonnement;
import static ui.menuPaiements.menuPaiements;
import static ui.menuRapports.menuRapports;

public class Main {

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Gestion des abonnements");
            System.out.println("2. Gestion des paiements");
            System.out.println("3. Rapports financiers");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    menuAbonnement();
                    break;
                case 2:
                    menuPaiements();
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