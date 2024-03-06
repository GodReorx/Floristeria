package FlowerStore;

import FlowerStore.Interfaces.GardenElements;
import FlowerStoreFactory.Products.Decoration;
import FlowerStoreFactory.Products.Flower;
import FlowerStoreFactory.Products.Tree;

public class FlowerStoreFactory {
    private String name;
    public FlowerStoreFactory(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public GardenElements getElement(String type, String characteristic, double price, int quantity) {
        GardenElements element = null;
        switch(type.toUpperCase()) {
            case "FLOWER" -> element = new Flower(characteristic, price, quantity);
            case "DECORATION" -> element = new Decoration(characteristic, price, quantity);
            case "TREES" -> element = new Tree(characteristic, price, quantity);
            default -> throw new IllegalStateException("Unexpected value");
        }
        return element;
    }
}

