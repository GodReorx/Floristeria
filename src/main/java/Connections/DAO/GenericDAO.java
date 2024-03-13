package Connections.DAO;

import FlowerStore.FlowerStore;
import FlowerStore.Interfaces.GardenElements;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface GenericDAO {
        HashMap<Integer,String> showFlowerStore();
        GardenElements findById(int id);
        List<GardenElements> allGardenElements(int idFlowerStore);
        int createStore(String name);
        void addStock(int idFlowerStore, List<GardenElements> products);
        void updateStock(int idProduct, int idFlowerStore, int quantity, double price);
        void deleteStock(int idFlowerStore, int idProduct, int quantity);
        HashMap<Integer, Date> allTickets(int idFlowerStore);
        void addTicket(FlowerStore flowerStore, List<GardenElements> gardenElementsList);
}
