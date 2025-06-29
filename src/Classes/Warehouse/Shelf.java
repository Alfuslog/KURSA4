package Classes.Warehouse;

import java.util.LinkedList;

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


    public void changeMarginShelf(int newHeight){
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

    public boolean checkOfCapacityManyProducts(int heightProduct, int widthProduct, int cntProducts){
        int cntInHeight = margin_top/heightProduct;
        int cntInWidth = freeLength/widthProduct;
        int totalSpaceCnt = cntInHeight*cntInWidth;

        if (totalSpaceCnt >= cntProducts) return true;
        else return false;
    }
    public int checkOfCountInSpace(int heightProduct, int widthProduct, int cntProducts){
        int cntInHeight = margin_top/heightProduct;
        int cntInWidth = freeLength/widthProduct;
        int totalSpaceCnt = cntInHeight*cntInWidth;

        return totalSpaceCnt/cntProducts;

    }


    public boolean checkShelfIsFulled(){
        if ()
    }


//    public void removeShelf(){
//        if(checkShellfIsEmpty()){
//            shelfProducts.remove();
//        }
//    }

    public void addProductToShelf(Product product, int width) {
        if (!shelfIsFulled){
            shelfIsEmpty = false;
            shelfProducts.add(product);
            this.freeLength -= width;
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
