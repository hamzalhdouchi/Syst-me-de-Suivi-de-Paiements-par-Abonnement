package dao;

import model.entity.Paiement;

import java.util.List;

public class PaiementDAO {
    void create(Paiement p){};
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
