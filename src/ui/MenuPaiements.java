package ui;

import java.util.Scanner;

public class menuPaiements {

    public static void menuPaiements() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION DES PAIEMENTS ---");
            System.out.println("1. Enregistrer un paiement");
            System.out.println("2. Modifier un paiement");
            System.out.println("3. Supprimer un paiement");
            System.out.println("4. Afficher les paiements d’un abonnement");
            System.out.println("5. Consulter les paiements manqués et montant total impayé");
            System.out.println("6. Afficher la somme payée d’un abonnement");
            System.out.println("7. Afficher les 5 derniers paiements");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            Scanner scanner = new Scanner(System.in);
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1: System.out.println(" Enregistrement d'un paiement..."); break;
                case 2: System.out.println("Modification d'un paiement..."); break;
                case 3: System.out.println("Suppression d'un paiement..."); break;
                case 4: System.out.println("Afficher les paiements d’un abonnement..."); break;
                case 5: System.out.println("Paiements manqués & total impayé..."); break;
                case 6: System.out.println("Somme payée d’un abonnement..."); break;
                case 7: System.out.println("Les 5 derniers paiements..."); break;
                case 0: back = true; break;
                default: System.out.println("⚠Choix invalide.");
            }
        }
    }
}
