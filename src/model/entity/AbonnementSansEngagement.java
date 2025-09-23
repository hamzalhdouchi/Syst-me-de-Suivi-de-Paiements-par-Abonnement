package model.entity;

import model.enums.TypeAbonnement;
import model.enums.statusabonnement;

import java.time.LocalDate;

public class AbonnementSansEngagement extends Abonnement {
    public AbonnementSansEngagement(String id,String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, statusabonnement statut , TypeAbonnement type) {
        super(id,nomService, montantMensuel, dateDebut, dateFin, statut, type);
    }
}
