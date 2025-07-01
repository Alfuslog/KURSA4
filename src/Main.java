import Classes.Warehouse.Product;
import Classes.Warehouse.Rack;
import Classes.Warehouse.Storage;
import gui.MainApp;

public class Main {
    public static void main(String[] args) {

        MainApp.show();

        Storage storage = new Storage();
        Rack rack1 = new Rack("R1", 3, 100, 20, 50, 10000);
        storage.addRack(rack1);

        Product apple = new Product("Apple", 10, 5, 5, 500, 10);
        apple.generateArticule();

        int remaining = rack1.addProductToShelf(1, apple);

        System.out.println("не положилось на полку: " + remaining);
        System.out.println("JSON:\n" + rack1.exportProductsToJson());
    }
}
