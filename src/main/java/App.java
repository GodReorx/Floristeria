import Connections.DAO.ManagerDAO;
import Connections.MongoDB.GardenElementsMongoDB;
import Exceptions.NotValidOptionException;
import Exceptions.InputControl;
import FlowerStore.FlowerStore;
import FlowerStore.Interfaces.GardenElements;
import Ticket.ShopCart;
import java.util.*;



public class App {
    private static FlowerStore flowerStore;
    private static ShopCart shopCart = ShopCart.getInstance();
    private static final ManagerDAO MANAGER_DAO = new ManagerDAO(new GardenElementsMongoDB());

//    private static final ArrayList<GenericDAO> LIST_GENERIC_DAO = new ArrayList(){{
//        add(new GardenElementsMySQL());
//        add(new GardenElementsMongoDB());}};
//    private static final ManagerDAO MANAGER_DAO_LIST = new ManagerDAO(LIST_GENERIC_DAO);



    public static void runApp(){
        int opc = 0;
        System.out.println("Here are the available flower stores");
        System.out.println("0. Create or delete a FlowerStore");
        List<FlowerStore> listFlowerStores = showFlowerStores();
        if (listFlowerStores.isEmpty()) {
            System.out.println("You have not created any FlowerStore");
        }
        boolean validId = false;
        while (!validId) {
            try {
                opc = InputControl.requestIntData("Please indicate the ID of the flower shop you want to work with:") - 1;
                if (opc >= 0 && opc < listFlowerStores.size()) {
                    flowerStore = new FlowerStore(listFlowerStores.get(opc).getId(), listFlowerStores.get(opc).getName());
                    runProgram();
                    validId = true;
                }else if(opc == -1){
                    addDeleteFlowerShopMenu();
                    validId = true;
                }else {
                    throw new NotValidOptionException("The FlowerStore ID entered is not valid.");
                }
            } catch (NotValidOptionException e) {
                System.out.println(e.getMessage());
            }
        }
        if(opc == -1){
            runApp();
        }
    }
    private static void addDeleteFlowerShopMenu (){
        int opc;
        do {
            opc = InputControl.requestIntData("Choose an option:\n" +
                    "0. Return to choose a FlowerStore\n" +
                    "1. Create a new FlowerStore\n" +
                    "2. Remove a FlowerStore");
            switch (opc) {
                case 0:
                    System.out.println("Returning to main menu");
                    break;
                case 1:
                    createFlowerStore();
                    break;
                case 2:
                    removeFlowerStore();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option: " + opc);
            }
        }while(opc != 0);
    }
    private static void runProgram() {
        System.out.println("Working with FlowerStore: " + flowerStore.getName());
        boolean seguirBucle;
        do {
            seguirBucle = menu(InputControl.requestIntData("Indicate number option:\n"
                    + "0.Exit\n"
                    + "1.Insert a tree, flower or decoration to stock\n"
                    + "2.Remove a tree, flower or decoration from stock\n"
                    + "3.Print the stock of all the trees, flowers and decorations\n"
                    + "4.Print the stock with quantities\n"
                    + "5.Print total value of flowerstore\n"
                    + "6.Create tickets with multiples objects\n"
                    + "7.Show a list of old purchases\n"
                    + "8.View the total money earned from all sales\n"));
        }while(seguirBucle);
    }
    private static boolean menu(int opcion) {
        boolean seguirBucle = true;
        switch (opcion){
            case 0:
                seguirBucle = false;
                System.out.println("You have exited the menu");
                break;
            case 1: insertProduct();
                break;
            case 2: removeProduct();
                break;
            case 3: printStock(false);
                break;
            case 4: printStock(true);
                break;
            case 5: valueTotal();
                break;
            case 6: createTicket();
                break;
            case 7: oldPurchasesList();
                break;
            case 8: totalMoneyEarned();
                break;
            default:
                System.out.println("Option no valid!");
        }
        return seguirBucle;
    }
    private static void insertProduct(){
        List<GardenElements> listaElements = showAllProducts();
        try {
            int opc = InputControl.requestIntData("Indicate the product you want to update the stock:") -1;
            if (opc >= 0 && opc < listaElements.size()) {
                listaElements.get(opc).setQuantity(InputControl.requestIntData("Indicates the amount to add:") + listaElements.get(opc).getQuantity());
                double price = InputControl.requestDoubleData("Enter the sale price (enter 0 to not modify it)");
                if (price > 0) {
                    listaElements.get(opc).setPrice(price);
                }
                MANAGER_DAO.updateStockManager(flowerStore.getId(), listaElements.get(opc));
                System.out.println(listaElements.get(opc).getQuantity() + " " + listaElements.get(opc).getFeatures() + " " + listaElements.get(opc).getNameType() + "have been added to the " + flowerStore.getName());
                waitForContinue();

            } else {
                throw new NotValidOptionException("Incorrect option!");
            }
        } catch (NotValidOptionException e){
            System.out.println(e.getMessage());
        }
    }
    private static void removeProduct(){
        List<GardenElements> listaElements = showAllProducts();
        try {
            int opc = InputControl.requestIntData("Indicate the product you want to remove the stock:") - 1;
            if (opc >= 0 && opc < listaElements.size()) {
                int stockRemove = InputControl.requestIntData("Indicates the amount to remove:");
                if (listaElements.get(opc).getQuantity() >= stockRemove) {
                    listaElements.get(opc).setQuantity(listaElements.get(opc).getQuantity() - stockRemove);
                    MANAGER_DAO.updateStockManager(flowerStore.getId(), listaElements.get(opc));
                    System.out.println(stockRemove + " products have been deleted from the " + flowerStore.getName() + " florist's stock.");
                    waitForContinue();
                } else {
                    throw new NotValidOptionException("You can't delete more quantity than the existing stock");
                }
            } else {
                throw new NotValidOptionException("Incorrect option!");
            }
        } catch (NotValidOptionException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static void printStock(boolean includeQuantity) {
        List<GardenElements> listaStock = MANAGER_DAO.showStockManager(flowerStore);
        for (GardenElements element : listaStock) {
            System.out.print(element.getNameType() + ": " + element.getFeatures());
            if (includeQuantity) {
                System.out.print(" " + element.getQuantity());
            }
            System.out.println();
        }
        waitForContinue();
    }
    private static void valueTotal(){
        List<GardenElements> listaStock = MANAGER_DAO.showStockManager(flowerStore);
        double total = listaStock.stream().mapToDouble(prod -> prod.getPrice()* prod.getQuantity()).sum();
        System.out.println("The value total of Flower store " + flowerStore.getName() + " is: " + total);
        waitForContinue();
    }
    private static void createTicket() {
        Scanner entrada = new Scanner(System.in);
        List<GardenElements> gardenElementsList = showAllProducts();
        boolean addInformation = true;
        while (addInformation) {
            int productId = -1;
            while(productId < 0 || productId >= gardenElementsList.size()){
                productId = InputControl.requestIntData("Enter product ID:") - 1;
            }
            int quantity = InputControl.requestIntData("Enter quantity:");
            if(quantity <= gardenElementsList.get(productId).getQuantity()){
                GardenElements temp = gardenElementsList.get(productId);
                GardenElements ticketGarden = flowerStore.createElement(temp.getIdProduct(), temp.getIdType(), temp.getNameType(), temp.getFeatures(), temp.getPrice(), temp.getQuantity());
                shopCart.addProductos(ticketGarden,quantity);
                gardenElementsList.get(productId).setQuantity(gardenElementsList.get(productId).getQuantity()-quantity);
                MANAGER_DAO.updateStockManager(flowerStore.getId(),gardenElementsList.get(productId));
                System.out.println("Do you want to add more products to the ticket? (yes/no)");
                String answer = entrada.next();
                addInformation = answer.equalsIgnoreCase("yes");
            }
        }
        shopCart.printTicket();
        MANAGER_DAO.newTicketManager(flowerStore, shopCart.getProducts());
        System.out.println("Ticket created successfully.");
        waitForContinue();
    }
    private static void oldPurchasesList(){
        HashMap<String, Date> tickets = MANAGER_DAO.showAllTicketsManager(flowerStore.getId());
        if(tickets.isEmpty()){
            System.out.println("Don't have any ticket created");
        }else {
            System.out.println("Here you have the all tickets:");
            for (String ticketId : tickets.keySet()) {
                System.out.println("Ticket ID: " + ticketId + ", Date: " + tickets.get(ticketId));
            }
        }
        waitForContinue();
    }
    private static void totalMoneyEarned(){
        double totalMoneyEarned = MANAGER_DAO.totalPriceManager(flowerStore.getId());
        System.out.println("Total Money Earned from all sales: " + totalMoneyEarned);
        waitForContinue();
    }
    private static void createFlowerStore(){
        String nameStore = InputControl.askNameOnlyLetters("FlowerStore name: ");
        String id;
        id = MANAGER_DAO.createStoreManager(nameStore);
        flowerStore = new FlowerStore(id, nameStore);
        List<GardenElements> products = MANAGER_DAO.showStockManager(flowerStore);
        MANAGER_DAO.addStockManager(id, products);
        System.out.println("FlowerStore " + nameStore + " is created" );
        waitForContinue();
    }
    private static void removeFlowerStore(){
        List<FlowerStore> listFlowerStores = showFlowerStores();
        try {
            int opc = InputControl.requestIntData("Select the florist to delete") - 1;
            if(opc >= 0 && opc < listFlowerStores.size()) {
                MANAGER_DAO.removeFlowerStore(listFlowerStores.get(opc).getId());
                System.out.println("Flowershop eliminated.");
                waitForContinue();
            } else if (opc == 0){
                System.out.println("Empty flower store list");
            } else {
                throw new NotValidOptionException("The FlowerStore ID entered is not valid.");
            }
        } catch (NotValidOptionException e) {
            System.out.println(e.getMessage());
        }

    }
    private static List<FlowerStore> showFlowerStores(){
        List<FlowerStore> listFlowerStores = MANAGER_DAO.showFlowerStoreManager();
        for(int i = 0; i < listFlowerStores.size(); i++){
            System.out.println((i+1) + ". " + listFlowerStores.get(i).getName());
        }
        return listFlowerStores;

    }
    private static List<GardenElements> showAllProducts(){
        List<GardenElements> listaElements = MANAGER_DAO.showStockManager(flowerStore);
        System.out.println("We have these products: ");
        for(int i = 0; i < listaElements.size(); i++){
            System.out.println((i+1) + ". " + listaElements.get(i).toString());
        }
        return listaElements;
    }
    private static void waitForContinue(){
        Scanner sc = new Scanner(System.in);
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nPress any key to continue...");
        sc.nextLine();
        System.out.println("\n\n\n\n");
    }



}
