package Classes.Warehouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Shelf {
    private LinkedList<Product> shelfProducts;
    private final int totalLength;
    private final int width;
    private final int maxWeight;
    private final int nomberShelf;

    // Текущее состояние полки
    private int freeLength;
    private int margin_top;
    private int currentWeight; // Добавлено для контроля веса

    private boolean shelfIsFulled;
    private boolean shelfIsEmpty;

    public Shelf(int nomberShelf, int totalLength, int width, int margin, int maxWeight) {
        this.shelfProducts = new LinkedList<>();
        this.nomberShelf = nomberShelf;
        this.totalLength = totalLength;
        this.freeLength = totalLength;
        this.width = width;
        this.maxWeight = (maxWeight == 0) ? 40000 : maxWeight; // Упрощенная запись
        this.margin_top = margin;
        this.currentWeight = 0;
        this.shelfIsFulled = false;
        this.shelfIsEmpty = true;
    }

    public List<Product> getShelfProducts() {
        return new ArrayList<>(shelfProducts);
    }

    // ИСПРАВЛЕНО: метод изменения высоты полки
    public void changeMarginShelf(int newHeight) {
        if (newHeight > 0 && newHeight <= margin_top) {
            this.margin_top = newHeight;
        }
    }

    public boolean checkShelfIsEmpty() {
        return shelfProducts.isEmpty() && totalLength == freeLength;
    }

    // ИСПРАВЛЕНО: логика расчета количества товаров
    public int getCountInLength(Product product) {
        if (product.getHeight() <= 0 || product.getWidth() <= 0) {
            return product.getCountProduct();
        }

        // Сколько поместится в высоту
        int cntInHeight = margin_top / product.getHeight();
        // Сколько поместится в длину на одном уровне
        int cntInLength = freeLength / product.getWidth();
        // Общее количество, которое поместится
        int totalCanFit = cntInHeight * cntInLength;

        // Если поместится все - возвращаем 0, иначе остаток
        return (totalCanFit >= product.getCountProduct()) ? 0 : (product.getCountProduct() - totalCanFit);
    }

    // ИСПРАВЛЕНО: проверка по ширине, а не длине
    public boolean canProductFit(Product product) {
        // Проверяем размеры
        if (product.getWidth() > freeLength || product.getHeight() > margin_top) {
            return false;
        }
        // Проверяем вес
        int productTotalWeight = product.getWeight() * product.getCountProduct();
        return (currentWeight + productTotalWeight) <= maxWeight;
    }

    public int addProductToShelf(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }

        // Проверяем, помещается ли продукт
        if (!canProductFit(product)) {
            return product.getCountProduct(); // Ничего не поместилось
        }

        // Проверяем, сколько продуктов не помещается
        int remainingProducts = getCountInLength(product);

        if (remainingProducts > 0) {
            // Помещаем только часть продуктов
            int productsToAdd = product.getCountProduct() - remainingProducts;
            Product partialProduct = new Product(product);
            partialProduct.setCountProduct(productsToAdd);

            shelfProducts.add(partialProduct);
            updateShelfState(partialProduct, true);

            return remainingProducts;
        } else {
            // Помещаем все продукты
            shelfProducts.add(new Product(product)); // Создаем копию
            updateShelfState(product, true);
            return 0;
        }
    }

    public boolean extractProduct(int productIndex) {
        if (productIndex < 0 || productIndex >= shelfProducts.size()) {
            return false;
        }

        Product removedProduct = shelfProducts.remove(productIndex);
        updateShelfState(removedProduct, false);
        return true;
    }

    // Новый метод для удаления по артикулу
    public boolean extractProductByArticle(int article) {
        for (int i = 0; i < shelfProducts.size(); i++) {
            if (shelfProducts.get(i).getArticle() == article) {
                return extractProduct(i);
            }
        }
        return false;
    }

    // Вспомогательный метод для обновления состояния полки
    private void updateShelfState(Product product, boolean isAdding) {
        int lengthChange = product.getWidth() * product.getCountProduct();
        int weightChange = product.getWeight() * product.getCountProduct();

        if (isAdding) {
            this.freeLength -= lengthChange;
            this.currentWeight += weightChange;
            this.shelfIsEmpty = false;
        } else {
            this.freeLength += lengthChange;
            this.currentWeight -= weightChange;
            this.shelfIsEmpty = shelfProducts.isEmpty();
        }

        // Обновляем статус заполненности
        this.shelfIsFulled = (freeLength < 10); // Условно считаем полной, если остается меньше 10 единиц
    }

    // Геттеры
    public int getNomberShelf() {
        return nomberShelf;
    }

    public int getFreeLength() {
        return freeLength;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean isShelfEmpty() {
        return shelfIsEmpty;
    }

    public boolean isShelfFull() {
        return shelfIsFulled;
    }

    // Метод для получения информации о полке
    public String getShelfInfo() {
        return String.format("Полка %d: Товаров: %d, Свободно: %d/%d, Вес: %d/%d",
                nomberShelf, shelfProducts.size(), freeLength, totalLength, currentWeight, maxWeight);
    }
}