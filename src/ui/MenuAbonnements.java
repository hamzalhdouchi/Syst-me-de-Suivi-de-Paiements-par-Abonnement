package ui;

import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.enums.TypeAbonnement;
import model.enums.statusabonnement;
import service.AbonnementServiceImpl;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuAbonnements {

    private final static Scanner sc = new Scanner(System.in);
    private final  AbonnementServiceImpl abonnementService ;

    public MenuAbonnements(AbonnementServiceImpl abonnementService) {
        this.abonnementService = abonnementService;
    }
    public void menuAbonnement() {


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
                    CreerAbonnements();
                    break;
                case 2:
                    updateAbon();
                     break;
                case 3:
                    deleteAbonnement();
                     break;
                case 4:
                    listerAbonnements();
                    break;
                case 5:
                    CanselSubscription();
                    break;
                case 0: back = true; break;
                default: System.out.println("Choix invalide.");
            }
        }
    }
    public void CreerAbonnements() {
        System.out.println("Création d'un abonnement...");
        System.out.println("1 - Avec Engagement");
        System.out.println("2 - Sans Engagement");
        int choix;
        try {
            Scanner sc = new Scanner(System.in);
            choix = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            return;
        }catch (NullPointerException e){
            System.out.println("Erreur inattendue. Veuillez réessayer.");
            return;
        }

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
                TypeAbonnement type = TypeAbonnement.AVEC_ENGAGEMENT;
                statusabonnement status = statusabonnement.ACTIVE;
                Abonnement ab = new AbonnementAvecEngagement(null, name, montantMensuel, dateDebut, null, status, dureeEngagement, type);
                abonnementService.createAbonnement(ab, dureeEngagement);
                break;

            case 2:
                System.out.println("Veuillez entre le name du service:");
                String names = sc.next();
                System.out.println("Veuillez entre le montant Mensuel :");
                double montantMensuels = sc.nextDouble();
                sc.nextLine();
                System.out.println("entre la date de début (YYYY-MM-DD) :");
                String dateDebutStrs = sc.nextLine();
                LocalDate dateDebuts = LocalDate.parse(dateDebutStrs);
                statusabonnement statutAV = statusabonnement.ACTIVE;
                TypeAbonnement typeAV = TypeAbonnement.SANS_ENGAGEMENT;

                Abonnement abs = new AbonnementSansEngagement(null, names, montantMensuels, dateDebuts, null, statutAV, typeAV);
                abonnementService.createAbonnement(abs, 0);
                break;

            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }


    }

    public  void listerAbonnements() {
        System.out.println("Liste des abonnements :");
        List<Abonnement> abonnements = abonnementService.findAll();

        for (Abonnement abonnement : abonnements) {
            System.out.println("ID: " + abonnement.getId());
            System.out.println("Nom du service: " + abonnement.getNomService());
            System.out.println("Montant mensuel: " + abonnement.getMontantMensuel());
            System.out.println("Date de début: " + abonnement.getDateDebut());
            System.out.println("Date de fin: " + abonnement.getDateFin());
            System.out.println("Statut: " + abonnement.getStatutAbonnement());
            System.out.println("---------------------------");
        };
    }


    public void deleteAbonnement() {
        System.out.println("Veuillez entrer l'ID de l'abonnement à supprimer :");
        String id = sc.next();
        abonnementService.deleteById(id);
    }

    public void updateAbon() {
        System.out.println("------------------UPDATE ABONNEMENT------------------");
        System.out.println("Veuillez entrer l'ID de l'abonnement à modifier :");
        String id;
        Abonnement a = null;
        try {
            id = sc.next();
            a = abonnementService.findById(id);
            if (a == null) {
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre. " + e.getMessage());
        }
        System.out.println("Veuillez entre le name du service:");
        String name = sc.next();
        System.out.println("Veuillez entre le montant Mensuel :");
        double montantMensuel = sc.nextDouble();
        sc.nextLine();
        System.out.println("Entre le statut (ACTIVE, SUSPENDU, RESILIE) :");
        String statutStr = sc.next();
        statusabonnement statut = statusabonnement.valueOf(statutStr.toUpperCase());

        System.out.println("Voici les informations actuelles de l'abonnement :");
        System.out.println("Type de abonnement      :    "+a.getType());
        System.out.println("Statut de abonnement    :    "+a.getStatut());
        System.out.println("Date de Debut           :    "+a.getDateDebut());
        System.out.println("Date de Fin             :    "+a.getDateFin());
        System.out.println("Montant de abonnement   :    "+a.getMontantMensuel());
        System.out.println("Nome de service         :    "+a.getNomService());
        System.out.println("ID de abonnement        :    "+a.getId());
        if (a.getType().equals(TypeAbonnement.AVEC_ENGAGEMENT)) {
            System.out.println("entre la durée d'engagement (min 3mois) :");
            int dureeEngagement = sc.nextInt();
            Abonnement abonnement = new AbonnementAvecEngagement(a.getId(), name, montantMensuel, a.getDateDebut(), a.getDateFin(), statut, dureeEngagement, TypeAbonnement.AVEC_ENGAGEMENT);
            abonnementService.updateAbonnement(abonnement, dureeEngagement);
        }
        Abonnement abonnement = new AbonnementSansEngagement(a.getId(), name, montantMensuel, a.getDateDebut(), a.getDateFin(), statut, TypeAbonnement.SANS_ENGAGEMENT);
        abonnementService.updateAbonnement(abonnement, 0);
    }

    public void CanselSubscription() {
        System.out.println("Veuillez entrer l'ID de l'abonnement à annuler :");
        String id = sc.next();
        abonnementService.cancelSubscription(id);

    }
}
