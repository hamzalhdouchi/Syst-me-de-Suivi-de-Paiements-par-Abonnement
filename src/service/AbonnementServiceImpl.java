package service;

import dao.AbonnementDAO;
import dbConnection.DataBase;
import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.enums.TypeAbonnement;
import util.DateVerfied;
import util.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;

public class AbonnementServiceImpl {

    private Connection connection = DataBase.getInstance().getConnection();

    public void createAbonnement(Abonnement abonnement,int dureeEngagementMois) {
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        if (abonnement.getId() == null) {
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
            Abonnement abonnement1 = new AbonnementSansEngagement(abonnement.getId(),abonnement.getNomService(), abonnement.getMontantMensuel(), date,dateFin, abonnement.getStatut(), abonnement.getType());
            abonnementDAO.create(abonnement1);
        } else if (abonnement.getType() == TypeAbonnement.AVEC_ENGAGEMENT && dureeEngagementMois > 3) {
            LocalDate DateFin = DateVerfied.dateFinAvecEng(abonnement.getDateFin(),dureeEngagementMois);
            Abonnement abonnement1 = new AbonnementAvecEngagement(abonnement.getId(),abonnement.getNomService(), abonnement.getMontantMensuel(), date,DateFin, abonnement.getStatut(), abonnement.getType(), dureeEngagementMois);
            abonnementDAO.create(abonnement1);
        }

    }

    public void findById(String id){
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        try {
            Abonnement abonnement = abonnementDAO.findById(id).orElse(null);
            if (abonnement == null) {
                System.out.println("Abonnement trouvé : " + abonnement.getNomService());
            } else {
                System.out.println("Abonnement non trouvé avec l'ID : " + id);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}
