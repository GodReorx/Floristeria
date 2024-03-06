package FlowerStoreFactory.Products;

import FlowerStore.Interfaces.GardenElements;

public class Decoration implements GardenElements {

    private String typeMaterial;
    public Decoration(String typeMaterial){
        this.typeMaterial=typeMaterial;
    }

    @Override
    public void displayCharacteristics() {
        System.out.println("The type of material is: " + typeMaterial);
    }

    @Override
    public void getPrice(double price) {
        System.out.println("The price of material is: " + price);
    }
}
