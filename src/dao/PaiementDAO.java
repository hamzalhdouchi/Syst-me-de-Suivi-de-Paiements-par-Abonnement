package dao;

import dao.interfaceDAO.PaiementInterface;
import dbConnection.DataBase;
import model.entity.Abonnement;
import model.entity.Paiement;
import util.Logger;
import model.enums.StatutPaiement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PaiementDAO implements PaiementInterface {

    private final Connection connection ;

    public PaiementDAO(Connection con) {
        this.connection = con;
    }


    public void create(Paiement p) {
        String sql = "INSERT INTO paiement (idPaiement, idAbonnement, montant, datePaiement, dateEcheance, statut, TypePaiement) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, p.getIdPaiement());
            stmt.setString(2, p.getIdAbonnement());
            stmt.setDouble(3, p.getMontent());
            stmt.setObject(4, p.getDatePaiement());
            stmt.setObject(5, p.getDateEcheance());
            stmt.setString(6, p.getStatut().toString());
            stmt.setString(7, p.getTypePaiement().toString());
            stmt.executeUpdate();
            System.out.println("--------------Paiement ajouté avec succès Votre ID de paiement : " + p.getIdPaiement() + "--------------");
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

    }

    ;

    public Paiement findById(String idPaiement) throws Exception {
        List<Paiement> paiements = findAll();

        return paiements.stream()
                .filter(p -> p.getIdPaiement().equals(idPaiement))
                .findFirst()
                .orElseThrow(() -> {
                    Exception error = new Exception("Abonnement non trouvé");
                    Logger.error(error.getMessage());
                    return error;
                });
    }

    ;

    public List<Paiement> findAll() {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paiement p = new Paiement(
                        rs.getString("idAbonnement"),
                        rs.getString("idPaiement"),
                        rs.getDate("dateEcheance").toLocalDate(),
                        rs.getDate("datePaiement").toLocalDate(),
                        StatutPaiement.valueOf(rs.getString("statut")),
                        rs.getString("typePaiement"),
                        rs.getDouble("montant")
                );
                paiements.add(p);
            }
            return paiements;
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return paiements;
    }

    ;

    public void update(Paiement p) {
        String sql = "UPDATE paiement SET   statut = ? WHERE idPaiement = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, p.getStatut().toString());
            stmt.setString(2, p.getIdPaiement());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("--------------Paiement mis à jour avec succès !--------------");
            } else {
                System.out.println("Aucun paiement trouvé avec l'ID spécifié.");
            }
        } catch (Exception e) {
            Logger.error("there no paiement in this id : " + e.getMessage());
        }
    }

    ;

    public void delete(String idPaiement) {
        String sql = "DELETE FROM paiement WHERE idPaiement = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, idPaiement);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("--------------Paiement supprimé avec succès !--------------");
            } else {
                System.out.println("Aucun paiement trouvé avec l'ID spécifié.");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    ;

    public List<Paiement> findByAbonnement(String idAbonnement, String type)  {
        List<Paiement> paiments = findAll();
        List<Paiement> paiementsNonPaye = new ArrayList<>();
        if (type.equals("TOUS")) {
            paiementsNonPaye = paiments.stream().filter((p) -> p.getIdAbonnement().equals(idAbonnement))
                    .collect(Collectors.toList());
            if (paiementsNonPaye.isEmpty()) {
                String error = "Aucun paiement trouvé pour cet abonnement.";
                Logger.error(error);
            }
            return paiementsNonPaye;
        } else if (type.equals("PAIEMENT")) {
            paiementsNonPaye = paiments.stream().filter((p) -> p.getIdAbonnement().equals(idAbonnement))
                    .filter((p) -> p.getStatut() == StatutPaiement.PAYE).collect(Collectors.toList());
            if (paiementsNonPaye.isEmpty()) {
                String error = "Aucun paiement payé trouvé pour cet abonnement.";
                Logger.error(error);
            }
            return paiementsNonPaye;
        } else if (type.equals("NON_PAIEMENT")) {
            paiementsNonPaye = paiments.stream().filter((p) -> p.getIdAbonnement().equals(idAbonnement))
                    .filter((p) -> p.getStatut() == StatutPaiement.NON_PAYE).collect(Collectors.toList());
            if (paiementsNonPaye.isEmpty()) {
                String error = "Aucun paiement non payé trouvé pour cet abonnement.";
                Logger.error(error);
            }
            return paiementsNonPaye;
        } else if (type.equals("EN_RETARD")) {
            paiementsNonPaye = paiments.stream().filter((p) -> p.getIdAbonnement().equals(idAbonnement))
                    .filter((p) -> p.getStatut() == StatutPaiement.NON_PAYE).collect(Collectors.toList());
            if (paiementsNonPaye.isEmpty()) {
                String error = "Aucun paiement non payé trouvé pour cet abonnement.";
                Logger.error(error);
            }
            return paiementsNonPaye;
        }
        return null;
    }

    public List<Paiement> PImpyeAbonnement() {
        List<Paiement> Paiement = findAll();
        Paiement = Paiement.stream()
                .filter(p -> p.getStatut() == StatutPaiement.NON_PAYE)
                .collect(Collectors.toList());

       return Paiement;
    }

    public double SommeImpyeAbonnement() {

        List<Paiement> Paiement = PImpyeAbonnement();

        if (Paiement.size() == 0) {
            System.out.println("Aucun paiement trouvé.");
            return 0;
        }
        double someP = Paiement.stream()
                .mapToDouble(p -> p.getMontent())
                .sum();
        return someP;

    }

    public double SommePayéeAbonnement(String idAbonnement) {

        List<Paiement> Paiement = findByAbonnement(idAbonnement,"TOUS");

        if (Paiement.size() == 0) {
            System.out.println("Aucun paiement trouvé pour cet abonnement.");
            return 0;
        }

        double someP = Paiement.stream()
                .mapToDouble(p -> p.getMontent())
                .sum();
        return someP;

    }

    public List<Paiement> last5Paiements() {
        List<Paiement> paiements = new ArrayList<>();
        paiements = findAll();

        List<Paiement> newPaiements = new ArrayList<>();
        newPaiements = paiements.stream().sorted(Comparator.comparing(Paiement::getDatePaiement).reversed())
                .limit(5)
                .collect(Collectors.toList());
        return newPaiements;
    }

    public List<Paiement> findByDateRange(LocalDate start, LocalDate end) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE datePaiement BETWEEN ? AND ?";

        try{
             PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paiement p = new Paiement(
                        rs.getString("idAbonnement"),
                        rs.getString("idPaiement"),
                        rs.getDate("dateEcheance").toLocalDate(),
                        rs.getDate("datePaiement").toLocalDate(),
                        StatutPaiement.valueOf(rs.getString("statut")),
                        rs.getString("typePaiement"),
                        rs.getDouble("montant")
                );
                paiements.add(p);
            }

        } catch (SQLException e){
            Logger.error(e.getMessage());
        }

        return paiements;
    }

}
