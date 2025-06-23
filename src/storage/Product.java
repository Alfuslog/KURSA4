package storage;

import java.util.Scanner;
import java.util.ArrayList;

public class Product {
    private final int length;
    private final int width;
    private final int height;

    private String article;




    public Product(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }


    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticle() {
        return article;
    }
    public void getArticle(String args){
        this.article = args;
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

}
