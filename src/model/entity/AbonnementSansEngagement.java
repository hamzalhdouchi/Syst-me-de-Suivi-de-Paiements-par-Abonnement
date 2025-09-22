package model.entity;

import model.enums.statusabonnement;

import java.time.LocalDate;

public class AbonnementSansEngagement extends Abonnement {
    public AbonnementSansEngagement(String id,String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, statusabonnement statut) {
        super(id,nomService, montantMensuel, dateDebut, dateFin, statut);
    }
}
