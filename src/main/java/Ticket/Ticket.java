package Ticket;

import FlowerStore.Interfaces.GardenElements;

import java.util.HashMap;
import java.util.Map;

public class Ticket {

    private static Ticket instance;
    private Map<GardenElements, Integer> productos;

    private Ticket() {
        this.productos = new HashMap<>();
    }

    public static Ticket getInstance() {
        if (instance == null) {
            instance = new Tiket();
        }
        return instance;
    }
    public void addProductos (GardenElements producto, int quantity){
       productos.put(producto, quantity);
    }
    public void removeProduct(GardenElements producto, int quantityToRemove) {
        if (productos.containsKey(producto)) {
            int currentQuantity = productos.get(producto);
            int updatedQuantity = currentQuantity - quantityToRemove;

            if (updatedQuantity > 0) {
                productos.put(producto, updatedQuantity);
            } else {
                System.out.println("The quantity to be removed is greater than the current quantity of the product.");
            }
            } else {
                System.out.println("The product is not in inventory.");
            }
        }
    }
    public int getProductosQuantity(GardenElements producto){
        return productos.getOrDefault(producto, 0);
    }
    public HashMap<GardenElements, Integer> getProducts() {
        return new HashMap<>(productos);
    }
    public void printTicket() {
        System.out.println("....Ticket information....");
        for (Map.Entry<GardenElements, Integer> entry : productos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}




