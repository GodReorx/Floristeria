package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Tree implements GardenElements {

    private String size;
    private double price;

    public Tree(String size, double price) {
        this.size=size;
        this.price=price;
    }


    @Override
    public String getCharacteristics() {
        return size;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
