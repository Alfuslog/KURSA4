package Classes.Warehouse;

import Classes.LocationProducts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rack {
    private final List<Shelf> shelves;
    private final String idRack;

    public Rack(String idRack, int shelfCount, int shelfLength, int shelfWidth,
                int marginTop, int maxWeight) {
        this.idRack = idRack;
        this.shelves = new ArrayList<>(shelfCount);

        for (int i = 0; i < shelfCount; i++) {
            shelves.add(new Shelf(i + 1, shelfLength, shelfWidth, marginTop, maxWeight));
        }
    }

    public int addProductToShelf(int shelfNumber, Product product) {
        if (shelfNumber < 1 || shelfNumber > shelves.size()) {
            throw new IllegalArgumentException("неверная полка: " + shelfNumber);
        }
        return shelves.get(shelfNumber - 1).addProductToShelf(product);
    }


    public void removeProductFromShelf(int shelfNumber, int productNumber) {
        if (shelfNumber < 1 || shelfNumber > shelves.size()) {
            throw new IllegalArgumentException("неверная полка: " + shelfNumber);
        }
        shelves.get(shelfNumber - 1).extractProduct(productNumber);
    }

    public List<LocationProducts> getAllProducts() {
        List<LocationProducts> result = new ArrayList<>();
        for (Shelf shelf : shelves) {
            for (Product product : shelf.getShelfProducts()) {
                LocationProducts loc = new LocationProducts(product.getNameProduct());
                loc.setArticleLocation(product.getArticle());
                loc.setCountProductLocation(product.getCountProduct());
                loc.setNameRackLocation(this.idRack);
                loc.setNumberShelfLocation(shelf.getNomberShelf());
                result.add(loc);
            }
        }
        return result;
    }

    public String exportProductsToJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(getAllProducts());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("ошибка экспорта :( JSON", e);
        }
    }
}