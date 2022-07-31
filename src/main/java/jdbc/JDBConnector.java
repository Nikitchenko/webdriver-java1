package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JDBConnector {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(JDBConnector.class));
    private Connection connection;

    public void dbDriverSetup() {
        LOGGER.info("Start setup JDBC.");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.severe("Exceptions. See details in console.");
            e.printStackTrace();
            return;
        }
        LOGGER.info("JDBC Driver registered.");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception.");
            e.printStackTrace();
        }

    }

    public Connection getConnectionDB() {
        return connection;
    }

}
