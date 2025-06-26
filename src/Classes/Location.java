package Classes;


public class Location {

    private String nameProduct;
    private int article;
    private int countProduct;

    private String nameRack;
    private int numberShelf;

    public Location( ){
    }

    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public int getArticle() {
        return article;
    }
    public void setArticle(int article) {
        article = article;
    }
    public int getNumberShelf() {
        return numberShelf;
    }
    public void setNumberShelf(int numberShelf) {
        this.numberShelf = numberShelf;
    }
    public String getNameRack() {
        return nameRack;
    }
    public void setNameRack(String nameRack) {
        this.nameRack = nameRack;
    }
    public int getCountProduct() {
        return countProduct;
    }
    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
}
