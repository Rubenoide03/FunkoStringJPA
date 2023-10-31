package dev.ruben.controllers;

import dev.ruben.dto.FunkoCreateDTO;
import dev.ruben.dto.FunkoUpdateDTO;
import dev.ruben.models.Funko;
import dev.ruben.services.FunkoService;
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
    public ResponseEntity<Funko> findById(Long id){
        log.info("Finding funko by id");
        return ResponseEntity.ok(funkoService.findById(id));
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
        log.info("Borrando Funko");
        funkoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{name}")
    public ResponseEntity<Funko> findByName(String name){
        log.info("Finding funko by name");
        return ResponseEntity.ok(funkoService.findByName(name));
    }

}
