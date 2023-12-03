package com.web.api.controller;

import com.web.api.model.Clothing;
import com.web.api.repositories.clothing.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clothings")
public class ClothingController {

    private final ClothingRepository repository;

    @Autowired
    public ClothingController(ClothingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Clothing> getClothings() {
        return repository.getClothings();
    }

    @GetMapping("/{clothing_id}")
    public Clothing getClothing(@PathVariable("clothing_id") int clothingId) {
        return repository.getClothing(clothingId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createClothing(@RequestBody Clothing clothing) {
        repository.createClothing(clothing);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{clothing_id}")
    public void updateClothing(@RequestBody Clothing clothing, @PathVariable("clothing_id") int clothingId) {
        repository.updateClothing(clothing, clothingId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{clothing_id}")
    public void deleteClothing(@PathVariable("clothing_id") int clothingId) {
        repository.deleteClothing(clothingId);
    }
}