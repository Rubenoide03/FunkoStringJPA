package dev.ruben.funkos.services;

import dev.ruben.categorias.models.Categoria;
import dev.ruben.categorias.repositories.CategoriaRepository;
import dev.ruben.funkos.mappers.FunkoMapper;
import dev.ruben.funkos.models.Funko;
import dev.ruben.funkos.repositories.FunkoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class FunkoServiceImplTest {
    Categoria categoria = Categoria.builder()
            .id(1L)
            .name("Categoria")
            .build();
    private final Funko funko1 = Funko.builder()
            .id(1L)
            .name("Funko1")
            .price(10.0)
            .stock(10)
            .image("image")
            .categoria(categoria)
            .build();
    private final Funko funko2 = Funko.builder()
            .id(2L)
            .name("Funko2")
            .price(109.0)
            .stock(10)
            .image("image2")
            .categoria(categoria)
            .build();
    @Mock
    private FunkoRepository funkoRepository;
    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private FunkoMapper funkoMapper;
    @InjectMocks
    private FunkoServiceImpl funkoService;

    @Test
    void findAll() {
        List<Funko> funkos = Arrays.asList(funko1,funko2);
        when(funkoRepository.findAll()).thenReturn(funkos);
        var result = funkoService.findAll();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
        () -> assertFalse(funkos.isEmpty())
        );
        verify(funkoRepository, times(1)).findAll();

    }

    @Test
    void findById() {
        when(funkoRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(funko1));
        var result = funkoService.findById(1L);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId()),
                () -> verify(funkoRepository, times(1)).findById(1L));
    }

    @Test
    void save() {
        when(funkoRepository.save(funko1)).thenReturn(funko1);
        var result = funkoService.save(funkoMapper.toFunkoCreateDto(funko1));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId()));
        verify(funkoRepository, times(1)).save(funko1);
    }

    @Test
    void findByName() {
        when(funkoRepository.findByNameEqualsIgnoreCase("Funko1")).thenReturn(java.util.Optional.ofNullable(funko1));
        var result = funkoService.findByName("Funko1");

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Funko1", result.getName()));
                 verify(funkoRepository, times(1)).findById(1L);


    }

    @Test
    void deleteById() {
        Long id = 1L;
        funkoService.deleteById(id);
        var all = funkoRepository.findAll();
        assertAll(() -> assertNotNull(funkoRepository),
                () -> assertEquals(1, all.size()),
                () -> assertFalse(funkoRepository.findById(id).isPresent()))   ;
    }

    @Test
    void update() {
        List<Funko> funkos = Arrays.asList(funko1,funko2);
        funkoService.findAll();
        when(funkoRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(funko1));
        when(categoriaRepository.findByNameEqualsIgnoreCase("Categoria")).thenReturn(java.util.Optional.ofNullable(categoria));
        var result = funkoService.update(1L, funkoMapper.toFunkoUpdateDto(funko1));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId()));
        verify(funkoRepository, times(1)).findById(1L);
    }
}