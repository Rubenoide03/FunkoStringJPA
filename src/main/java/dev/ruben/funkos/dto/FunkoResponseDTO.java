package dev.ruben.funkos.dto;

import dev.ruben.categorias.models.Categoria;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FunkoResponseDTO {
    private final Long id;
    private final String name;
    private final Double price;
    private final Integer stock;
    private final String image;
    private final Categoria category;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

}
