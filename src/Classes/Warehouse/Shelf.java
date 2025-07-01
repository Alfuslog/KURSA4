package Classes.Warehouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Shelf {
    private LinkedList<Product> shelfProducts;
    private Product product;
    private LocationProducts lProducts;
    public List<Product> getShelfProducts() {
        return new ArrayList<>(shelfProducts); // Возвращаем копию для безопасности
    }

    //дшв
    private final int totalLength;
    private final int width;
    private final int maxWeight;

    // отступы для полок
    private int freeLength;
    private int margin_top;
    private int nomberShelf;

    private boolean shelfIsFulled;
    private boolean shelfIsEmpty;


    public Shelf(int nomberShelf,int totalLength, int width, int margin, int maxWeight){
        this.shelfProducts = new LinkedList<>();
        this.nomberShelf = nomberShelf;
        this.totalLength = this.freeLength = totalLength;
        this.width = width;
        if (!(maxWeight == 0)) { // ноль для значения по умолчнию
            this.maxWeight = maxWeight;
        }
        else this.maxWeight = 40000; //gramm
        this.margin_top = margin;
        this.shelfIsFulled = false;
        this.shelfIsEmpty = true;
    }


    public void changeMarginShelf(int newHeight){  // меняем высоту полки
        this.margin_top = margin_top - newHeight;
        if (newHeight <= margin_top/2)
            this.margin_top = margin_top - newHeight;
    }

    public boolean checkShellfIsEmpty(){ //проверка для удаления
        if (shelfProducts.isEmpty() && totalLength == freeLength){
            return true;
        }
        else return false;
    }

    public int getCountInLength(Product product){ // узнаём, помещается ли наше кол-во на полку, если нет, то ретурн остаток, или 0
        if (product.getHeight() == 0 || product.getWidth() == 0) {
            return product.getCountProduct();
        }


        int cntInHeight = margin_top/product.getHeight();
        int cntInLength = freeLength/product.getWidth();
        int cntProducts = product.getCountProduct();

        int totalSpaceCnt = cntInHeight*cntInLength;
        //boolean CapacityAllProducts = totalSpaceCnt >= cntProducts;
        //int cntInLengthAndHeight;
        //
        //int ostatok = cntProducts - totalSpaceCnt;
        return (totalSpaceCnt >= cntProducts) ? 0 : (cntProducts - totalSpaceCnt);
    }

    public boolean canProductPut(int heightProduct){
        if (heightProduct>freeLength) {
            return false;
        } else return true;
    }

//    public void removeShelf(){
//        if(checkShellfIsEmpty()){
//            shelfProducts.remove();
//        }
//    }

    public int addProductToShelf(Product product) {
        if (product == null) {
            return -1; // или выбросить исключение
        }

        // Проверяем, помещается ли продукт по длине
        if (!canProductPut(product.getWidth())) {
            return product.getCountProduct(); // не помещается - возвращаем всё количество
        }

        // Проверяем, сколько продуктов не помещается (остаток)
        int remainingProducts = getCountInLength(product);

        if (remainingProducts > 0) {
            // Помещаем только часть продуктов (сколько влезает)
            int productsToAdd = product.getCountProduct() - remainingProducts;
            Product partialProduct = new Product(product); // нужен конструктор копирования
            partialProduct.setCountProduct(productsToAdd);
            shelfProducts.add(partialProduct);
            this.freeLength -= product.getWidth() * productsToAdd;
            shelfIsEmpty = false;
            return remainingProducts;
        } else {
            // Помещаем все продукты
            shelfProducts.add(product);
            this.freeLength -= product.getWidth() * product.getCountProduct();
            shelfIsEmpty = false;
            return 0; // всё поместилось
        }
    }

    public void extractProduct(int numberProduct) {
        if (numberProduct < 1 || shelfProducts == null || shelfProducts.isEmpty()) {
            return; // некорректный номер или пустая полка
        }

        int index = numberProduct - 1; // преобразуем номер в индекс (если нумерация с 1)

        if (index >= 0 && index < shelfProducts.size()) {
            Product removedProduct = shelfProducts.remove(index);
            this.freeLength += removedProduct.getWidth() * removedProduct.getCountProduct();

            // Если полка опустела, устанавливаем флаг
            if (shelfProducts.isEmpty()) {
                shelfIsEmpty = true;
            }
        }
    }

    public int getNomberShelf() {
        return nomberShelf;
    }
}
