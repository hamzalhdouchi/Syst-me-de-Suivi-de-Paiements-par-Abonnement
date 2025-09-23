package ui;

import java.util.Scanner;

public class menuRapports {

    public static void menuRapports() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- RAPPORTS FINANCIERS ---");
            System.out.println("1. Rapport mensuel");
            System.out.println("2. Rapport annuel");
            System.out.println("3. Rapport des impayés");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1: System.out.println(" Génération du rapport mensuel..."); break;
                case 2: System.out.println(" Génération du rapport annuel..."); break;
                case 3: System.out.println("Rapport des impayés..."); break;
                case 0: back = true; break;
                default: System.out.println("⚠ Choix invalide.");
            }
        }
    }
}
