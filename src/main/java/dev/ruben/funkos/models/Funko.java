package dev.ruben.funkos.models;

import dev.ruben.categorias.models.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
@Entity

public class Funko {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size( max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;
    @Column(name="price", nullable = false)
    @Positive(message = "El precio debe ser mayor que 0")
    private Double price;
    @Column(name="stock", nullable = false)
    @PositiveOrZero(message = "El stock debe ser mayor que 0")
    private Integer stock;
    @Column(name="image", nullable = false)
    private String image;
    @Column(name="createdAt", nullable = true)
    private LocalDateTime createdAt;
    @Column(name="updatedAt", nullable = true)
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;



}
