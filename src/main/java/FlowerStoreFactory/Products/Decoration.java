package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Decoration implements GardenElements {

    private String typeMaterial;
    private double price;

    public Decoration(String typeMaterial, double price){
        this.typeMaterial=typeMaterial;
        this.price=price;
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
