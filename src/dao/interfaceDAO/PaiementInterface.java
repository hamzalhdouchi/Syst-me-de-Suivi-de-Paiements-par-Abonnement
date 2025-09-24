package dao.interfaceDAO;

import model.entity.Paiement;

import java.util.List;

public interface PaiementInterface {
    public void create(Paiement p);
    public Paiement findById(String id) throws  Exception;
    public void update(Paiement p);
    public void delete(String id);
    public List<Paiement> findByAbonnement(String idAbonnement) throws Exception;
}
