package service;

import dao.AbonnementDAO;
import dbConnection.DataBase;
import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.enums.TypeAbonnement;
import util.DateVerfied;
import util.Logger;
import util.UuidGen;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AbonnementServiceImpl {


    public  void createAbonnement(Abonnement abonnement,int dureeEngagementMois) {
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        String id = UuidGen.codeGen();
        if (id == null) {
            System.out.println("Abonnement ID is null, cannot create abonnement.");
            return;
        }
        if (abonnement.getMontantMensuel() <= 0) {
            System.out.println("Montant mensuel must be greater than zero.");
            return;
        }
        LocalDate date =  DateVerfied.DateNowVirfied(abonnement.getDateDebut());

        if (abonnement.getType() == TypeAbonnement.SANS_ENGAGEMENT){
            LocalDate dateFin = DateVerfied.datefinSansEng(abonnement.getDateFin());
            Abonnement abonnement1 = new AbonnementSansEngagement(id,abonnement.getNomService(), abonnement.getMontantMensuel(), date,dateFin, abonnement.getStatut(), abonnement.getType());
            abonnementDAO.create(abonnement1);
        } else if (abonnement.getType() == TypeAbonnement.AVEC_ENGAGEMENT && dureeEngagementMois > 3) {
            LocalDate DateFin = DateVerfied.dateFinAvecEng(abonnement.getDateFin(),dureeEngagementMois);
            Abonnement abonnement1 = new AbonnementAvecEngagement(id,abonnement.getNomService(), abonnement.getMontantMensuel(), date,DateFin, abonnement.getStatut(),  dureeEngagementMois,abonnement.getType());
            abonnementDAO.create(abonnement1);
        }

    }

    public Abonnement findById(String id){
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        try {
            Abonnement abonnement = abonnementDAO.findById(id).orElse(null);
            if (abonnement == null) {
                System.out.println("Abonnement trouvé : " + abonnement.getNomService());
            } else {
                System.out.println("Abonnement non trouvé avec l'ID : " + id);
            }
            return abonnement;
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return null;
    }

    public List<Abonnement> findAll(){
        AbonnementDAO abonnementDAO = new AbonnementDAO();
            List<Abonnement> abonnements = abonnementDAO.findAll();
            return abonnements;
    };

    public void deleteById(String id){
        AbonnementDAO abonnementDAO = new AbonnementDAO();
         abonnementDAO.delete(id);
    };

    public void updateAbonnement(Abonnement abonnement, int dureeEngagementMois){
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        if (abonnement.getMontantMensuel() <= 0) {
            System.out.println("Montant mensuel must be greater than zero.");
            return;
        }
        if (abonnement.getType() == TypeAbonnement.SANS_ENGAGEMENT){
            Abonnement abonnement1 = new AbonnementSansEngagement(abonnement.getId(),abonnement.getNomService(), abonnement.getMontantMensuel(), null ,null, abonnement.getStatut(), abonnement.getType());
            abonnementDAO.update(abonnement1,0);
        } else if (abonnement.getType() == TypeAbonnement.AVEC_ENGAGEMENT && dureeEngagementMois > 3) {
            Abonnement abonnement1 = new AbonnementAvecEngagement(abonnement.getId(),abonnement.getNomService(), abonnement.getMontantMensuel(), null,null, abonnement.getStatut(), dureeEngagementMois, abonnement.getType());
            abonnementDAO.update(abonnement1,dureeEngagementMois);
        }
    };

    public List<Abonnement> findActiveSubscriptions(){
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        return abonnementDAO.findActiveSubscriptions();
    }

    public void cancelSubscription(String id){
        if (id == null) {
            System.out.println("Abonnement ID is null, cannot cancel abonnement.");
            return;
        }
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        abonnementDAO.canseldAbonnement(id);
    }
}
