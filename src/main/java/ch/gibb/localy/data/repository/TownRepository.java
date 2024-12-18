package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Integer> {

    Optional<Town> findByName(String name);
}
