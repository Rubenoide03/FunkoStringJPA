package dev.ruben.funkos.mappers;

import dev.ruben.funkos.dto.FunkoCreateDTO;
import dev.ruben.funkos.dto.FunkoResponseDTO;
import dev.ruben.funkos.dto.FunkoUpdateDTO;
import dev.ruben.categorias.models.Categoria;
import dev.ruben.funkos.models.Funko;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class FunkoMapper {
    public Funko toFunko(FunkoCreateDTO dto, Categoria categoria){
        return Funko.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .image(dto.getImage())
                .categoria(categoria)
                .build();
    }
    public Funko toFunko(FunkoCreateDTO dto){
        return Funko.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .image(dto.getImage())
                .build();
    }
    public Funko toFunko(FunkoUpdateDTO dto, Funko funko, Categoria categoria) {
        return Funko.builder()
                .id(funko.getId())
                .name(dto.getName() != null ? dto.getName() : funko.getName())
                .price(dto.getPrice() !=null ? dto.getPrice() : funko.getPrice())
                .stock(dto.getStock() != null ? dto.getStock() : funko.getStock())
                .createdAt(funko.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .categoria(dto.getCategory() !=null ? dto.getCategory() : funko.getCategoria())
                .build();
    }
    public FunkoResponseDTO toFunkoResponseDto(Funko funko){
        return FunkoResponseDTO.builder()
                .id(funko.getId())
                .name(funko.getName())
                .price(funko.getPrice())
                .stock(funko.getStock())
                .createdAt(funko.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .category(funko.getCategoria())
                .build();
    }
    public FunkoCreateDTO toFunkoCreateDto(Funko funko){
        return FunkoCreateDTO.builder()
                .name(funko.getName())
                .price(funko.getPrice())
                .stock(funko.getStock())
                .image(funko.getImage())
                .build();
    }
    public FunkoUpdateDTO toFunkoUpdateDto(Funko funko){
        return FunkoUpdateDTO.builder()
                .name(funko.getName())
                .price(funko.getPrice())
                .stock(funko.getStock())
                .category(funko.getCategoria())
                .build();
    }
}
