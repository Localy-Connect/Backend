package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TownRepository extends CrudRepository<Town, Integer> {
}
