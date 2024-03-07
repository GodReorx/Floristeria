package Connections.MySQL;

import Connections.DAO.GenericDAO;
import FlowerStore.Interfaces.GardenElements;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GardenElementsMySQL<T extends GardenElements> implements GenericDAO {

    private static final HikariDataSource dataSource;
    private static Connection connection;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + Constants.MYSQL_SERVER + "/" + Constants.MYSQL_DATABASE);
        config.setUsername(Constants.MYSQL_USERNAME);
        config.setPassword(Constants.MYSQL_PASSWORD);
        dataSource = new HikariDataSource(config);
    }
    public GardenElementsMySQL(){
        try {
            connection = dataSource.getConnection();
            System.out.println("Conectado a la bbdd");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static void connectMySQL(){
        try {
            if(connection == null) {
                connection = dataSource.getConnection();
                System.out.println("Conectado a la bbdd");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static void disconnectMySQL(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @Override
    public HashMap<Integer, String> showFlowerStore() throws SQLException {
        HashMap<Integer,String> flowerStores = new HashMap<>();
        connectMySQL();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM FlowerShops");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            flowerStores.put(rs.getInt("IdFlowerShop"),rs.getString("Name"));
        }
        disconnectMySQL();
        return flowerStores;
    }

    @Override
    public GardenElements findById(int id) {
        GardenElements gardenElement = null;
        connectMySQL();
        String query = "SELECT * FROM GardenElements WHERE Id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                gardenElement = new GardenElements(
                        rs.getInt("Id"),
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectMySQL();
        }
        return gardenElement;
    }

    @Override
    public List<GardenElements> allGardenElements(int idFlowerStore) {
        List<GardenElements> gardenElements = new ArrayList<>();
        connectMySQL();
        String query = "SELECT * FROM GardenElements WHERE IdFlowerShop = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, idFlowerStore);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Creamos objetos GardenElements basados en los datos recuperados de la base de datos
                GardenElements gardenElement = new GardenElementImpl(
                        rs.getInt("Id"),
                        rs.getInt("TypesId"),
                        rs.getString("Features")

                );
                gardenElements.add(gardenElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectMySQL();
        }
        return gardenElements;
    }
    }

    @Override
    public int createStore(String name) {
        int newStoreId = -1;
        connectMySQL();
        String query = "INSERT INTO FlowerShops (Name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                newStoreId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to get the generated ID for the new store.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectMySQL();
        }
        return newStoreId;
    }


    @Override
    public void addStock(GardenElements gardenElement, int quantity) {
        connectMySQL();
        String query = "UPDATE GardenElements SET Stock = Stock + ? WHERE Id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, gardenElement.getIdProduct());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectMySQL();
        }
    }

    @Override
    public void updateStock(GardenElements gardenElement, int quantity) {
        // Update stock para un producto
    }

    @Override
    public void deleteStock(GardenElements gardenElement) {
        // Eliminar el stock completo
    }

    @Override
    public HashMap<Integer, Date> allTickets(int idFlowerStore) {
        //Buscar todos los tickets de la tienda activa
        return null;
    }

    @Override
    public void addTicket(int idFlowerstore, HashMap gardenElementsList) {
        //Para añadir un ticket nuevo
    }
}
