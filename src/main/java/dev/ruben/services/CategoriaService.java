package dev.ruben.services;

import dev.ruben.dto.CategoriaDTO;
import dev.ruben.models.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findById(Long id);
    Categoria save(CategoriaDTO categoria);
    Categoria update(Long id, CategoriaDTO categoria);
    void deleteById(Long id);
    Categoria findByNombre(String nombre);

}
