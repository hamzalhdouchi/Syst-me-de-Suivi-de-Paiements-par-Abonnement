package ui;

import java.util.Scanner;

public class menuAbonnements {
    public static void menuAbonnement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION DES ABONNEMENTS ---");
            System.out.println("1. Créer un abonnement");
            System.out.println("2. Modifier un abonnement");
            System.out.println("3. Supprimer un abonnement");
            System.out.println("4. Consulter la liste des abonnements");
            System.out.println("5. Résilier un abonnement");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            switch (choix) {
                case 1: System.out.println("👉 Création d'abonnement..."); break;
                case 2: System.out.println("👉 Modification d'abonnement..."); break;
                case 3: System.out.println("👉 Suppression d'abonnement..."); break;
                case 4: System.out.println("👉 Liste des abonnements..."); break;
                case 5: System.out.println("👉 Résiliation d'abonnement..."); break;
                case 0: back = true; break;
                default: System.out.println("⚠️ Choix invalide.");
            }
        }
    }
}
