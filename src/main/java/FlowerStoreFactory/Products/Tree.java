package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Tree implements GardenElements {

    private int idProduct;
    private String size;
    private double price;

    public Tree(int idProduct, String size, double price) {
        this.idProduct=idProduct;
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
