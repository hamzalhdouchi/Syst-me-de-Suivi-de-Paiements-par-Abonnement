package ui;

import model.entity.Paiement;
import model.enums.StatutPaiement;
import service.PaiementServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPaiements {
    private final PaiementServiceImpl paiementService;
    private static Scanner sc = new Scanner(System.in);

    public MenuPaiements(PaiementServiceImpl paiementService) {
        this.paiementService = paiementService;

    }

    public void menuPaiements() {
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
            Scanner sc = new Scanner(System.in);
            int choix;
            try {
                choix = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Entrée invalide. Veuillez entrer un nombre. " + e.getMessage());
                continue;
            }catch (NullPointerException e){
                System.out.println("Erreur inattendue. Veuillez réessayer. " + e.getMessage());
                continue;
            }
            switch (choix) {
                case 1:
                    enrgestrerPaiement();
                    break;
                case 2:
                    updatePaiement();
                    break;
                case 3:
                    deletePaiement();
                    break;
                case 4:
                    AfficherPaiementsAbonnement();
                    break;
                case 5:
                    PaiementManquésEtMontant();
                    break;
                case 6:
                    SomePaiement();
                    break;
                case 7:
                    last5Paiements();
                break;
                case 0: back = true; break;
                default: System.out.println("⚠Choix invalide.");
            }
        }
    }

    public  void enrgestrerPaiement() {
        System.out.println("\n------------------ENREGISTRER PAIEMENT------------------");
        System.out.println("Veuillez entrer l'ID de l'abonnement pour lequel vous souhaitez enregistrer un paiement :");
        String id = sc.next();
        System.out.println("Entre type de paiement (CREDIT_CARD, PAYPAL, BANK_TRANSFER, ect..) :");
        String typePaiement = sc.next();
        System.out.println("confirmation de l'enregistrement du paiement...");
        Paiement paiement = new Paiement(id,null,null,null,null,typePaiement,0);
        paiementService.CreerPaiement(paiement);
    }

    public void  updatePaiement(){
        System.out.println("\n------------------UPDATE PAIEMENT------------------");
        System.out.println("Veuillez entrer l'ID du paiement à modifier :");
        String id = sc.nextLine();

        System.out.println("Entre le statut (PAYE, NON_PAYE, EN_RETARD) :");
        String statutStr = sc.nextLine();
        StatutPaiement status = StatutPaiement.valueOf(statutStr.toUpperCase());
        Paiement p = new Paiement(null,id,null,null,status,null,0);
        paiementService.update(p);
    }

    public void deletePaiement(){
        System.out.println("\n------------------DELETE PAIEMENT------------------");
        System.out.println("Entre l'ID de Paiement");
        String id = sc.nextLine();
        paiementService.delete(id);
    }

    public void AfficherPaiementsAbonnement() {
        System.out.println("\n------------------Afficher les Paiements d'un Abonnement ------------------");
        System.out.println("Entre le Status des Paiemnt tu veux trover");
        System.out.println("1 : PAYE");
        System.out.println("2 : NON PAYE");
        System.out.println("3 : EN_RETARD");
        System.out.println("4 : TOUT");
        int choixStatus;
        try {
            Scanner sc = new Scanner(System.in);
            choixStatus = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Entrée invalide. Veuillez entrer un nombre. " +e.getMessage() );
            return;
        }catch (NullPointerException e){
            System.out.println("Erreur inattendue. Veuillez réessayer." + e.getMessage());
            return;
        }
        String statusStr = "";
        switch (choixStatus) {
            case 1:
                statusStr = "PAIEMENT";
                break;

            case 2:
                statusStr = "NON_PAIEMENT";
                break;
            case 3:
                statusStr = "EN_RETARD";
                break;
            case 4:
                statusStr = "TOUS";
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
        System.out.println("Entre l'ID de l'abonnement");
        String id = sc.nextLine();
        sc.nextLine();
        List<Paiement> P=  paiementService.findByAbonnement(id, statusStr);
        for(Paiement paiement : P){
            System.out.println("------------------------------------------------------------------");
            System.out.println("ID: " + paiement.getIdPaiement());
            System.out.println("Montant: " + paiement.getMontent());
            System.out.println("Type De Paiement: " + paiement.getTypePaiement());
            System.out.println("Date De Paiement: " + paiement.getDatePaiement());
            System.out.println("Date DE Echeance: " + paiement.getDateEcheance());
            System.out.println("Type de Paiement: " + paiement.getTypePaiement());
            System.out.println("Statut: " + paiement.getStatut());
            System.out.println("-------------------------------------------------------------------");

        }

    }

    public void SomePaiement(){
        System.out.println("--------------Some Paiements------------------");
        System.out.println("Entrez l'ID du Abonnement :");
        String idAbonnement = sc.nextLine();
        double somme = paiementService.sommePaiement(idAbonnement);
        System.out.println("La somme totale payée pour l'abonnement " + idAbonnement + " est : " + somme);
    }

    public void last5Paiements(){
        System.out.println("--------------Les 5 derniers Paiements------------------");
        List<Paiement> paiements = paiementService.last5Paiements();
        for(Paiement paiement : paiements){
            System.out.println("------------------------------------------------------------------");
            System.out.println("ID: " + paiement.getIdPaiement());
            System.out.println("Montant: " + paiement.getMontent());
            System.out.println("Type De Paiement: " + paiement.getTypePaiement());
            System.out.println("Date De Paiement: " + paiement.getDatePaiement());
            System.out.println("Date DE Echeance: " + paiement.getDateEcheance());
            System.out.println("Type de Paiement: " + paiement.getTypePaiement());
            System.out.println("Statut: " + paiement.getStatut());
            System.out.println("-------------------------------------------------------------------");

        }
    }

    public void PaiementManquésEtMontant(){
        System.out.println("--------------Paiements Manqués & Montant------------------");
        List<Paiement> paiements =   paiementService.PaiementsNonPaye();

        for(Paiement paiement : paiements){
            System.out.println("------------------------------------------------------------------");
            System.out.println("ID: " + paiement.getIdPaiement());
            System.out.println("Montant: " + paiement.getMontent());
            System.out.println("Type De Paiement: " + paiement.getTypePaiement());
            System.out.println("Date De Paiement: " + paiement.getDatePaiement());
            System.out.println("Date DE Echeance: " + paiement.getDateEcheance());
            System.out.println("Type de Paiement: " + paiement.getTypePaiement());
            System.out.println("Statut: " + paiement.getStatut());
            System.out.println("-------------------------------------------------------------------\n");
        }
        double somme = paiementService.SommeImpyeAbonnement();
        System.out.println("La somme totale impayée est : " + somme);
        System.out.println("\n-------------------------------------------------------------------");
    }
}
