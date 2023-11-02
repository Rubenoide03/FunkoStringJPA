package dev.ruben.categorias.services;

import dev.ruben.categorias.dto.CategoriaDTO;
import dev.ruben.categorias.models.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findById(Long id);
    Categoria save(CategoriaDTO categoria);
    Categoria update(Long id, CategoriaDTO categoria);
    void deleteById(Long id);
    Categoria findByNombre(String nombre);

}
