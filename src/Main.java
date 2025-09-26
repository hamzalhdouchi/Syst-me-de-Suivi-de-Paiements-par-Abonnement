import dao.AbonnementDAO;
import dao.PaiementDAO;
import service.AbonnementServiceImpl;
import service.PaiementServiceImpl;
import ui.MainMenu;
import ui.MenuAbonnements;
import ui.MenuPaiements;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection connection = dbConnection.DataBase.getInstance().getConnection();
        AbonnementDAO abonnementDao = new AbonnementDAO(connection);
        PaiementDAO paiementDao = new PaiementDAO(connection);
        PaiementServiceImpl paiementService = new PaiementServiceImpl(paiementDao, abonnementDao);
        AbonnementServiceImpl abonnementService = new AbonnementServiceImpl(abonnementDao);
        MenuAbonnements menu = new MenuAbonnements(abonnementService);
        MenuPaiements menuPaiements = new MenuPaiements(paiementService);
        MainMenu mainMenu = new MainMenu(menuPaiements ,menu);
        mainMenu.mainMenu();


    }
}
