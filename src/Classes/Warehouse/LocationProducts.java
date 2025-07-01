package Classes.Warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "nameProduct",
        "article",
        "countProduct",
        "nameRack",
        "numberShelf"
})
public class LocationProducts {
    @JsonProperty("nameProduct")
    private String nameProduct;

    @JsonProperty("article")
    private int article;

    @JsonProperty("countProduct")
    private int countProduct;

    @JsonProperty("nameRack")
    private String nameRack;

    @JsonProperty("numberShelf")
    private int numberShelf;

    public LocationProducts(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    // Геттеры и сеттеры
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
        this.article = article;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public String getNameRack() {
        return nameRack;
    }

    public void setNameRack(String nameRack) {
        this.nameRack = nameRack;
    }

    public int getNumberShelf() {
        return numberShelf;
    }

    public void setNumberShelf(int numberShelf) {
        this.numberShelf = numberShelf;
    }

    // Алиасы для совместимости с существующим кодом
    public int getArticleLocation() {
        return article;
    }

    public void setArticleLocation(int article) {
        this.article = article;
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