package dev.ruben.categorias.services;

import dev.ruben.categorias.dto.CategoriaDTO;
import dev.ruben.categorias.exceptions.CategoriaNotFoundException;
import dev.ruben.categorias.mappers.CategoriaMapper;
import dev.ruben.categorias.models.Categoria;
import dev.ruben.categorias.repositories.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "categorias")
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final Logger logger = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Cacheable
    public List<Categoria> findAll() {
        logger.info("Finding all categorias");
        return categoriaRepository.findAll();
    }

    @Cacheable
    public Categoria findById(Long id) {
        logger.info("Finding categoria by id");
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria not found"));
    }



    @CachePut(key = "#result.id")
    public Categoria save(CategoriaDTO categoriaDTO) {
        logger.info("Guardando Categoria");
        return categoriaRepository.save(categoriaMapper.toCategoria(categoriaDTO));
    }

    @Cacheable
    public Categoria findByNombre(String name) {
        logger.info("Finding categoria by nombre");
        return categoriaRepository.findByNameEqualsIgnoreCase(name).orElseThrow(() -> new RuntimeException("Categoria not found"));
    }

    @CachePut(key = "#result.id")
    public Categoria update(Long id, CategoriaDTO categoriaDTO) {
        logger.info("Actualizando Categoria" + categoriaDTO);
        Categoria categoriaActual = findById(id);
        categoriaRepository.findByNameEqualsIgnoreCase(categoriaDTO.getName()).ifPresent(c -> {
            if (c.getId().equals(id)) {
                throw new RuntimeException("Categoria con nombre" + categoriaDTO.getName());
            }

        });
        return categoriaRepository.save(categoriaMapper.toCategoria(categoriaDTO, categoriaActual));

    }
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        logger.info("Borrando Categoria");
        try {
            categoriaRepository.deleteById(id);

        } catch (Exception e) {
            throw new CategoriaNotFoundException("Categoria with id" + id + "not found");
        }


    }
    public Categoria findByName(String name) {
        logger.info("Finding categoria by nombre");
        return categoriaRepository.findByNameEqualsIgnoreCase(name).orElseThrow(() -> new RuntimeException("Categoria not found"));
    }
}
