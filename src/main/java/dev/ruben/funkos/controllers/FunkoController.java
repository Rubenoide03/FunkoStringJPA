package dev.ruben.funkos.controllers;

import dev.ruben.funkos.dto.FunkoCreateDTO;
import dev.ruben.funkos.dto.FunkoUpdateDTO;
import dev.ruben.funkos.models.Funko;
import dev.ruben.funkos.services.FunkoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/funkos")
public class FunkoController {
    private FunkoService funkoService;



    @Autowired
    public FunkoController(FunkoService funkoService){
        this.funkoService=funkoService;
        

    }
    @GetMapping
    public ResponseEntity<List<Funko>> findAll(){
        log.info("Finding all funkos");
        return ResponseEntity.ok(funkoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Funko> findById(Long id) {
        try {
            log.info("Finding funko by id");
            return ResponseEntity.ok(funkoService.findById(id));

        }catch (Exception e){
            log.error("Error al buscar el funko");
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping
    public ResponseEntity<Funko> save(FunkoCreateDTO funkoCreateDTO){
        log.info("Guardando Funko");
        return ResponseEntity.ok(funkoService.save(funkoCreateDTO));
    }
    @PutMapping
    public ResponseEntity<Funko> update(Long id, FunkoUpdateDTO funkoCreateDTO){
        log.info("Actualizando Funko");
        return ResponseEntity.ok(funkoService.update(id,funkoCreateDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id){
        try{log.info("Borrando Funko");
            funkoService.deleteById(id);
            return ResponseEntity.noContent().build();

        }catch (Exception e){
            log.error("Error al borrar el funko");
            return ResponseEntity.badRequest().build();

    }}



}
