package is.hi.hbv501g.hbv501g.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author postgresqltutorial.com
 */
public class DatabaseConnection{

    private final String url = "jdbc:postgresql://ec2-52-212-228-71.eu-west-1.compute.amazonaws.com:5432/dfcs63u99jmpjn";
    private final String user = "nkkanoyhcelufu";
    private final String password = "a5376c7daa62cde714010e956cd07b9698a0bdd02071315fbc53aaccc3b4172f";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnection app = new DatabaseConnection();
        app.connect();
    }
}
