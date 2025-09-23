package dao;

import dao.interfaceDAO.AbonnementInterface;
import dbConnection.DataBase;
import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.enums.StatutPaiement;
import model.enums.TypeAbonnement;
import model.enums.statusabonnement;
import util.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AbonnementDAO implements AbonnementInterface {

    private Connection con;
    public AbonnementDAO() {
        this.con = DataBase.getInstance().getConnection();
    }
    public void create(Abonnement a){

        a.setId( UUID.randomUUID().toString());
        String sql = "INSERT INTO Abonnement (nomService, montantMensuel, dateDebut, dateFin, statut,dureeEngagementMois,typeEngagement,statutPaiement) VALUES (?, ?, ?, ?, ?,?,?,?)";
        try{
            PreparedStatement pstmt = this.con.prepareStatement(sql);
             pstmt.setString(1, a.getNomService());
             pstmt.setDouble(2, a.getMontantMensuel());
             pstmt.setDate(3, Date.valueOf(a.getDateDebut()));
             pstmt.setDate(4, Date.valueOf(a.getDateFin()));
             pstmt.setString(5, a.getStatut().toString());
                if (a instanceof AbonnementAvecEngagement){
                    AbonnementAvecEngagement abonnementAvecEngagement = (AbonnementAvecEngagement) a;
                    pstmt.setInt(6, abonnementAvecEngagement.getDureeEngagementMois());
                    pstmt.setString(7, "AVEC_ENGAGEMENT");
                } else {
                    pstmt.setString(7, "SANS_ENGAGEMENT");
                }
                pstmt.setString(8, a.getStatutPaiement().toString());
             pstmt.executeUpdate();

            System.out.println("-----------------Abonnement créé avec succès.-------------------");
        }catch(SQLException e){
            Logger.error(e.getMessage());
        }
    };
    public Optional<Abonnement> findById(String id) {
        try {
            List<Abonnement> abonnements = findAll();

            Abonnement abonnement = abonnements.stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() ->{
                       Exception error =   new Exception("Abonnement non trouvé");
                        Logger.error(error.getMessage());
                        return error;
                    });
            return Optional.of(abonnement);

        } catch (Exception e) {
            Logger.error(e.getMessage());
            return Optional.empty();
        }
    }
    public List<Abonnement> findAll() {
        List<Abonnement> abonnements = new ArrayList<>();
        String SQL = "SELECT * FROM Abonnement";

        try {
            PreparedStatement stmt = this.con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String nomService = rs.getString("nomService");
                double montantMensuel = rs.getDouble("montantMensuel");
                LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
                LocalDate dateFin = rs.getDate("dateFin").toLocalDate();
                String status = rs.getString("statutAbonnement");
                int dureeEngagement = rs.getInt("dureeEngagementMois");
                TypeAbonnement type = TypeAbonnement.valueOf(rs.getString("typeEngagement"));
                statusabonnement statut = statusabonnement.valueOf(status);
                StatutPaiement statutPaiement = StatutPaiement.valueOf(rs.getString("statutPaiement"));
                if (dureeEngagement == 0) {
                    AbonnementSansEngagement abonnement = new AbonnementSansEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, type,statutPaiement);
                    abonnements.add(abonnement);
                } else {
                    AbonnementAvecEngagement abonnement = new AbonnementAvecEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, dureeEngagement, type, statutPaiement);
                    abonnements.add(abonnement);
                }
            }
            System.out.println("-----------------Abonnements récupérés avec succès.-------------------");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return new ArrayList<>();
        }

        return abonnements;
    }

    public void update(Abonnement a, int dureeEngagementMois){
        if (a instanceof AbonnementAvecEngagement){
            String  sql = "UPDATE abonnement (nomService ,statutAbonnement, dureeEngagementMois,statutPaiement) VALUE (?,?,?,?) where id = ?";
        }
        String sql = "UPDATE abonnement (nomService ,statut) VALUE (?,?) where id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, a.getNomService());
            stmt.setString(2, a.getStatut().toString());
            stmt.setString(3, a.getId());
            if(a instanceof AbonnementAvecEngagement){
                stmt.setInt(4, dureeEngagementMois);
            }
            stmt.setString(5, a.getStatutPaiement().toString());
            stmt.executeUpdate();
            System.out.println("-----------------Abonnement mis à jour avec succès.-------------------");
        }catch (Exception e){
            Logger.error(e.getMessage());
        }

    };
    public void delete(String idAbonnement){
        String sql = "DELETE FROM abonnement where id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, idAbonnement);
            stmt.executeUpdate();
            System.out.println("-----------------Abonnement supprimé avec succès.-------------------");
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    };
    public List<Abonnement> findActiveSubscriptions(){
        List<Abonnement> abonnements = new ArrayList<>();
        abonnements = findAll();
        List<Abonnement> newAbonnements = new ArrayList<>();
        newAbonnements =  abonnements.stream().filter(a -> a.getStatut() == statusabonnement.ACTIVE).collect(Collectors.toList());
        return newAbonnements;
    };
}
