package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"town"})
    User findByName(String name);
}
