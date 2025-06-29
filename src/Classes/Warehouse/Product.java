package Classes.Warehouse;

import Classes.LocationProducts;

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

    public int generateArticule(){
        article = new Random().nextInt(100000, 999999);
        return article;
    }

    public void changeNumberOnShelf(int newNumber){
        this.numberOnShelf = newNumber;
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
}
