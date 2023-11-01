package dev.ruben.funkos.repositories;
import dev.ruben.funkos.models.Funko;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FunkoRepository extends JpaRepository<Funko, Long> {
    Optional<Funko> findByNameEqualsIgnoreCase(String nombre);





}
