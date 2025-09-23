package dao;

import dbConnection.DataBase;
import model.entity.Paiement;
import util.Logger;
import model.enums.StatutPaiement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PaiementDAO {

    private Connection connection;

    public PaiementDAO() {
        this.connection = DataBase.getInstance().getConnection();
    }

    void create(Paiement p){
            String sql = "INSERT INTO paiement (id, idAbonnement, montant, datePaiement, dateEcheance, statut, TypePaiement) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try{
                PreparedStatement stmt = this.connection.prepareStatement(sql);
                stmt.setString(1,p.getIdPaiement());
                stmt.setString(2,p.getIdAbonnement());
                stmt.setDouble(3,p.getMontent());
                stmt.setObject(4,p.getDatePaiement());
                stmt.setObject(5,p.getDateEcheance());
                stmt.setString(6,p.getStatut().toString());
                stmt.setString(7,p.getTypePaiement().toString());
                stmt.executeUpdate();
                System.out.println("--------------Paiement ajouté avec succès !--------------");
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }

    };
    Paiement findById(String idPaiement) throws Exception {
        List<Paiement> paiements = findAll();

        return paiements.stream()
                .filter(p -> p.getIdPaiement().equals(idPaiement))
                .findFirst()
                .orElseThrow(() ->{
                    Exception error =   new Exception("Abonnement non trouvé");
                    Logger.error(error.getMessage());
                    return error;
               });
    };

   List<Paiement> findAll(){
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Paiement p = new Paiement(
                        rs.getString("idAbonnement"),
                        rs.getString("idPaiement"),
                        rs.getDate("dateEcheance").toLocalDate(),
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
    };
    void update(Paiement p){};
    void delete(String idPaiement){};
    List<Paiement> findUnpaidByAbonnement(String idAbonnement) throws Exception {
        List<Paiement> paiments = findAll();
        List<Paiement> paiementsNonPaye = new ArrayList<>();
        paiementsNonPaye = paiments.stream().filter((p) -> p.getIdAbonnement().equals(idAbonnement))
                .filter((p) -> p.getStatut() == StatutPaiement.NON_PAYE).collect(Collectors.toList());
        if(paiementsNonPaye.isEmpty()){
            String error = "Aucun paiement non payé trouvé pour cet abonnement.";
            Logger.error(error);
            throw new Exception(error);
        }
        return paiementsNonPaye;
    }
}
