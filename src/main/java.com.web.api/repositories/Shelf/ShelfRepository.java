package com.web.api.repositories.shelf;

import com.web.api.model.Clothing;
import com.web.api.model.Shelf;

import java.util.List;

public interface ShelfRepository {
    List<Shelf> getShelfs();

    Shelf getShelf(int shelfId);

    List<Clothing> getClothingsByShelfId(int shelfId);

    void createShelf(Shelf shelf);

    void updateShelf(Shelf shelf, int shelfId);

    void deleteShelf(int shelfId);
}