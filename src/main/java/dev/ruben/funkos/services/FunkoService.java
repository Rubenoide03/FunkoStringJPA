package dev.ruben.funkos.services;

import dev.ruben.funkos.dto.FunkoCreateDTO;
import dev.ruben.funkos.dto.FunkoUpdateDTO;
import dev.ruben.funkos.models.Funko;

import java.util.List;


public interface FunkoService {
    List<Funko> findAll();
  Funko findById(Long id);
  Funko save(FunkoCreateDTO funkoCreateDTO);
    Funko update(Long id,FunkoUpdateDTO funkoUpdateDTO);
    void deleteById(Long id);
    Funko findByName(String name);
}
