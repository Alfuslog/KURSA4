package gui;

import Classes.Warehouse.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WarehouseController {

    @FXML private ListView<String> productList;
    @FXML private TextField nameField, lengthField, widthField, heightField, weightField, countField, shelfField;

    private Rack rack = new Rack("Rack1", 3, 100, 20, 50, 10000);
    private ObservableList<String> productItems = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        productList.setItems(productItems);
        refreshProductList();
    }

    private void refreshProductList() {
        productItems.clear();
        for (LocationProducts p : rack.getAllProducts()) {
            productItems.add(p.getNameProduct() + " | Арт: " + p.getArticle() + " | Кол-во: " + p.getCountProduct());
        }
    }

    @FXML
    private void onAddProduct() {
        try {
            String name = nameField.getText();
            int len = Integer.parseInt(lengthField.getText());
            int w = Integer.parseInt(widthField.getText());
            int h = Integer.parseInt(heightField.getText());
            int weight = Integer.parseInt(weightField.getText());
            int count = Integer.parseInt(countField.getText());
            int shelfNum = Integer.parseInt(shelfField.getText());

            Product p = new Product(name, len, w, h, weight, count);
            p.generateArticule();
            int remaining = rack.addProductToShelf(shelfNum, p);
            if (remaining > 0) {
                showAlert("Осталось непринято: " + remaining, Alert.AlertType.WARNING);
            }

            refreshProductList();
        } catch (Exception e) {
            showAlert("Ошибка добавления: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
