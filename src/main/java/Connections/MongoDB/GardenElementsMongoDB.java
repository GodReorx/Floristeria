package Connections.MongoDB;

import Connections.DAO.Constants;
import Connections.DAO.GenericDAO;
import FlowerStore.Interfaces.GardenElements;
import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GardenElementsMongoDB implements GenericDAO {

    //private final MongoCollection<Document> collection;
    private static ConnectionString connectionString = new ConnectionString(Constants.MONGO_URL);
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    public GardenElementsMongoDB() {
        try {
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase("flowerStoreBBDD");
        } catch (MongoException e){
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    private static void connectMongodb(){
        try{
            if(mongoClient == null){
                mongoClient = MongoClients.create(connectionString);
                database = mongoClient.getDatabase("flowerStoreBBDD");
            }
        } catch (MongoException e){
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    private static void disconnectMongodb(){
        try{
            if(mongoClient != null){
                mongoClient.close();
            }
        } catch (MongoException e){
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @Override
    public HashMap<Integer, String> showFlowerStore() {
        return null;
    }

    @Override
    public GardenElements findById(int id) {
        return null;
    }

    @Override
    public List<GardenElements> allGardenElements(int idFlowerStore) {
        return null;
    }

    @Override
    public int createStore(String name) {
        return 0;
    }

    @Override
    public void updateStock(GardenElements gardenElement, int quantity) {

    }

    @Override
    public void deleteStock(GardenElements gardenElement) {

    }

    @Override
    public HashMap<Integer, Date> allTickets(int idFlowerStore) {
        return null;
    }

    @Override
    public void addTicket(int idFlowerstore, HashMap gardenElementsList) {

    }

    @Override
    public void addStock(int idFlowerStore, List<GardenElements> products) {

    }
}
