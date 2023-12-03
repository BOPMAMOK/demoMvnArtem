package com.web.api.model;

import java.util.Date;

public record Clothing(int clothingId, int shelfId, Date issueDate, int clothingPrice, boolean isMale,
                    ClothingType type) {
}