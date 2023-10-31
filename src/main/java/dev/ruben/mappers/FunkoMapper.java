package dev.ruben.mappers;

import dev.ruben.dto.FunkoCreateDTO;
import dev.ruben.dto.FunkoResponseDTO;
import dev.ruben.dto.FunkoUpdateDTO;
import dev.ruben.models.Categoria;
import dev.ruben.models.Funko;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class FunkoMapper {
    public Funko toFunko(FunkoCreateDTO dto,Categoria categoria){
        return Funko.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .image(dto.getImage())
                .category(dto.getCategory())
                .build();
    }
    public Funko toFunko(FunkoCreateDTO dto){
        return Funko.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .image(dto.getImage())
                .category(dto.getCategory())
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
                .category(dto.getCategory() !=null ? dto.getCategory() : funko.getCategory())
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
                .category(funko.getCategory())
                .build();
    }
}
