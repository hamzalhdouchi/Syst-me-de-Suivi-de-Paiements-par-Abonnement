package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static DataBase instance = null;
    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/abonnements?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "37533753";

    private DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
        }
    }

    public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }



    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();
        Connection conn = db.getConnection();

        if (conn != null) {
            System.out.println("Connexion obtenue via Singleton.");
        } else {
            System.out.println("Erreur lors de la récupération de la connexion.");
        }
    }

}
