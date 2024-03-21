package Connections.DAO;

import FlowerStore.FlowerStore;
import FlowerStore.Interfaces.GardenElements;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface GenericDAO {
        String createStore(String name);
        void removeFlowerStore(String flowerStoreId);
        List<FlowerStore> showFlowerStore();
        void addStock(String idFlowerStore, List<GardenElements> products);
        void updateStock(String idFlowerStore, GardenElements gardenElements);
        List<GardenElements> showStock(FlowerStore flowerStore);
        void newTicket(FlowerStore flowerStore, List<GardenElements> gardenElementsList);
        HashMap<String, Date> showAllTickets(String idFlowerStore);
        double totalPrice(String flowerStoreId);
}
