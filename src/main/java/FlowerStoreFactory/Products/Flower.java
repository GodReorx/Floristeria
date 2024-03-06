package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Flower implements GardenElements {

    private String color;
    private double price;
    private int quantity;

    public Flower(String color, double price, int quantity){
        this.color=color;
        this.price=price;
        this.quantity=quantity;
    }

    @Override
    public String getCharacteristics() {
        return color;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
