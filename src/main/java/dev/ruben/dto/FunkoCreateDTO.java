package dev.ruben.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
@Getter
@Setter
@Builder
public class FunkoCreateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Length(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;
    @Positive(message = "El precio debe ser mayor que 0")
    private Double price;
    @Positive(message = "El stock debe ser mayor que 0")
    private Integer stock;
    @NotBlank(message = "La imagen no puede estar vacía")
    private String image;
    @NotBlank(message = "La categoría no puede estar vacía")
    private String category;


}
