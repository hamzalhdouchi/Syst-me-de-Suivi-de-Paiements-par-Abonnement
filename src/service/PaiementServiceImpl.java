package service;

import dao.PaiementDAO;
import model.entity.Paiement;
import model.enums.StatutPaiement;
import util.DateVerfied;
import util.UuidGen;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class PaiementServiceImpl {

        public void CreerPaiement(Paiement paiement) {
            LocalDate Date = DateVerfied.DateNowVirfied(null);
            LocalDate dateEcheance = DateVerfied.datefinSansEng(Date);
            StatutPaiement statutPaiement = StatutPaiement.PAYE;
            String idPaiement = UuidGen.codeGen();
            if (paiement.getIdPaiement() == null) {
                System.out.println("Paiement ID is null, cannot create Paiement.");
                return;
            }
            if (paiement.getMontent() <= 0) {
                System.out.println("Montant must be greater than zero.");
                return;
            }
            PaiementDAO p;
            Paiement newPaiement = new Paiement(paiement.getIdAbonnement(), idPaiement, dateEcheance, Date, statutPaiement, paiement.getTypePaiement(), paiement.getMontent());
            p = new PaiementDAO();
            p.create(newPaiement);

        }

        public Paiement findById(String idPaiement) throws Exception {
            if (idPaiement.equals("")) {
                throw new Exception("ID Paiement cannot be empty.");
            }
            PaiementDAO p = new PaiementDAO();
            return p.findById(idPaiement);
        }

       public void update(Paiement p){

            if(p.getIdPaiement()==null){
                System.out.println("Paiement ID is null, cannot update Paiement.");
                return;
            }if(p.getMontent()<=0){
                System.out.println("Montant must be greater than zero.");
                return;
            }
            PaiementDAO paiementDAO = new PaiementDAO();
            StatutPaiement StatutPaiement = p.getStatut();
            Paiement paiement = new Paiement(p.getIdPaiement(),null ,null,null,StatutPaiement,null,p.getMontent());
            paiementDAO.update(paiement);
        }

        public void delete(String IdPaiement) {
            if (IdPaiement == null) {
                System.out.println("Paiement ID is null, cannot delete Paiement.");
                return;
            }
            PaiementDAO paiementDAO = new PaiementDAO();
            paiementDAO.delete(IdPaiement);
        }

    public List<Paiement> findUnpaidByAbonnement(String idAbonnement) throws Exception {
        if (idAbonnement == null) {
            System.out.println("Abonnement ID is null or empty, cannot find unpaid Paiements.");
            return null;
        }
        PaiementDAO paiementDAO = new PaiementDAO();
        return paiementDAO.findUnpaidByAbonnement(idAbonnement);

    }
}




