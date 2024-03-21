package Connections.DAO;

import FlowerStore.FlowerStore;
import FlowerStore.Interfaces.GardenElements;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ManagerDAO {

//    private List<GenericDAO> listGenericDAO;
//    public ManagerDAO(List<GenericDAO> listGenericDAO){
//        this.listGenericDAO = listGenericDAO;
//    }
    private final GenericDAO GENERICDAO;
    public ManagerDAO(GenericDAO genericDAO){
        this.GENERICDAO = genericDAO;
    }

    public String createStoreManager(String name){
        return GENERICDAO.createStore(name);
    }
    public void removeFlowerStore(String flowerStoreId){
        GENERICDAO.removeFlowerStore(flowerStoreId);
    }
    public List<FlowerStore> showFlowerStoreManager(){
        return GENERICDAO.showFlowerStore();
    }
    public void addStockManager(String idFlowerStore, List<GardenElements> products){
        GENERICDAO.addStock(idFlowerStore, products);
    }
    public void updateStockManager(String idFlowerStore, GardenElements gardenElements){
        GENERICDAO.updateStock(idFlowerStore, gardenElements);
    }
    public List<GardenElements> showStockManager (FlowerStore flowerStore){
        return GENERICDAO.showStock(flowerStore);
    }
    public void newTicketManager(FlowerStore flowerStore, List<GardenElements> gardenElementsList){
        GENERICDAO.newTicket(flowerStore, gardenElementsList);
    }
    public HashMap<String, Date> showAllTicketsManager(String idFlowerStore){
        return GENERICDAO.showAllTickets(idFlowerStore);
    }
    public double totalPriceManager(String flowerStoreId) {
        return GENERICDAO.totalPrice(flowerStoreId);
    }

}