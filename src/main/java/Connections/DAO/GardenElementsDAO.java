package Connections.DAO;

import FlowerStore.Interfaces.GardenElements;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GardenElementsDAO<T extends GardenElements> implements GenericDAO {
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
