package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Tree implements GardenElements {

    private String size;
    private double price;
    private int quantity;

    public Tree(String size, double price, int quantity) {
        this.size=size;
        this.price=price;
        this.quantity=quantity;
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
