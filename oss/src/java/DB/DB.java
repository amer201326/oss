package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    public static Connection connection;

    public DB() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void startConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/oss?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    + "", "root", "root");
        }

    }

    public ResultSet read(String sql) throws SQLException {
        startConnection();
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }

    public int write(String sql) throws SQLException {
        startConnection();
        Statement statement = connection.createStatement();

        int x = statement.executeUpdate(sql);
        connection.close();
        connection = null;
        return x;
    }

}
