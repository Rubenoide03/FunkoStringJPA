package dev.ruben.funkos.repositories;

import dev.ruben.categorias.models.Categoria;
import dev.ruben.categorias.repositories.CategoriaRepository;
import dev.ruben.funkos.models.Funko;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.cache.annotation.CachePut;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class FunkoRepositoryTest {
    private final Categoria categoria = Categoria.builder()
            .id(1L)
            .name("Categoria")
            .createdAt(null)
            .updatedAt(null)
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
    @Autowired
    private FunkoRepository repository;
    @Autowired
    private TestEntityManager entityManager; // EntityManager para hacer las pruebas
    @BeforeEach
    void setUp() {
        entityManager.merge(categoria);
        entityManager.flush();
        entityManager.merge(funko1);
        entityManager.merge(funko2);
        entityManager.flush();

    }
    @Test
    void findAll() {
        List<Funko> funkos = repository.findAll();
        assertEquals(2, repository.findAll().size());
    }
    @Test
    void findById() {
        var all = repository.findAll();
        Funko funko = repository.findById(1L).get();
        System.out.println("Found Funko: " + funko);
        repository.save(funko);

        assertAll(() -> assertNotNull(funko),
                () -> assertEquals(1L, funko.getId()));

    }
    @Test
    void findByName() {
        String name = "Funko1";
        Optional<Funko> funko = repository.findByNameEqualsIgnoreCase(name);
        assertAll(() -> assertNotNull(funko),
                () -> assertTrue(funko.isPresent()),
                () -> assertEquals(funko1, funko.get()));

    }
    @Test

    void deleteById() {
        Long id = 1L;
        repository.deleteById(id);
        var all = repository.findAll();
        assertAll(() -> assertNotNull(repository),
                () -> assertEquals(1, all.size()),
                () -> assertFalse(repository.findById(id).isPresent()))   ;

    }
    @Test

    void save() {
        Funko funko = Funko.builder()
                .id(3L)
                .name("Funko3")
                .price(10.0)
                .stock(10)
                .image("image3")
                .categoria(categoria)
                .build();
        repository.save(funko);
        var all = repository.findAll();
        assertAll((
                () -> assertEquals(3, all.size())),
                () -> assertTrue(repository.findById(3L).isPresent()))   ;

    }
    @Test
    void update() {
        Funko funko = repository.findById(1L).orElse(null);
        funko.setName("Funko4");
        repository.save(funko);
        var all = repository.findAll();
        assertAll(() -> assertNotNull(repository),
                () -> assertEquals(2, repository.findAll().size()),
                () -> assertTrue(repository.findById(1L).isPresent()),
                () -> assertEquals("Funko4", repository.findById(1L).orElse(null).getName()))   ;

    }

}