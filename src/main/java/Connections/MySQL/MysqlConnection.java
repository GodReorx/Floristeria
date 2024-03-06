package Connections.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlConnection {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + Constants.MYSQL_SERVER + "/" + Constants.MYSQL_DATABASE);
        config.setUsername(Constants.MYSQL_USERNAME);
        config.setPassword(Constants.MYSQL_PASSWORD);
        dataSource = new HikariDataSource(config);
    }

    public static void connectMySQL(){
        /*String url = "jdbc:mysql://" + Constants.MYSQL_SERVER + "/" + Constants.MYSQL_DATABASE;
        try {
            Connection connection = DriverManager.getConnection(url,Constants.MYSQL_USERNAME,Constants.MYSQL_PASSWORD);
            System.out.println("Conectado a la bbdd");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Conectado a la bbdd");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
