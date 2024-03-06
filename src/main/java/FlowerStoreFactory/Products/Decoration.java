package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Decoration implements GardenElements {

    private String typeMaterial;
    private double price;
    private int quantity;
    public Decoration(String typeMaterial, double price, int quantity){
        this.typeMaterial=typeMaterial;
        this.price=price;
        this.quantity=quantity;
    }

    @Override
    public String getCharacteristics() {
        return typeMaterial;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
