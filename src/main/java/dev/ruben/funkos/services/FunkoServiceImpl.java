package dev.ruben.funkos.services;

import dev.ruben.categorias.CategoriaService;
import dev.ruben.funkos.dto.FunkoCreateDTO;
import dev.ruben.funkos.dto.FunkoUpdateDTO;
import dev.ruben.funkos.exceptions.FunkoNotFoundException;
import dev.ruben.funkos.mappers.FunkoMapper;
import dev.ruben.categorias.models.Categoria;
import dev.ruben.funkos.models.Funko;
import dev.ruben.funkos.repositories.FunkoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FunkoServiceImpl implements FunkoService{
    private final FunkoRepository funkoRepository;
    private final Logger logger= LoggerFactory.getLogger(FunkoServiceImpl.class);
    private final FunkoMapper funkoMapper;
    private final CategoriaService categoriaService;


    @Autowired
    public FunkoServiceImpl(FunkoRepository funkoRepository,FunkoMapper funkoMapper,CategoriaService categoriaService){
        this.funkoRepository=funkoRepository;
        this.funkoMapper=funkoMapper;
        this.categoriaService=categoriaService;
    }
    @Cacheable (value="funkos")
    public List<Funko> findAll(){
        logger.info("Finding all funkos");
        return funkoRepository.findAll();

    }

    @Cacheable (key="#id")

    public Funko findById(Long id) {
        logger.info("Finding funko by id");
        return funkoRepository.findById(id).orElseThrow(() -> new FunkoNotFoundException("Funko not found"));
    }


    @CachePut (key="#result.id")
    public Funko save(FunkoCreateDTO funkoCreateDTO){
        logger.info("Guardando Funko");
        funkoRepository.findByNameEqualsIgnoreCase(funkoCreateDTO.getName()).ifPresent(funko -> {
            throw new RuntimeException("Funko already exists");
        });
        return funkoRepository.save(funkoMapper.toFunko(funkoCreateDTO));




    }
    @Cacheable ( key="#id")
    public Funko findByName(String name) {
        logger.info("Finding funko by nombre");
        return funkoRepository.findByNameEqualsIgnoreCase(name).orElseThrow(() -> new FunkoNotFoundException("Funko not found"));
    }
    @CacheEvict ( key="#id")
    public void deleteById(Long id){
        logger.info("Deleting funko by id");
        try{
            var funko = this.findById(id);
            funkoRepository.deleteById(id);
        }catch (Exception e)
        {
            throw new FunkoNotFoundException("Funko not found");
        }


    }
    @CachePut (key="#result.id")
   public Funko update(Long id, FunkoUpdateDTO funkoUpdateDTO){
        logger.info("Updating funko");
        var funko = this.findById(id);
        Categoria categoria= null;
        if(funkoUpdateDTO.getCategory()!=null){
            categoria=categoriaService.findByNombre(funkoUpdateDTO.getCategory());

        }
        return funkoMapper.toFunko(funkoUpdateDTO,funko,categoria);
    }





}
