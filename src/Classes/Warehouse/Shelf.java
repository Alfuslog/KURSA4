package Classes.Warehouse;

import java.util.LinkedList;
import java.lang.Math;


public class Shelf {
    private LinkedList<Product> shelfProducts;

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

    public int getCountInLength(int heightProduct, int widthProduct, int cntProducts){
        int cntInHeight = margin_top/heightProduct;
        int cntInLength = freeLength/widthProduct;

        int totalSpaceCnt = cntInHeight*cntInLength;

        boolean CapacityAllProducts = totalSpaceCnt >= cntProducts;
        int cntInLengthAndHeight;
        if(cntProducts > cntInHeight){
                cntInLengthAndHeight = (int) Math.ceil((double) cntProducts/cntInHeight);
            }
            else cntInLengthAndHeight = cntProducts;


        return totalSpaceCnt/cntProducts;
    }

    public int getCountCanPutInShelf(int heightProduct, int widthProduct, int cntProducts){ //возвращаем не влезший остаток  остаток
        int cntInHeight = margin_top/heightProduct;
        int cntInWidth = freeLength/widthProduct;


        int totalSpaceCnt = cntInHeight*cntInWidth;

        int capacityInSpace = totalSpaceCnt/cntProducts;
        int ostatok = cntProducts - capacityInSpace;
        if (ostatok > 0){
            return ostatok;
        }
        else return 0;
    }

    //public int getCntInWidth()

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

    public int addProductToShelf(Product product, int cntProducts) {
        if (canProductPut(product.getWidth()) && checkOfCapacityProducts()){
            if (shelfIsEmpty == true) {shelfIsEmpty = false;}

            int occupiedWidth =
            shelfProducts.add(product);
            this.freeLength -= occupiedWidth;
            }


    }

    public void extractProduct(int numberProduct){
        numberProduct -= 1;

    }

//    public int getNomberShelf() {
//        return nomberShelf;
//    }


    public int getNomberShelf() {
        return nomberShelf;
    }
}
