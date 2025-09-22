package dao;

import model.entity.Paiement;

import java.util.List;

public class PaiementDAO {
    void create(Paiement p){};
    Paiement findById(String idPaiement){};
    List<Paiement> findByAbonnement(String idAbonnement){};
    List<Paiement> findAll(){};
    void update(Paiement p){};
    void delete(String idPaiement){};
    List<Paiement> findUnpaidByAbonnement(String idAbonnement){};
}
