package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Flower implements GardenElements {

    private int idProduct;
    private String color;
    private double price;

    public Flower(int idProduct, String color, double price){
        this.idProduct=idProduct;
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
