package FlowerStore;

import FlowerStore.Interfaces.GardenElements;
import FlowerStore.Products.Decoration;
import FlowerStore.Products.Flower;
import FlowerStore.Products.Tree;

public class FlowerStore {
    private String name;
    private String id;
    public FlowerStore(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
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
    //ToDo: Hay que usar esto para construir nuestros elementos, asi es una fabrica bien hecha
    public GardenElements getElement(int type, String characteristic, double price, int id, int quantity) {
        GardenElements element;
        switch(type) {
            case 1 -> element = new Tree(quantity,id, characteristic, price);
            case 2 -> element = new Flower(quantity,id, characteristic, price);
            case 3 -> element = new Decoration(quantity,id, characteristic, price);
            default -> throw new IllegalStateException("Unexpected value");
        }
        return element;
    }
}

