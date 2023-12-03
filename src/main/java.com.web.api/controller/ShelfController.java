package com.web.api.controller;


import com.web.api.model.Clothing;
import com.web.api.model.Shelf;
import com.web.api.repositories.shelf.ShelfRepositoryH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shelfs")
public class ShelfController {

    private final ShelfRepositoryH2 repository;

    @Autowired
    public ShelfController(ShelfRepositoryH2 repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Shelf> getShelfs() {
        return repository.getShelfs();
    }

    @GetMapping("/{shelf_id}")
    public Shelf getShelf(@PathVariable("shelf_id") int shelfId) {
        return repository.getShelf(shelfId);
    }

    @GetMapping("/{shelf_id}/movies")
    public List<Clothing> getClothingsByShelfId(@PathVariable("shelf_id") int shelfId) {
        return repository.getClothingsByShelfId(shelfId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createShelf(@RequestBody Shelf shelf) {
        repository.createShelf(shelf);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{shelf_id}")
    public void updateShelf(@RequestBody Shelf shelf, @PathVariable("shelf_id") int shelfId) {
        repository.updateShelf(shelf, shelfId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{shelf_id}")
    public void deleteShelf(@PathVariable("shelf_id") int shelfId) {
        repository.deleteShelf(shelfId);
    }
}