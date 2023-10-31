package dev.ruben.services;

import dev.ruben.dto.FunkoCreateDTO;
import dev.ruben.dto.FunkoUpdateDTO;
import dev.ruben.models.Funko;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FunkoService {
    List<Funko> findAll();
  Funko findById(Long id);
  Funko save(FunkoCreateDTO funkoCreateDTO);
    Funko update(Long id,FunkoUpdateDTO funkoUpdateDTO);
    void deleteById(Long id);
    Funko findByName(String name);
}
