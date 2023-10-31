package dev.ruben.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FunkoUpdateDTO {
    private final String name;
    private final Double price;
    private final Integer stock;
    private final String image;
    private final String category;
}
