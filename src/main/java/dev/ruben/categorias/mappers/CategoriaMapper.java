package dev.ruben.categorias.mappers;

import dev.ruben.categorias.dto.CategoriaDTO;
import dev.ruben.categorias.models.Categoria;
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
    public CategoriaDTO toCategoriaDTO(Categoria categoria){
        return CategoriaDTO.builder()
                .name(categoria.getName())
                .build();
    }
}
