package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private final static Scanner scanner = new Scanner(System.in);

    private final  MenuPaiements menuPaiements ;
    private final  MenuAbonnements menuAbonnements;

    public MainMenu(MenuPaiements menuPaiements , MenuAbonnements menuAbonnements) {
        this.menuAbonnements = menuAbonnements;
        this.menuPaiements = menuPaiements;
    }
    public void mainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Gestion des abonnements");
            System.out.println("2. Gestion des paiements");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choix;
            try {
                Scanner sc = new Scanner(System.in);
                choix = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                continue;
            }catch (NullPointerException e){
                System.out.println("Erreur inattendue. Veuillez réessayer.");
                continue;
            }

            switch (choix) {
                case 1:
                    menuAbonnements.menuAbonnement();
                    break;
                case 2:
                    menuPaiements.menuPaiements();
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