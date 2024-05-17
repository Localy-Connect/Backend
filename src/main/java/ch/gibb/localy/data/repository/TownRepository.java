package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Integer> {

    Optional<Town> findByName(String name);
}
