package model.entity;

import model.enums.StatutPaiement;
import model.enums.TypeAbonnement;
import model.enums.statusabonnement;

import java.time.LocalDate;

public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois = 0;

    public AbonnementAvecEngagement(String id, String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, statusabonnement statut , int dureeEngagement , TypeAbonnement type ,StatutPaiement statutPaiement) {
        super( id,nomService, montantMensuel, dateDebut, dateFin, statut, type, statutPaiement);
        this.dureeEngagementMois = dureeEngagement;
    }
    public int getDureeEngagementMois() {
        return dureeEngagementMois;
    }

    public void setDureeEngagementMois(int dureeEngagementMois) {
        this.dureeEngagementMois = dureeEngagementMois;
    }
}
