package model.entity;

import model.enums.StatutPaiement;

import java.time.LocalDate;
import java.util.UUID;

public class Paiement {
    private String idPaiement;
    private String idAbonnement;
    private LocalDate dateEcheance;
    private LocalDate datePaiement;
    private StatutPaiement statut;
    private String TypePaiement;



    private double montent;

    public Paiement(String idAbonnement,String idPaiement, LocalDate dateEcheance, LocalDate datePaiement,StatutPaiement statut, String TypePaiement,double montent) {
        this.idPaiement = idPaiement;
        this.idAbonnement = idAbonnement;
        this.dateEcheance = dateEcheance;
        this.datePaiement = datePaiement;
        this.statut = statut;
        this.TypePaiement = TypePaiement;
        this.montent = montent;
    }

    public String getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(String idPaiement) {
        this.idPaiement = idPaiement;
    }

    public String getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(String idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public String getTypePaiement() {
        return TypePaiement;
    }
    public double getMontent() {
        return montent;
    }



    public void setMontent(double montent) {
        this.montent = montent;
    }
    public void setTypePaiement(String typePaiement) {
        TypePaiement = typePaiement;
    }
    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public StatutPaiement getStatut() {
        return statut;
    }

    public void setStatut(StatutPaiement statut) {
        this.statut = statut;
    }



}
