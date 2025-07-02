package gui;

import Classes.Warehouse.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WarehouseController {

    @FXML private ListView<String> productList;
    @FXML private TextField nameField, lengthField, widthField, heightField, weightField, countField, shelfField;
    @FXML private Button addButton, removeButton, exportButton, clearButton;
    @FXML private Label statusLabel;
    @FXML private ComboBox<String> rackSelector;

    private Storage storage = new Storage();
    private ObservableList<String> productItems = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        System.out.println("Инициализация контроллера...");

        // Инициализация хранилища
        initializeStorage();

        // Настройка ListView
        productList.setItems(productItems);

        // Настройка селектора стеллажей
        setupRackSelector();

        refreshProductList();
        updateStatus("Система готова к работе");
    }

    private void initializeStorage() {
        // Создаем стеллажи
        Rack rack1 = new Rack("A1", 5, 3000, 600, 500, 40000);
        Rack rack2 = new Rack("A2", 5, 3000, 600, 500, 40000);

        storage.addRack(rack1);
        storage.addRack(rack2);

        System.out.println("Создано стеллажей: " + storage.getRacks().size());
    }

    private void setupRackSelector() {
        ObservableList<String> rackIds = FXCollections.observableArrayList();
        for (Rack rack : storage.getRacks()) {
            rackIds.add(rack.getIdRack());
        }
        rackSelector.setItems(rackIds);
        if (!rackIds.isEmpty()) {
            rackSelector.getSelectionModel().selectFirst();
        }
    }

    private void refreshProductList() {
        productItems.clear();

        for (Rack rack : storage.getRacks()) {
            for (LocationProducts location : rack.getAllProducts()) {
                String displayText = String.format("%s | Арт:%d | Кол:%d | %s-%d",
                        location.getNameProduct(),
                        location.getArticle(),
                        location.getCountProduct(),
                        location.getNameRack(),
                        location.getNumberShelf());

                productItems.add(displayText);
            }
        }

        updateStatus(String.format("Товаров на складе: %d", productItems.size()));
    }

    @FXML
    private void onAddProduct() {
        try {
            // Проверяем обязательные поля
            if (nameField.getText().trim().isEmpty()) {
                showAlert("Введите название товара", Alert.AlertType.WARNING);
                return;
            }

            if (shelfField.getText().trim().isEmpty()) {
                showAlert("Введите номер полки", Alert.AlertType.WARNING);
                return;
            }

            String name = nameField.getText().trim();
            int len = parseIntOrDefault(lengthField.getText(), 10);
            int width = parseIntOrDefault(widthField.getText(), 5);
            int height = parseIntOrDefault(heightField.getText(), 5);
            int weight = parseIntOrDefault(weightField.getText(), 100);
            int count = parseIntOrDefault(countField.getText(), 1);
            int shelfNum = Integer.parseInt(shelfField.getText());

            // Получаем выбранный стеллаж
            String selectedRackId = rackSelector.getValue();
            if (selectedRackId == null) {
                showAlert("Выберите стеллаж", Alert.AlertType.WARNING);
                return;
            }

            Rack selectedRack = storage.getRackById(selectedRackId);
            if (selectedRack == null) {
                showAlert("Стеллаж не найден: " + selectedRackId, Alert.AlertType.ERROR);
                return;
            }

            Product product = new Product(name, len, width, height, weight, count);
            product.generateArticule();

            int remaining = selectedRack.addProductToShelf(shelfNum, product);

            if (remaining > 0) {
                showAlert(String.format("Добавлено частично. Не поместилось: %d из %d",
                        remaining, count), Alert.AlertType.WARNING);
            } else {
                updateStatus(String.format("Товар '%s' добавлен на полку %d стеллажа %s",
                        name, shelfNum, selectedRackId));
            }

            refreshProductList();
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Проверьте числовые поля (полка обязательна)", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Ошибка: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void onRemoveProduct() {
        int selectedIndex = productList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            showAlert("Удаление товаров пока не реализовано", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Выберите товар для удаления", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onExportData() {
        try {
            StringBuilder allData = new StringBuilder();
            allData.append("=== ЭКСПОРТ ДАННЫХ СКЛАДА ===\n\n");

            for (Rack rack : storage.getRacks()) {
                allData.append("Стеллаж: ").append(rack.getIdRack()).append("\n");
                allData.append(rack.exportProductsToJson()).append("\n");
                allData.append("-".repeat(50)).append("\n");
            }

            System.out.println(allData.toString());
            updateStatus("Данные экспортированы в консоль");
            showAlert("Данные экспортированы в консоль", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            showAlert("Ошибка экспорта: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClearFields() {
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        lengthField.clear();
        widthField.clear();
        heightField.clear();
        weightField.clear();
        countField.clear();
        shelfField.clear();
    }

    private void updateStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText(message);
        }
        System.out.println("Status: " + message);
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Склад");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private int parseIntOrDefault(String text, int defaultValue) {
        try {
            return text.trim().isEmpty() ? defaultValue : Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}