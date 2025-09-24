package ui;

import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.entity.Paiement;
import model.enums.StatutPaiement;
import model.enums.TypeAbonnement;
import model.enums.statusabonnement;
import service.AbonnementServiceImpl;
import service.PaiementServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class functions {
    private static Scanner sc = new Scanner(System.in);
    private static AbonnementServiceImpl abonnementService = new AbonnementServiceImpl();
    private static PaiementServiceImpl paiementService = new PaiementServiceImpl();

    public static void CreerAbonnements() {
        System.out.println("Création d'un abonnement...");
        System.out.println("1 - Avec Engagement");
        System.out.println("2 - Sans Engagement");
        int choix = sc.nextInt();

        switch (choix) {
            case 1:
                System.out.println("Veuillez entre le name du service:");
                String name = sc.next();
                System.out.println("Veuillez entre le montant Mensuel :");
                double montantMensuel = sc.nextDouble();
                System.out.println("Veuillez entre la durée d'engagement (en mois) :");
                int dureeEngagement = sc.nextInt();
                System.out.println("entre la date de début (YYYY-MM-DD) :");
                String dateDebutStr = sc.next();
                LocalDate dateDebut = LocalDate.parse(dateDebutStr);

                Abonnement ab = new AbonnementAvecEngagement(null, name, montantMensuel, dateDebut, null, null, dureeEngagement, null);
                abonnementService.createAbonnement(ab, dureeEngagement);
                break;

            case 2:
                System.out.println("Veuillez entre le name du service:");
                String names = sc.next();
                System.out.println("Veuillez entre le montant Mensuel :");
                double montantMensuels = sc.nextDouble();
                System.out.println("entre la date de début (YYYY-MM-DD) :");
                String dateDebutStrs = sc.next();
                LocalDate dateDebuts = LocalDate.parse(dateDebutStrs);
                statusabonnement statut = statusabonnement.ACTIVE;
                TypeAbonnement type = TypeAbonnement.SANS_ENGAGEMENT;

                Abonnement abs = new AbonnementSansEngagement(null, names, montantMensuels, null, null, statut, type);
                abonnementService.createAbonnement(abs, 0);
                break;

            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }


    }

    public static void listerAbonnements() {
        System.out.println("Liste des abonnements :");
        abonnementService.findAll().forEach(abonnement -> {
            System.out.println("ID: " + abonnement.getId());
            System.out.println("Nom du service: " + abonnement.getNomService());
            System.out.println("Montant mensuel: " + abonnement.getMontantMensuel());
            System.out.println("Date de début: " + abonnement.getDateDebut());
            System.out.println("Date de fin: " + abonnement.getDateFin());
            System.out.println("Statut: " + abonnement.getStatutAbonnement());
            if (abonnement instanceof AbonnementAvecEngagement) {
                AbonnementAvecEngagement abAvecEngagement = (AbonnementAvecEngagement) abonnement;
                System.out.println("Durée d'engagement (mois): " + abAvecEngagement.getDureeEngagementMois());
            }
            System.out.println("---------------------------");
        });
    }

    public static void deleteAbonnement() {
        System.out.println("Veuillez entrer l'ID de l'abonnement à supprimer :");
        String id = sc.next();
        abonnementService.deleteById(id);
    }

    public static void updateAbon() {
        System.out.println("------------------UPDATE ABONNEMENT------------------");
        System.out.println("Veuillez entrer l'ID de l'abonnement à modifier :");
        String id = sc.next();

        System.out.println("Veuillez entre le name du service:");
        String name = sc.next();
        System.out.println("Veuillez entre le montant Mensuel :");
        double montantMensuel = sc.nextDouble();
        System.out.println("Entre le statut (ACTIVE, INACTIVE, SUSPENDED) :");
        String statutStr = sc.next();
        statusabonnement statut = statusabonnement.valueOf(statutStr.toUpperCase());

        Abonnement a = abonnementService.findById(id);
        if (a.getType().equals(TypeAbonnement.AVEC_ENGAGEMENT)) {
            System.out.println("entre la durée d'engagement (en mois) :");
            int dureeEngagement = sc.nextInt();
            a = new AbonnementAvecEngagement(id, name, montantMensuel, a.getDateDebut(), a.getDateFin(), statut, dureeEngagement, TypeAbonnement.AVEC_ENGAGEMENT);
            abonnementService.updateAbonnement(a, dureeEngagement);
        }
        a = new AbonnementSansEngagement(id, name, montantMensuel, a.getDateDebut(), a.getDateFin(), statut, TypeAbonnement.SANS_ENGAGEMENT);
        abonnementService.updateAbonnement(a, 0);
    }

    public void CanselSubscription() {
        System.out.println("Veuillez entrer l'ID de l'abonnement à annuler :");
        String id = sc.next();
        abonnementService.cancelSubscription(id);

    }

    public static void enrgestrerPaiement() {
        System.out.println("\n------------------ENREGISTRER PAIEMENT------------------");
        System.out.println("Veuillez entrer l'ID de l'abonnement pour lequel vous souhaitez enregistrer un paiement :");
        String id = sc.next();
        System.out.println("Veuillez entrer le montant du paiement :");
        double montant = sc.nextDouble();
        System.out.println("Entre type de paiement (CREDIT_CARD, PAYPAL, BANK_TRANSFER, ect..) :");
        String typePaiement = sc.next();
        Paiement paiement = new Paiement(id,null,null,null,null,typePaiement,montant);
        paiementService.CreerPaiement(paiement);
    }

    public static void  updatePaiement(){
        System.out.println("\n------------------UPDATE PAIEMENT------------------");
        System.out.println("Veuillez entrer l'ID du paiement à modifier :");
        String id = sc.nextLine();
        System.out.println("Veuillez entrer le montant du paiement :");
        double montant = sc.nextDouble();
        System.out.println("Entre type de paiement (CREDIT_CARD, PAYPAL, BANK_TRANSFER, ect..) :");
        String typePaiement = sc.nextLine();
        System.out.println("Entre le statut (PAYE, NON_PAYE, EN_RETARD) :");
        String statutStr = sc.nextLine();
        StatutPaiement status = StatutPaiement.valueOf(statutStr.toUpperCase());
        Paiement p = new Paiement(null,id,null,null,status,typePaiement,montant);
        paiementService.update(p);
    }

    public static void deletePaiement(){
        System.out.println("\n------------------DELETE PAIEMENT------------------");
        System.out.println("Entre l'ID de Paiement");
        String id = sc.nextLine();
        paiementService.delete(id);
    }

    public static void AfficherPaiementsAbonnement() throws Exception {
        System.out.println("\n------------------Afficher les Paiements d'un Abonnement ------------------");
        System.out.println("Entre le Status des Paiemnt tu veux trover");
        System.out.println("1 : PAYE");
        System.out.println("2 : NON PAYE");
        System.out.println("3 : EN_RETARD");
        System.out.println("4 : TOUT");
        int choixStatus = sc.nextInt();
        sc.nextLine();
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
        List<Paiement>  P=  paiementService.findByAbonnement(id, statusStr);
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
}
