package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u from User as u where u.id = :id ")
    User findUserById(@Param("id") Integer id);

    @Query("SELECT u from User as u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u from User as u where u.name = :name")
    User findByName(@Param("name") String name);
}
