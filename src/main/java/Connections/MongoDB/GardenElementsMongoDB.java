package Connections.MongoDB;

import Connections.DAO.Constants;
import Connections.DAO.GenericDAO;
import FlowerStore.FlowerStore;
import FlowerStore.Interfaces.GardenElements;
import FlowerStore.Products.Decoration;
import FlowerStore.Products.Flower;
import FlowerStore.Products.Tree;
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
        List<FlowerStore> flowerStores = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("FlowerShops");
        FindIterable<Document> documents = collection.find();

        for (Document doc : documents) {
            FlowerStore flowerStore = new FlowerStore("_id", "Name");
            flowerStore.setId(doc.getObjectId("_id").toString());
            flowerStore.setName(doc.getString("name"));

            flowerStores.add(flowerStore);
        }

        return flowerStores;
    }


    @Override
    public List<GardenElements> allGardenElements(String idFlowerStore) {
        List<GardenElements> elements = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("FlowerShops");

        Document query = new Document("_id", new ObjectId(idFlowerStore));
        FindIterable<Document> results = collection.find(query);


        for (Document doc : results) {
            List<Document> stock = (List<Document>) doc.get("stock");


            for (Document item : stock) {
                String type = item.getString("type");
                String features = item.getString("Features");
                int quantity = item.getInteger("Quantity");
                double price = item.getDouble("Price");

                switch (type) {
                    case "tree":
                        elements.add(new Tree(quantity, 0, features, price));
                        break;
                    case "flower":
                        elements.add(new Flower(quantity,0, features, price));
                        break;
                    case "decoration":
                        elements.add(new Decoration(quantity,0, features, price));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid type: " + type);
                }
            }
        }

        return elements;
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
        return null;
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

    }

    @Override
    public double totalPrice(String flowerStoreId) {
        return 0;
    }

    @Override
    public void addStock(String idFlowerStore, List<GardenElements> products) {

    }

}
