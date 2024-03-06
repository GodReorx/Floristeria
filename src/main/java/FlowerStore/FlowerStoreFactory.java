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

    @Override
    public String toString() {
        return "FlowerStoreFactory{" +
                "name='" + name + '\'' +
                '}';
    }

    public GardenElements getElement(String type, String characteristic, double price) {
        GardenElements element = null;
        switch(type.toUpperCase()) {
            case "FLOWER" -> element = new Flower(characteristic, price);
            case "DECORATION" -> element = new Decoration(characteristic, price);
            case "TREES" -> element = new Tree(characteristic, price);
            default -> throw new IllegalStateException("Unexpected value");
        }
        return element;
    }
}

