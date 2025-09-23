package dao.interfaceDAO;

import model.entity.Abonnement;

import java.util.List;
import java.util.Optional;

public interface AbonnementInterface {

    public void create(Abonnement a);
    public Optional<Abonnement> findById(String id);
    public List<Abonnement> findAll();
    public void update(Abonnement a, int dureeEngagementMois);
    public List<Abonnement> findActiveSubscriptions();
}
