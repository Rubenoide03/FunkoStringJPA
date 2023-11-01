package dev.ruben.services;

import dev.ruben.funkos.models.Funko;
import dev.ruben.funkos.repositories.FunkoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FunkoServiceImplTest {
    private List<Funko> funkos;

    private final Funko funko1 = Funko.builder()
            .id(1L)
            .name("Funko 1")
            .category("Category 1")
            .price(10.0)
            .stock(10)
            .image("Image 1")
            .build();
    @Autowired
    private FunkoRepository funkoRepository;

    @BeforeEach
    void setUp() {
        funkoRepository.deleteAll();
        funkos.add(funko1);
    }

    @Test
    void findAll() {
        List<Funko> funkos = funkoRepository.findAll();

        assertAll(
                () -> assertNotNull(funkos),
                () -> assertEquals(1, funkos.size()),
        () -> assertFalse(funkos.isEmpty())
        );





    }

    @Test
    void findById() {
        Long id = 1L;
        Optional<Funko> funko = funkoRepository.findById(id);
        assertAll(
                () -> assertNotNull(funko),
                () ->assertTrue(funko.isPresent())

        );
    }

    @Test
    void save() {
    }

    @Test
    void findByName() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}