package Classes.Warehouse;

import java.util.List;
import java.util.Random;

public class Rack {

    private List<Shelf> listShelfs;

    private int cntShelfs;
    private int lowShelfSpace = 0;
    private String idRack;


    public Rack(String idRack,){

    }

    public void changeMarginShelfR(int nomberShelf, int newHeight){   //подсказала нейро
        if ( nomberShelf != 1 && nomberShelf <= cntShelfs){
            listShelfs.get(nomberShelf).changeMarginShelf(newHeight);
            listShelfs.get(nomberShelf-1).changeMarginShelf(-newHeight);
        }
        else listShelfs.get(nomberShelf).changeMarginShelf(newHeight);
    }

    public boolean checkShellfIsEmpty(int numberProduct){
        numberProduct -= 1;
        if (listShelfs[numberProduct].)
        if (shelfProducts.isEmpty() && totalLength == freeLength){
            return true;
        }
        else return false;
    }



}
