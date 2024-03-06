package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Flower implements GardenElements {

    private String color;
    private double price;

    public Flower(String color, double price){
        this.color=color;
        this.price=price;
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
