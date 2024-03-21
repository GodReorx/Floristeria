package FlowerStore.Products;

import FlowerStore.Interfaces.GardenElements;

public class Tree implements GardenElements {

    private int idProduct;
    private int idType;
    private String nameType;
    private String features;
    private double price;
    private int quantity;

    public Tree(int idProduct, int idType, String nameType, String features, double price, int quantity) {
        this.idProduct = idProduct;
        this.idType = idType;
        this.nameType = nameType;
        this.features = features;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int getIdProduct() {
        return idProduct;
    }
    @Override
    public int getIdType() {
        return idType;
    }
    @Override
    public String getNameType() {
        return nameType;
    }
    @Override
    public String getFeatures() {
        return features;
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public int getQuantity() {
        return quantity;
    }
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Tree:" + "->" + " Size: " + features + " Quantity: " + quantity + " Price: " + price;
    }
}
