package Classes;


public class LocationProducts {

    private String nameProduct;
    private int article;
    private int countProduct;

    private String nameRack;
    private int numberShelf;

    public LocationProducts(String nameProduct){
        this.nameProduct = nameProduct;
    }

//    public String getNameProductLocation() {
//        return nameProduct;
//    }
//    public void setNameProductLocation(String nameProduct) {
//        this.nameProduct = nameProduct;
//    }
    public int getArticleLocation() {
        return article;
    }
    public void setArticleLocation(int article) {
        article = article;
    }
    public int getNumberShelfLocation() {
        return numberShelf;
    }
    public void setNumberShelfLocation(int numberShelf) {
        this.numberShelf = numberShelf;
    }
    public String getNameRackLocation() {
        return nameRack;
    }
    public void setNameRackLocation(String nameRack) {
        this.nameRack = nameRack;
    }
    public int getCountProductLocation() {
        return countProduct;
    }
    public void setCountProductLocation(int countProduct) {
        this.countProduct = countProduct;
    }
}
