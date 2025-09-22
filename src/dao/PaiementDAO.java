package dao;

import dbConnection.DataBase;
import model.entity.Paiement;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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
                stmt.setString(7,p.getTypePaiement();
                stmt.executeUpdate();
                System.out.println("--------------Paiement ajouté avec succès !--------------");
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }

    };
    Paiement findById(String idPaiement){
        return null;
    };
    List<Paiement> findByAbonnement(String idAbonnement){};
    List<Paiement> findAll(){
        return java.util.Collections.emptyList();
    };
    void update(Paiement p){};
    void delete(String idPaiement){};
    List<Paiement> findUnpaidByAbonnement(String idAbonnement){};
}
