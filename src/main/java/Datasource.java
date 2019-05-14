import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class Datasource {
    //
    public static final String DB_NAME = "jobs.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\" + DB_NAME;
    //
    public static final String TABLE_RESUME = "resume";

    private static final String INSERT_SQL = "INSERT INTO " +
            TABLE_RESUME +
            "(payment_from, payment_to,phone, currency,profession, compensation, metro, candidat, type_of_work, education,experience,town ) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private Connection conn;

    public boolean open() {
        try {

            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public void insert(ArrayList<String> values) {
        try (PreparedStatement statement = conn.prepareStatement(INSERT_SQL)) {
            for (int i = 0; i < values.size(); i++) {

                statement.setString(i + 1, values.get(i));

            }
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
