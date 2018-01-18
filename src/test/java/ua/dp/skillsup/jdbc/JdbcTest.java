package ua.dp.skillsup.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

    private Connection conn;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:", "sa", "");
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void testUser() throws Exception {
        executeStatement("CREATE TABLE USER\n" +
                "(\n" +
                "    ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "    USERNAME VARCHAR(64) NOT NULL UNIQUE,\n" +
                "    PASSWORD VARCHAR(60) NOT NULL,\n" +
                "    EMAIL VARCHAR(64) NOT NULL,\n" +
                ");");

        executeStatement("INSERT INTO user(USERNAME, PASSWORD, EMAIL) VALUES\n" +
                "('admin','1234','helen.moore@gmail.com'), " +
                "('vasya','123','vasya@gmail.com');");

        printQuery("select * from user");

        executeStatement("CREATE TABLE POST\n" +
                "(\n" +
                "    ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "    USER_ID BIGINT NOT NULL,\n" +
                "    TITLE VARCHAR(60) NOT NULL,\n" +
                "    CONTENT VARCHAR(500) NOT NULL,\n" +
                ");");
        executeStatement("ALTER TABLE POST ADD (timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");

        executeStatement("INSERT INTO POST(USER_ID, TITLE, CONTENT) VALUES\n" +
                "(2,'I like burritos','burritos are awesome'), " +
                "(2,'I like taсos too','they are awesome as well');");

        System.out.println();
        printQuery("select * from post");

    }

    private void executeStatement(String createUser) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute(createUser);
        statement.close();
    }

    private void printQuery(String sql) throws SQLException {
        ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            System.out.print(resultSet.getMetaData().getColumnName(i + 1) + "|");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 0; i < columnCount; i++) {
                System.out.print(resultSet.getObject(i + 1) + "|");
            }
            System.out.println();
        }
    }
}
