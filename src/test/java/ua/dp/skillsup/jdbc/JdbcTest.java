package ua.dp.skillsup.jdbc;


import com.sun.xml.internal.bind.v2.model.core.ID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.lang.StringUtils;


public class JdbcTest
{
    private Connection conn;

    @Before
    public void setUp() throws Exception
    {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:", "sa", "");
    }

    @After
    public void tearDown() throws Exception
    {
        conn.close();
    }

    @Test
    public void testUser() throws Exception
    {
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

        executeStatement("CREATE TABLE POST\n" +
                "(\n" +
                "    ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "    USER_ID BIGINT NOT NULL,\n" +
                "    TITLE VARCHAR(60) NOT NULL,\n" +
                "    CONTENT VARCHAR(500) NOT NULL,\n" +
                ");");
        executeStatement("ALTER TABLE POST ADD (`TIMESTAMP` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");

        executeStatement("INSERT INTO POST(USER_ID, TITLE, CONTENT) VALUES\n" +
                "(2,'I like burritos','burritos are awesome'), " +
                "(2,'I like taсos too','they are awesome as well');");

        executeStatement("CREATE TABLE IF NOT EXISTS `LIKE` (\n" +
                "    `ID` BIGINT  PRIMARY KEY AUTO_INCREMENT,\n" +
                "     `POST_ID` BIGINT NOT NULL,\n" +
                "     `USER_ID` BIGINT  NOT NULL,\n" +
                "     `TIMESTAMP` TIMESTAMP );");

        PreparedStatement preparedStatement =conn.prepareStatement("INSERT INTO USER(USERNAME,PASSWORD,EMAIL) VALUES (?,?,?)");

        for(int i=0;i<5;i++)
        {
            preparedStatement.setString(1,"user"+i);
            preparedStatement.setString(2,"password"+i);
            preparedStatement.setString(3,"user"+i+"@gmail.com");
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        preparedStatement = conn.prepareStatement("INSERT INTO POST(USER_ID,TITLE,CONTENT,`TIMESTAMP`) VALUES (?,?,?,?)");

        for(int i=0;i<10;i++)
        {
            Random random = new Random();

            int userId = Math.abs(random.nextInt()%getMaxId("SELECT MAX(`ID`) FROM USER"));
            if(userId ==0)
            {
                userId++;
            }

            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,"Another one Title № "+i+1);
            preparedStatement.setString(3, StringUtils.repeat("bla",i));
            preparedStatement.setTimestamp(4,new Timestamp(getRandomTime()));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        preparedStatement =conn.prepareStatement("INSERT INTO `LIKE`(POST_ID,USER_ID,`TIMESTAMP`) VALUES (?,?,?)");
        String maxUserIdSqlQuery = "SELECT MAX(`ID`) FROM USER;";
        String maxPostIdSqlQuery = "SELECT MAX(`ID`) FROM POST;";

        int maxUserId = getMaxId(maxUserIdSqlQuery);
        int maxPostId = getMaxId(maxPostIdSqlQuery);

        for(int i =0;i<30;i++)
        {
            Random random = new Random();
            int userId = Math.abs(random.nextInt()%maxUserId);
            if(userId== 0)
            {
                userId++;
            }

            int postId = Math.abs(random.nextInt()%maxPostId);
            if(postId==0)
            {
                postId++;
            }

            preparedStatement.setInt(1,postId);
            preparedStatement.setInt(2,userId);
            preparedStatement.setTimestamp(3,new Timestamp(getRandomTime()) );
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();

        /*************************************************************************************************************
         * Initialisation ends.
         *
         * Test queries below.
         *
         * ***********************************************************************************************************/
        System.out.println("\n list of all post with columns (title, username) : ");
        String query ="SELECT POST.TITLE , USER.USERNAME FROM USER INNER JOIN POST  ON(USER.ID = POST.USER_ID);";
            printQuery(query);
        /*
        2nd test
         */
        System.out.println("\n post list (title, total_likes_received) with the most popular post at the top : ");

        query = "SELECT POST.TITLE, COUNT(`LIKE`.ID) AS MAX_LIKES_COUNT FROM POST " +
                " INNER JOIN `LIKE`  ON(POST.ID = `LIKE`.POST_ID)" +
                " GROUP BY POST.ID " +
                "ORDER BY MAX_LIKES_COUNT DESC;";
        printQuery(query);
        /*
        3d test
         */
        System.out.println("\n post list (title, total_likes_received) with the most popular" +
                " post at the top, display only posts written during some time interval : \n");

        preparedStatement = conn.prepareStatement("SELECT POST.TITLE , COUNT(`LIKE`.ID) AS MAX_LIKES_COUNT FROM POST " +
                " INNER JOIN `LIKE`  ON(POST.ID = `LIKE`.POST_ID) " +
                "WHERE (POST.`TIMESTAMP` > ?) AND (POST.`TIMESTAMP` < ? )" +
                "GROUP BY POST.ID " +
                "ORDER BY MAX_LIKES_COUNT DESC ;");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,7,17);

        preparedStatement.setTimestamp(1,new Timestamp(calendar.getTimeInMillis()));
        calendar.set(2017,7,27);
        preparedStatement.setTimestamp(2,new Timestamp(calendar.getTimeInMillis()));
       ResultSet rs =  preparedStatement.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        while (rs.next()) {
            for (int i=1; i<=rsmd.getColumnCount(); i++) {
                System.out.print(rs.getObject(i) + "|");
            }
            System.out.println();
        }

        /*
        4th test
         */
        System.out.println("\npost list (title, total_likes_received) with the most popular post " +
                "at the top, display only posts that have more than 5 likes : \n");

        query = "SELECT POST.TITLE, COUNT(`LIKE`.ID) as TOTAL_LIKES_COUNT " +
                "FROM POST INNER JOIN `LIKE` ON (POST.ID = `LIKE`.POST_ID) " +
                "GROUP BY POST.ID HAVING COUNT(`LIKE`.ID)>5";
        printQuery(query);


    }

    private void executeStatement(String createUser) throws SQLException
    {
        Statement statement = conn.createStatement();
        statement.execute(createUser);
        statement.close();
    }

    private void printQuery(String sql) throws SQLException
    {
        ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 0; i < columnCount; i++)
        {
            System.out.print(resultSet.getMetaData().getColumnName(i + 1) + "|");
        }
        System.out.println();

        while (resultSet.next())
        {
            for (int i = 0; i < columnCount; i++) {
                System.out.print(resultSet.getObject(i + 1) + "|");
            }
            System.out.println();
        }
    }
    private long getRandomTime() throws ParseException
    {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm:ss", Locale.ENGLISH);
        Calendar cal=Calendar.getInstance();
        String str_date1="17-August-17 02:10:15";
        String str_date2="27-August-17 02:10:20";

        cal.setTime(formatter.parse(str_date1));
        Long value1 = cal.getTimeInMillis();

        cal.setTime(formatter.parse(str_date2));
        Long value2 = cal.getTimeInMillis();
        long value3 = (long)(value1 + Math.random()*(value2 - value1));
        cal.setTimeInMillis(value3);

        return cal.getTimeInMillis();
    }
    private int getMaxId(String sql)
    {
        int identifier = 0;
        try {
            Statement id = conn.createStatement();

            id.executeQuery(sql);
            ResultSet rs = id.getResultSet();

            while (rs.next())
            {
                identifier = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return identifier;
    }
}
