package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TownRepository extends JpaRepository<Town, Integer> {
}
