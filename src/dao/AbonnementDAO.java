package dao;

import com.mysql.cj.xdevapi.AddStatement;
import dbConnection.DataBase;
import model.entity.Abonnement;
import model.entity.AbonnementAvecEngagement;
import model.entity.AbonnementSansEngagement;
import model.enums.statusabonnement;
import util.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;

public class AbonnementDAO {

    private Connection con;
    public AbonnementDAO() {
        this.con = DataBase.getInstance().getConnection();
    }
    public void create(Abonnement a){

        a.setId( UUID.randomUUID().toString());
        String sql = "INSERT INTO Abonnement (nomService, montantMensuel, dateDebut, dateFin, statut,dureeEngagementMois) VALUES (?, ?, ?, ?, ?,?)";
        try{
            PreparedStatement pstmt = this.con.prepareStatement(sql);
             pstmt.setString(1, a.getNomService());
             pstmt.setDouble(2, a.getMontantMensuel());
             pstmt.setDate(3, Date.valueOf(a.getDateDebut()));
             pstmt.setDate(4, Date.valueOf(a.getDateFin()));
             pstmt.setString(5, a.getStatut().toString());
             if (a instanceof AbonnementAvecEngagement){
                 pstmt.setInt(6, 0);
             }
             pstmt.executeUpdate();

            System.out.println("-----------------Abonnement créé avec succès.-------------------");
        }catch(SQLException e){
            Logger.error(e.getMessage());
        }
    };
    public Abonnement findById() {
        try {
            List<Abonnement> abonnements = findAll();

            Abonnement abonnement = abonnements.stream()
                    .filter(a -> a.getId().isEmpty())
                    .findFirst()
                    .orElseThrow(() ->{
                       Exception error =   new Exception("Abonnement non trouvé");
                        Logger.error(error.getMessage());
                        return error;
                    });
            return abonnement;

        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }
    List<Abonnement> findAll() {
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
                String status = rs.getString("statut");
                int dureeEngagement = rs.getInt("dureeEngagementMois");
                statusabonnement statut = statusabonnement.valueOf(status);
                if (dureeEngagement == 0) {
                    AbonnementSansEngagement abonnement = new AbonnementSansEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut);
                    abonnements.add(abonnement);
                } else {
                    AbonnementAvecEngagement abonnement = new AbonnementAvecEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, dureeEngagement);
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

    void update(Abonnement a){
        String sql = "UPDATE abonnement (nomService ,statut) VALUE (?,?) where id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, a.getNomService());
            stmt.setString(2, a.getStatut().toString());
            stmt.setString(3, a.getId());
            stmt.executeUpdate();
            System.out.println("-----------------Abonnement mis à jour avec succès.-------------------");
        }catch (Exception e){
            Logger.error(e.getMessage());
        }

    };
    void delete(Abonnement a){
        String sql = "DELETE FROM abonnement where id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, a.getId());
            stmt.executeUpdate();
            System.out.println("-----------------Abonnement supprimé avec succès.-------------------");
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    };
    List<Abonnement> findActiveSubscriptions(){
        List<Abonnement> abonnements = new ArrayList<>();
        String SQL = "SELECT * FROM Abonnement WHERE statut = 'ACTIF'";

        try {
            PreparedStatement stmt = this.con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String nomService = rs.getString("nomService");
                double montantMensuel = rs.getDouble("montantMensuel");
                LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
                LocalDate dateFin = rs.getDate("dateFin").toLocalDate();
                String status = rs.getString("statut");
                int dureeEngagement = rs.getInt("dureeEngagementMois");
                statusabonnement statut = statusabonnement.valueOf(status);
                if (dureeEngagement == 0) {
                    AbonnementSansEngagement abonnement = new AbonnementSansEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut);
                    abonnements.add(abonnement);
                } else {
                    AbonnementAvecEngagement abonnement = new AbonnementAvecEngagement(id, nomService, montantMensuel, dateDebut, dateFin, statut, dureeEngagement);
                    abonnements.add(abonnement);
                }
            }
            System.out.println("-----------------Abonnements actifs récupérés avec succès.-------------------");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return new ArrayList<>();
        }

        return abonnements;
    };
}
