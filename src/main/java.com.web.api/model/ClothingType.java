package com.web.api.model;

public enum ClothingType {
    JEANS(1),
    SHIRT(2),
    SHORTS(3),
    DRESS(4),
    TSHIRT(5),
    GLOVES(6),
    SCARF(7),
    SKIRT(8),
    JACKET(9),
    JUMPER(10);

    private final int id;

    public int getId() {
        return id;
    }

    ClothingType(Integer id) {
        this.id = id;
    }
}