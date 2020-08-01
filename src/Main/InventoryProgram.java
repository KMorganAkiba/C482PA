package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryProgram extends Application{

    public void start(Stage primarystage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ViewController/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primarystage.setTitle("Inventory Management");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args){

        OutSource part1 = new OutSource(1,"Turbo", 999.99, 2,1,8,"HKS");
        OutSource part2 = new OutSource(2,"Id1000",450.00,6,1,9,"Injector Dynamics");
        InHouse part3 = new InHouse(3,"FC3S Dual MC Mount", 79.99, 3,1, 6,17);
        Product product1 = new Product(1,"Caliper Kit", 399.99, 4,1,9);
        Product product2 = new Product(2,"Caliper Kit (Big)", 599.99, 4,1,9);
        Product product3 = new Product(3,"Caliper Kit (Bigger)", 799.99, 4,1,9);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart((part3));
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        launch(args);
    }
}
