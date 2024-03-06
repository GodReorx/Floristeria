package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Trees implements GardenElements {

    private double size;

    public Trees(double size){
        this.size=size;
    }

    @Override
    public void displayCharacteristics() {
         System.out.println("The size of trees is: " + size);
    }

    @Override
    public void getPrice(double price) {
        System.out.println("The price of trees is: " + price);
    }
}
