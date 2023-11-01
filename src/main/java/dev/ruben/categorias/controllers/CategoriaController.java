package dev.ruben.categorias.controllers;

import dev.ruben.categorias.models.Categoria;
import dev.ruben.categorias.CategoriaDTO;
import dev.ruben.categorias.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/categorias")
public class CategoriaController {
    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService=categoriaService;
    }
    @GetMapping("api/categorias")
    public ResponseEntity<List<Categoria>> findAll(){
        log.info("Finding all categorias");
        return ResponseEntity.ok(categoriaService.findAll());
    }
    @GetMapping
    public ResponseEntity<Categoria> findById(Long id){
        log.info("Finding categoria by id");
        return ResponseEntity.ok(categoriaService.findById(id));
    }
    @GetMapping("/{name}")
    public ResponseEntity<Categoria> findByName(String name){
        log.info("Finding categoria by name");
        return ResponseEntity.ok(categoriaService.findByNombre(name));
    }
    @PostMapping
    public ResponseEntity<Categoria> save(CategoriaDTO categoria){
        log.info("Guardando Categoria");
        return ResponseEntity.ok(categoriaService.save(categoria));
    }
    @PutMapping
    public ResponseEntity<Categoria> update(Long id, CategoriaDTO categoria){
        log.info("Actualizando Categoria");
        return ResponseEntity.ok(categoriaService.update(id,categoria));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id){
        log.info("Borrando Categoria");
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
