package dao;

import dbConnection.DataBase;
import model.entity.Abonnement;
import util.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;

public class AbonnementDAO {

    private Connection con;
    public AbonnementDAO() {
        this.con = DataBase.getInstance().getConnection();
    }
    public void create(Abonnement a){
        String sql = "INSERT INTO Abonnement (nomService, montantMensuel, dateDebut, dateFin, statut) VALUES (?, ?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = this.con.prepareStatement(sql);
             pstmt.setString(1, a.getNomService());
             pstmt.setDouble(2, a.getMontantMensuel());
             pstmt.setDate(3, Date.valueOf(a.getDateDebut()));
             pstmt.setDate(4, Date.valueOf(a.getDateFin()));
             pstmt.setString(5, a.getStatut().toString());
             pstmt.executeUpdate();
        }catch(SQLException e){
            Logger.error(e.getMessage());
        }
    };
    public Abonnement findById() {
        try {
            List<Abonnement> abonnements = findAll(); // récupère la liste

            Abonnement abonnement = abonnements.stream()
                    .filter(a -> a.getId().isEmpty())
                    .findFirst()
                    .orElseThrow(() ->{
                       Exception error =   new Exception("Abonnement non trouvé");
                        Logger.error(error.getMessage());
                        return error;
                    });
            return abonnement;

        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    List<Abonnement> findAll(){

    };
    void update(){};
    void delete(){};
    List<Abonnement> findActiveSubscriptions(){};
}
