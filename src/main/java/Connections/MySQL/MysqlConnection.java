package Connections.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
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
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Conectado a la bbdd");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
