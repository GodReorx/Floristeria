package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Decoration implements GardenElements {

    private int idProduct;
    private String typeMaterial;
    private double price;

    public Decoration(int idProduct, String typeMaterial, double price){
        this.idProduct=idProduct;
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
