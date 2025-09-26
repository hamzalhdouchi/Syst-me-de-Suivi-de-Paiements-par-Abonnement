package service;

import dao.PaiementDAO;
import dao.interfaceDAO.AbonnementInterface;
import dao.interfaceDAO.PaiementInterface;
import model.entity.Abonnement;
import model.entity.Paiement;
import model.enums.StatutPaiement;
import model.enums.TypeAbonnement;
import util.DateVerfied;
import util.Logger;
import util.UuidGen;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class PaiementServiceImpl {

    private final PaiementInterface paiementDao;
    private final AbonnementInterface abonnementDao;

    public PaiementServiceImpl(PaiementInterface paiementDao, AbonnementInterface abonnementDao) {
        this.abonnementDao = abonnementDao;
        this.paiementDao = paiementDao;
    }

        public void CreerPaiement(Paiement paiement) {
            Abonnement abonnement = null;
        try {
             abonnement = abonnementDao.findById(paiement.getIdAbonnement()).get();
        }catch (NoSuchElementException e){
            Logger.log("Abonnement not found with ID: " + paiement.getIdAbonnement());
            return;
        }
            if (abonnement.getType().equals(TypeAbonnement.SANS_ENGAGEMENT)){
                LocalDate newDate = DateVerfied.datefinSansEng(abonnement.getDateFin());
                try{
                    abonnementDao.changeDateFin(paiement.getIdAbonnement(), newDate);
                }catch (Exception e){
                    Logger.error("Error verifying date: " + e.getMessage());
                    return;
                }
            }


            paiement.setMontent(abonnement.getMontantMensuel());
            if(abonnement.getStatut().equals("RESILIE") && abonnement.getStatut().equals("SUSPENDU")){
                System.out.println("Abonnement is not active, cannot create Paiement.");
                return;
            }
            LocalDate Date = LocalDate.now();
            paiement.setDatePaiement(Date);
            LocalDate dateEcheance = DateVerfied.datefinSansEng(paiement.getDatePaiement());
            StatutPaiement statutPaiement = StatutPaiement.PAYE;
            String idPaiement = UuidGen.codeGen();
            paiement.setIdPaiement(idPaiement);
            if (paiement.getIdPaiement() == null) {
                System.out.println("Paiement ID is null, cannot create Paiement.");
                return;
            }
            if (paiement.getMontent() <= 0) {
                System.out.println("Montant must be greater than zero.");
                return;
            }
            Paiement newPaiement = new Paiement(paiement.getIdAbonnement(), idPaiement, dateEcheance, Date, statutPaiement, paiement.getTypePaiement(), paiement.getMontent());

            paiementDao.create(newPaiement);

        }

        public Paiement findById(String idPaiement) throws Exception {
            if (idPaiement.equals("")) {
                throw new Exception("ID Paiement cannot be empty.");
            }
            return paiementDao.findById(idPaiement);
        }

       public void update(Paiement p){

            if(p.getIdPaiement()==null){
                System.out.println("Paiement ID is null, cannot update Paiement.");
                return;
            }
            StatutPaiement  statutPaiement = StatutPaiement.valueOf(p.getStatut().toString());
            Paiement paiement = new Paiement(null,p.getIdPaiement() ,null,null,statutPaiement,null,0);
           paiementDao.update(paiement);
        }

        public void delete(String IdPaiement) {
            if (IdPaiement == null) {
                System.out.println("Paiement ID is null, cannot delete Paiement.");
                return;
            }
            paiementDao.delete(IdPaiement);
        }

    public List<Paiement> findByAbonnement(String idAbonnement,String type) {
        if (idAbonnement == null) {
            System.out.println("Abonnement ID is null or empty, cannot find unpaid Paiements.");
            return null;
        }
        try {
            return paiementDao.findByAbonnement(idAbonnement, type);

        }catch (Exception e){
            Exception exception = new Exception("Error finding Paiements by Abonnement ID: " + e.getMessage());
            System.out.println(exception.getMessage());
        }
            return  null;
    }

    public double sommePaiement(String idAbonnement){
        if (idAbonnement == null) {
            System.out.println("Abonnement ID is null or empty, cannot find unpaid Paiements.");
            return 0;
        }
        double some = paiementDao.SommePayéeAbonnement(idAbonnement);
        return some;

    }

    public List<Paiement> last5Paiements(){
        return paiementDao.last5Paiements();
    }

    public List<Paiement> PaiementsNonPaye() {
        return paiementDao.PImpyeAbonnement();
    }

    public double SommeImpyeAbonnement(){
        return paiementDao.SommeImpyeAbonnement();
    }


    public double rapportMensuel(int mois, int annee) {
        LocalDate start = LocalDate.of(annee, mois, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<Paiement> paiements = paiementDao.findByDateRange(start, end);

        double total = paiements.stream().mapToDouble(Paiement::getMontent).sum();
        System.out.println("Total des paiements pour " + mois + "/" + annee + " : " + total);
        return total;
    }

    public double rapportAnnuel(int annee) {
        LocalDate start = LocalDate.of(annee, 1, 1);
        LocalDate end = LocalDate.of(annee, 12, 31);
        List<Paiement> paiements = paiementDao.findByDateRange(start, end);

        double total = paiements.stream().mapToDouble(Paiement::getMontent).sum();
        System.out.println("Total des paiements pour l'année " + annee + " : " + total);
        return total;
    }
}




