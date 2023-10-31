package dev.ruben.mappers;

import dev.ruben.dto.CategoriaDTO;
import dev.ruben.models.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public Categoria toCategoria(CategoriaDTO categoriaDTO){
        return Categoria.builder()
                .name(categoriaDTO.getName())
                .build();
    }
    public Categoria toCategoria(CategoriaDTO categoriaDTO, Categoria categoria){
        return new Categoria(
                categoria.getId(),
                categoria.getName(),
                categoria.getCreatedAt(),
                categoria.getUpdatedAt()


        );
    }
}
