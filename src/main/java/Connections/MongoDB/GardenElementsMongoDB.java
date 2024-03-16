package Connections.MongoDB;

import Connections.DAO.Constants;
import Connections.DAO.GenericDAO;
import FlowerStore.FlowerStore;
import FlowerStore.Interfaces.GardenElements;
import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    private static void connectMongodb(){
        try{
            if(mongoClient == null){
                mongoClient = MongoClients.create(connectionString);
                database = mongoClient.getDatabase("flowerStoreBBDD");
            }
        } catch (MongoException e){
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    private static void disconnectMongodb(){
        try{
            if(mongoClient != null){
                mongoClient.close();
            }
        } catch (MongoException e){
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @Override
    public List<FlowerStore> showFlowerStore() {
        return null;
    }

    @Override
    public List<GardenElements> allGardenElements(String idFlowerStore) {
        return null;
    }

    @Override
    public String createStore(String name) {
        MongoCollection<Document> collection = database.getCollection("FlowerShops");
        Document newFlowerShop = new Document();
        try {
            List<Document> stock = new ArrayList<>();
            stock.add(new Document("type", "tree")
                    .append("Features", "3 m")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "tree")
                    .append("Features", "5 m")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "tree")
                    .append("Features", "50 cm")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "flower")
                    .append("Features", "Red")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "flower")
                    .append("Features", "Blue")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "flower")
                    .append("Features", "Green")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "decoration")
                    .append("Features", "Metal")
                    .append("Quantity", 0)
                    .append("Price", 0));
            stock.add(new Document("type", "decoration")
                    .append("Features", "Plastic")
                    .append("Quantity", 0)
                    .append("Price", 0));

            newFlowerShop = new Document("_id", new ObjectId())
                    .append("name", name)
                    .append("stock", stock);
            InsertOneResult result = collection.insertOne(newFlowerShop);
        } catch (MongoException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return newFlowerShop.getString("_id");
    }

    @Override
    public void updateStock(String idFlowerStore, GardenElements gardenElements) {
        MongoCollection<Document> collection = database.getCollection("FlowerShops");

        // Construye la consulta para encontrar el elemento en el stock
        Document query = new Document("_id", new ObjectId(idFlowerStore))
                .append("stock.type", gardenElements.getClass().getSimpleName())
                .append("stock.Features", gardenElements.getCharacteristics());

        // Construye la actualización del stock
        Document update = new Document("$inc", new Document("stock.$.Quantity", gardenElements.getQuantity()));

        // Ejecuta la actualización
        collection.updateOne(query, update);
    }



    @Override
    public void deleteStock(String idFlowerStore, GardenElements gardenElements) {
        MongoCollection<Document> collection = database.getCollection("FlowerShops");

        // Construye la consulta para encontrar el elemento en el stock
        Document query = new Document("_id", new ObjectId(idFlowerStore))
                .append("stock.type", gardenElements.getClass().getSimpleName())
                .append("stock.Features", gardenElements.getCharacteristics());

        // Ejecuta la eliminación del producto del stock
        collection.updateOne(query, new Document("$pull", new Document("stock", new Document("Features", gardenElements.getCharacteristics()))));

    }

    @Override
    public HashMap<Integer, Date> allTickets(String idFlowerStore) {
        MongoCollection<Document> collection = database.getCollection("Tickets");
        HashMap<Integer, Date> ticketsMap = new HashMap<>();

        Document query = new Document("FlowerStore", idFlowerStore);
        FindIterable<Document> tickets = collection.find(query);

        for (Document ticket : tickets) {
            Integer ticketId = ticket.getInteger("_id");
            Date ticketDate = ticket.getDate("date");
            ticketsMap.put(ticketId, ticketDate);
        }

        return ticketsMap;
    }

    @Override
    public void addTicket(FlowerStore flowerStore, List<GardenElements> gardenElementsList) {
        MongoCollection<Document> collection = database.getCollection("Tickets");
        List<Document> ticketInfoProd = new ArrayList<>();

        for(GardenElements product : gardenElementsList){
            ticketInfoProd.add(new Document("Type",product.getClass().getSimpleName())
                    .append("Features", product.getCharacteristics())
                    .append("Quantity", product.getQuantity())
                    .append("Price", product.getPrice()));
        }
        Document newTicket = new Document("_id", new ObjectId())
                .append("date", new Date())
                .append("FlowerStore", flowerStore.getName())
                .append("products", ticketInfoProd);
        InsertOneResult result = collection.insertOne(newTicket);
    }

    @Override
    public void removeFlowerStore(String flowerStoreId) {
        MongoCollection<Document> collection = database.getCollection("FlowerShops");

        Document query = new Document("_id", new ObjectId(flowerStoreId));

        collection.deleteOne(query);

    }

    @Override
    public double totalPrice(String flowerStoreId) {
        MongoCollection<Document> collection = database.getCollection("FlowerShops");

        Document query = new Document("_id", new ObjectId(flowerStoreId));
        Document flowerShop = collection.find(query).first();

        List<Document> stock = (List<Document>) flowerShop.get("stock");

        double totalMoneyEarned = 0;
        for (Document product : stock) {
            double price = product.getDouble("Price");
            int quantity = product.getInteger("Quantity");
            totalMoneyEarned += price * quantity;
        }

        return totalMoneyEarned;
    }

    @Override
    public void addStock(String idFlowerStore, List<GardenElements> products) {
        MongoCollection<Document> collection = database.getCollection("Stock");

        try {
            for (GardenElements prod : products) {

                Document gardenElementDocument = new Document();
                gardenElementDocument.append("FlowerShopId", idFlowerStore)
                        .append("GardenElementsId", prod.getIdProduct())
                        .append("Quantity", prod.getQuantity())
                        .append("Price", prod.getPrice());

                collection.insertOne(gardenElementDocument);
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}
