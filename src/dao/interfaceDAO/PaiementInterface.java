package dao.interfaceDAO;

import model.entity.Paiement;

import java.time.LocalDate;
import java.util.List;

public interface PaiementInterface {
    public void create(Paiement p);
    public Paiement findById(String id) throws  Exception;
    public void update(Paiement p);
    public void delete(String id);
    public List<Paiement> findByAbonnement(String idAbonnement, String type) ;
    public double SommePayéeAbonnement(String idAbonnement);
    public double SommeImpyeAbonnement();
    public List<Paiement> PImpyeAbonnement();
    public List<Paiement> last5Paiements();
    public List<Paiement> findByDateRange(LocalDate start, LocalDate end);
}
