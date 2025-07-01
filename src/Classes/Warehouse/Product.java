package Classes.Warehouse;

import java.util.Random;

public class Product {
    private LocationProducts location;

    private final int length;
    private final int width;
    private final int height;
    private final int weight; //вес

    private String nameProduct;
    private int countProduct;
    private int article;
    private int numberOnShelf;

    public Product(String nameProduct,int length, int width, int height, int weight, int countProduct) {
        this.nameProduct = nameProduct;
        this.countProduct = countProduct;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;

    }
    public Product(Product other) {
        this.nameProduct = other.nameProduct;
        this.countProduct = other.countProduct;
        this.length = other.length;
        this.width = other.width;
        this.height = other.height;
        this.weight = other.weight;
        this.article = other.article;
        this.numberOnShelf = other.numberOnShelf;
    }

    public int generateArticule(){
        article = new Random().nextInt(100000, 999999);
        return article;
    }

    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
    public int getArticle() {
        return article;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumberOnShelf(){return numberOnShelf;}
    public void setNumberOnShelf(int newNumber){
        this.numberOnShelf = newNumber;
    }
}
