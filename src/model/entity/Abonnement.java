package model.entity;

import model.enums.StatutPaiement;
import model.enums.TypeAbonnement;
import model.enums.statusabonnement;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public abstract class Abonnement {
    protected String id;
    protected String nomService;
    protected double montantMensuel;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected statusabonnement statutAbonnement;



    protected StatutPaiement statutPaiement;

    public statusabonnement getStatutAbonnement() {
        return statutAbonnement;
    }

    public void setStatutAbonnement(statusabonnement statutAbonnement) {
        this.statutAbonnement = statutAbonnement;
    }

    protected TypeAbonnement type;

    public Abonnement(String id,String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, statusabonnement statut, TypeAbonnement type) {
        this.id = UUID.randomUUID().toString();
        this.nomService = nomService;
        this.montantMensuel = montantMensuel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statutAbonnement = statut;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public TypeAbonnement getType() {
        return type;
    }

    public void setType(TypeAbonnement type) {
        this.type = type;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public statusabonnement getStatut() {
        return statutAbonnement;
    }

    public void setStatut(statusabonnement statut) {
        this.statutAbonnement = statut;
    }


}
