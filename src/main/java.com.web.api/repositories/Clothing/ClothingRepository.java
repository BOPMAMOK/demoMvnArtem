package com.web.api.repositories.clothing;

import com.web.api.model.Clothing;

import java.util.List;

public interface ClothingRepository {
    List<Clothing> getClothings();

    Clothing getClothing(int clothingId);

    void createClothing(Clothing clothing);

    void updateClothing(Clothing clothing, int clothingId);

    void deleteClothing(int clothingId);
}