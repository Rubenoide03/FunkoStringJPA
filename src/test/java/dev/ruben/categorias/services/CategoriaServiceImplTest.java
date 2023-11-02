package dev.ruben.categorias.services;

import dev.ruben.categorias.dto.CategoriaDTO;
import dev.ruben.categorias.mappers.CategoriaMapper;
import dev.ruben.categorias.models.Categoria;
import dev.ruben.categorias.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {
    private final Categoria categoria = Categoria.builder()
            .id(1L)
            .name("Categoria 1")
            .build();
    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;
    @InjectMocks
    private CategoriaServiceImpl categoriaService;


    @Test
    void findAll() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        var result = categoriaService.findAll();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.size()));

    }

    @Test
    void findById() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.ofNullable(categoria));
        var result = categoriaService.findById(1L);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId()),
        () -> verify(categoriaRepository, times(1)).findById(1L));

    }

    @Test
    void save() {
        Categoria categoria1 = Categoria.builder()
                .id(2L)
                .name("Categoria 2")
                .build();
        when(categoriaRepository.save(categoria1)).thenReturn(categoria1);
        var result = categoriaService.save(categoriaMapper.toCategoriaDTO(categoria1));
        assertAll(
                () -> assertNotNull(categoria1),
                () -> assertEquals(2L, categoria1.getId()));
        verify(categoriaRepository, times(1)).save(categoria1);
    }

    @Test
    void findByName() {
        when(categoriaRepository.findByNameEqualsIgnoreCase("Categoria 1")).thenReturn(Optional.ofNullable(categoria));
        var result = categoriaService.findByName("Categoria 1");
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Categoria 1", result.getName()),
                () -> verify(categoriaRepository, times(1)).findByNameEqualsIgnoreCase("Categoria 1"));



    }


    @Test
    void deleteById() {
        Long id = 1L;
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));
        categoriaService.deleteById(id);
        var result = categoriaService.findAll();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(0, result.size())
        );
        verify(categoriaRepository, times(1)).deleteById(id);

    }


    @Test
    void update() {
        CategoriaDTO categoriaDTO = CategoriaDTO.builder()
                .name("Categoria 1")
                .build();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.ofNullable(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        var result = categoriaService.update(1L, categoriaDTO);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Categoria 1", result.getName()),
                () -> verify(categoriaRepository, times(1)).findById(1L),
                () -> verify(categoriaRepository, times(1)).save(categoria));
    }
}