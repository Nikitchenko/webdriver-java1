package jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class TestJDBC {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestJDBC.class));

    @Test
    public void testJDBConnectionToDBWithData() throws SQLException {
        LOGGER.info("JDBC test started");

        final JDBConnector jdbcDriverSetup = new JDBConnector();
        jdbcDriverSetup.dbDriverSetup();

        Connection connectionDb = jdbcDriverSetup.getConnectionDB();
        assertNotNull(connectionDb, "no connection");

        Statement stm1 = connectionDb.createStatement();
        ResultSet resultSet1 = stm1.executeQuery("SELECT * FROM user WHERE username = 'Cardinal';");

        while (resultSet1.next()) {
            System.out.println(String.format("Test '%s' complete. Result: '%s'", "Test connection", resultSet1.getString("email")));
            assertEquals("cardinal@uk.cmo", resultSet1.getString("email"), "Cardinal has incorrect email");
        }

        Statement stm2 = connectionDb.createStatement();
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
