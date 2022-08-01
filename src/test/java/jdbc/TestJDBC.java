package jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestJDBC {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestJDBC.class));
    private static Connection connectionDB;

    @BeforeAll
    public static void setupConnection() {

        LOGGER.info("JDBC setup connection test started");

        final JDBConnector jdbcDriverSetup = new JDBConnector();
        jdbcDriverSetup.dbDriverSetup();
        connectionDB = jdbcDriverSetup.getConnectionDB();
        assertNotNull(connectionDB, "no connection");
    }

    @Test
    public void testJDBConnectionToDBWithDataSimple() throws SQLException {

        LOGGER.info("JDBC simple test started");

        Statement stm1 = connectionDB.createStatement();
        ResultSet resultSet1 = stm1.executeQuery("SELECT * FROM user WHERE username = 'Cardinal';");

        while (resultSet1.next()) {
            System.out.println(String.format("Test '%s' complete. Result: '%s'", "Test connection", resultSet1.getString("email")));
            assertEquals("cardinal@uk.cmo", resultSet1.getString("email"), "Cardinal has incorrect email");
        }

    }

    @Test
    public void testJDBConnectionToDBWithDataUseJoin() throws SQLException {

        LOGGER.info("JDBC test with JOIN started");

        Statement stm2 = connectionDB.createStatement();
        ResultSet resultSet2 = stm2.executeQuery(
                "SELECT *\n" +
                        "FROM testdb.persons\n" +
                        "INNER JOIN testdb.user\n" +
                        "ON testdb.user.username=testdb.persons.username\n" +
                        "WHERE testdb.user.email = 'cardinal@uk.cmo';");

        while (resultSet2.next()) {
            System.out.println(String.format("Test '%s' complete. Result: '%s', '%s' ", "Test connection",
                    resultSet2.getString("first_name"), resultSet2.getString("last_name")));
            assertEquals("Enrique", resultSet2.getString("first_name"), "Cardinal has incorrect First Name");
            assertEquals("Iglesias", resultSet2.getString("last_name"), "Cardinal has incorrect Last Name");
        }

    }

}
