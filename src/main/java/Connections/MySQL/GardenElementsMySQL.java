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
    private void disconnectMySQL(){
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
        HashMap<Integer,String> flowerStores = null;
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM FlowerShops");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            flowerStores.put(rs.getInt("IdFlowerShop"),rs.getString("Name"));
        }
        return flowerStores;
    }

    @Override
    public GardenElements findById(int id) {
        // Implementa la lógica para encontrar un producto por su ID en la base de datos
        return null;
    }

    @Override
    public List<GardenElements> allGardenElements(int idFlowerStore) {
        // Retorna todos los elementos que tiene en stock
        return null;
    }

    @Override
    public int createStore(String name) {
        //Crea una tienda nueva en la bbdd y retorna su ID
        return 0;
    }

    @Override
    public void addStock(GardenElements gardenElement, int quantity) {
        // Agregar stock nuevo producto
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
