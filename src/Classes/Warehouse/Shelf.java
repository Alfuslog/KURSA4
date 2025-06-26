package Classes.Warehouse;

import java.util.LinkedList;

public class Shelf {
    private LinkedList<Product> shelfProducts;

    private final int totalLength;
    private final int width;
    private final int maxWeight;

    private int freeLength;
    private int margin_top;
    private int nomberShelf;

    private boolean shelfIsFulled;

    public Shelf(int nomberShelf,int totalLength, int width, int margin, int maxWeight){
        this.nomberShelf = nomberShelf;
        this.totalLength = this.freeLength = totalLength;
        this.width = width;
        if (!(maxWeight == 0)) {
            this.maxWeight = maxWeight;
        }
        else this.maxWeight = 40000; //gramm
        this.margin_top = margin;
        this.shelfIsFulled = false;
    }


    public void changeMarginShelf(int newHeight){
        this.margin_top = margin_top - newHeight;
        if (newHeight <= margin_top/2)
            this.margin_top = margin_top - newHeight;
    }

    public boolean checkShellfIsEmpty(){
        if (shelfProducts.isEmpty() && totalLength == freeLength){
            return true;
        }
        else return false;
    }

//    public void removeShelf(){
//        if(checkShellfIsEmpty()){
//            shelfProducts.remove();
//        }
//    }

    public void addProductToShelf(Product product, int width) {
        if (!shelfIsFulled){
            shelfProducts.add(product);
            this.freeLength -= width;
            }
    }

//    public void extractProduct(){
//
//    }

//    public int getNomberShelf() {
//        return nomberShelf;
//    }


    public int getNomberShelf() {
        return nomberShelf;
    }
}
