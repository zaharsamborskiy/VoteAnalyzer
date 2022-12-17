import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBConnection {

    public static Connection connection;
    public static PreparedStatement preparedStatement;
    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "rootpass";
    private static StringBuilder insertQuery = new StringBuilder();
    private static final int core = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService service = Executors.newFixedThreadPool(core);

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "PRIMARY KEY(id))");
                /*connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "count INT NOT NULL, " +
                        "PRIMARY KEY(id))");*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void printVoterCounts () throws SQLException {
        String sql = "SELECT *, count(*) c FROM learn.voter_count group by concat(name, birthDate) having c > 1 order by name";
        ResultSet rs = DBConnection.connection.createStatement().executeQuery(sql);

        service.submit(() -> {
            try {
                if (!rs.next()) {
                    rs.close();
                    service.shutdown();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                service.shutdown();
            }
        });
        message(rs);
    }

    private static void message(ResultSet rs) throws SQLException
    {
        while (rs.next())
        {
            System.out.println(("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("c")));
        }
        rs.close();
    }
    public static void multiInsert() throws SQLException
    {
        String sql = "INSERT INTO learn.voter_count(name, birthDate, `count`) " +
                "VALUES " + insertQuery.toString() +
                "on duplicate key update `count` = `count` + 1;";
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) throws SQLException
    {
        birthDay = birthDay.replace('.', '-');
        String finalBirthDay = birthDay;
        insertQuery.append(insertQuery.length() == 0 ? "" : ", ")
                .append("('")
                .append(name)
                .append("', '")
                .append(finalBirthDay)
                .append("', 1) ");
    }
}
