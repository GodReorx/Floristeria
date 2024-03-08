package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Flower implements GardenElements {

    private String name;
    private int idProduct;
    private String color;
    private double price;

    public Flower(String name, int idProduct, String color, double price){
        this.name=name;
        this.idProduct=idProduct;
        this.color=color;
        this.price=price;
    }

    @Override
    public String getName() {
        return name;
    }

    public Flower(String characteristic, double price) {
    }

    @Override
    public int getIdProduct() {
        return idProduct;
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
