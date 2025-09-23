package model.entity;

import model.enums.TypeAbonnement;
import model.enums.statusabonnement;

import java.time.LocalDate;

public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois = 0;

    public AbonnementAvecEngagement(String id, String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, statusabonnement statut , TypeAbonnement type, int dureeEngagement) {
        super( id,nomService, montantMensuel, dateDebut, dateFin, statut, type);
        this.dureeEngagementMois = dureeEngagement;
    }

    public int getDureeEngagementMois() {
        return dureeEngagementMois;
    }

    public void setDureeEngagementMois(int dureeEngagementMois) {
        this.dureeEngagementMois = dureeEngagementMois;
    }
}
