package dao.interfaceDAO;

import model.entity.Abonnement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AbonnementInterface {

    public void create(Abonnement a);
    public Optional<Abonnement> findById(String id);
    public List<Abonnement> findAll();
    public void update(Abonnement a, int dureeEngagementMois);
    void delete(String idAbonnement);
    public List<Abonnement> findActiveSubscriptions();
    public void canseldAbonnement(String idAbonnement);
    public void changeDateFin(String idAbonnement, LocalDate newDateFin);

}
