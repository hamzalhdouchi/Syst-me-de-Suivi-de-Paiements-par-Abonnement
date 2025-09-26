package dao;

import dao.interfaceDAO.AbonnementInterface;
import dbConnection.DataBase;
import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.enums.StatutPaiement;
import model.enums.TypeAbonnement;
import model.enums.statusabonnement;
import util.DateVerfied;
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
    public AbonnementDAO(Connection con) {
        this.con = con;
    }
    public void create(Abonnement a){
        String sql = "INSERT INTO Abonnement (id ,nomService, montantMensuel, dateDebut, dateFin, statutAbonnement,dureeEngagementMois,typeAbonnement) VALUES (?, ?, ?, ?, ?,?,?, ?)";
        try{
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            pstmt.setString(1, a.getId());
             pstmt.setString(2, a.getNomService());
             pstmt.setDouble(3, a.getMontantMensuel());
             pstmt.setDate(4, Date.valueOf(a.getDateDebut()));
             pstmt.setDate(5, Date.valueOf(a.getDateFin()));
             pstmt.setString(6, a.getStatut().toString());
                if (a instanceof AbonnementAvecEngagement){
                    pstmt.setInt(7, ((AbonnementAvecEngagement) a).getDureeEngagementMois());
                    pstmt.setString(8, "AVEC_ENGAGEMENT");
                } else {
                    pstmt.setInt(7, 0);
                    pstmt.setString(8, "SANS_ENGAGEMENT");
                }
             pstmt.executeUpdate();
            System.out.println("Votre ID d'abonnement : " + a.getId());
            System.out.println("-----------------Abonnement créé avec succès.-------------------");
        }catch(SQLException e){
            Logger.error(e.getMessage());
        }
    };
    public  Optional<Abonnement> findById(String id) {
        String sql = "SELECT * FROM abonnement WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nomService = rs.getString("nomService");
                double montantMensuel = rs.getDouble("montantMensuel");
                LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
                LocalDate dateFin = rs.getDate("dateFin").toLocalDate();
                String statutStr = rs.getString("statutAbonnement");
                statusabonnement statut = statutStr != null ? statusabonnement.valueOf(statutStr) : null;
                int dureeEngagement = rs.getInt("dureeEngagementMois");
                TypeAbonnement type = TypeAbonnement.valueOf(rs.getString("typeAbonnement"));

                Abonnement abonnement;
                if (dureeEngagement == 0) {
                    abonnement = new AbonnementSansEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, type);
                } else {
                    abonnement = new AbonnementAvecEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, dureeEngagement, type);
                }
                return Optional.of(abonnement);
            } else {
                Logger.error("Abonnement non trouvé");
                return Optional.empty();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return Optional.empty();
        }
    }
    public List<Abonnement> findAll() {
        List<Abonnement> abonnements = new ArrayList<>();
        String SQL = "SELECT * FROM abonnement";

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
                String type = rs.getString("typeAbonnement");
                TypeAbonnement typeAbonnement = TypeAbonnement.valueOf(type);
                statusabonnement statut = statusabonnement.valueOf(status);
                if (dureeEngagement == 0) {
                    AbonnementSansEngagement abonnement = new AbonnementSansEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, typeAbonnement);
                    abonnements.add(abonnement);
                } else {
                    AbonnementAvecEngagement abonnement = new AbonnementAvecEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, dureeEngagement, typeAbonnement);
                    abonnements.add(abonnement);
                }
            }
            System.out.println("-----------------Abonnements récupérés avec succès.-------------------");
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des abonnements : " + e.getMessage());
            Logger.error(e.getMessage());
            return new ArrayList<>();
        }

        return abonnements;
    }

    public void update(Abonnement a, int dureeEngagementMois) {

        String sql;
        LocalDate DATE;
        if (a instanceof AbonnementAvecEngagement) {
            sql = "UPDATE abonnement SET nomService = ?, statutAbonnement = ?, dureeEngagementMois = ?, dateFin = ? WHERE id = ?";
            DATE = DateVerfied.dateFinAvecEng(a.getDateDebut(), dureeEngagementMois);
            a.setDateFin(DATE);
        } else {
            sql = "UPDATE abonnement SET nomService = ?, statutAbonnement = ? WHERE id = ?";
        }

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, a.getNomService());
            stmt.setString(2, a.getStatut().toString());

            if (a instanceof AbonnementAvecEngagement) {
                stmt.setInt(3, dureeEngagementMois);
                stmt.setDate(4, Date.valueOf(a.getDateFin()));
                stmt.setString(5, a.getId());
            } else {
                stmt.setString(3, a.getId());
            }
            stmt.executeUpdate();

            System.out.println("-----------------Abonnement mis à jour avec succès.-------------------");
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
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

    public void canseldAbonnement(String idAbonnement){

        String sql = "UPDATE abonnement SET statutAbonnement = ? WHERE id = ?";
        statusabonnement statutAbonnement = statusabonnement.RESILIE;
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, statutAbonnement.toString());
            stmt.setString(2, idAbonnement);
            stmt.executeUpdate();
            System.out.println("-----------------Abonnement annulé avec succès.-------------------");
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    };

    public void changeDateFin(String idAbonnement, LocalDate newDateFin){
        String sql = "UPDATE abonnement SET dateFin = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(newDateFin));
            stmt.setString(2, idAbonnement);
            stmt.executeUpdate();
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    };


}
