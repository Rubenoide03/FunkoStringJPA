package dev.ruben.categorias;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
@Builder
public class CategoriaDTO {
    @Length(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;

}
