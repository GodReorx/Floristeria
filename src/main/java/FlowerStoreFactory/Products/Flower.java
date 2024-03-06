package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Flower implements GardenElements {

    private String color;

    public Flower(String color){
     this.color=color;;
    }

    @Override
    public void displayCharacteristics() {
        System.out.println("The flower color is: " + color);
    }

    @Override
    public void getPrice(double price) {
        System.out.println("The price of flower is: " + price);
    }
}
