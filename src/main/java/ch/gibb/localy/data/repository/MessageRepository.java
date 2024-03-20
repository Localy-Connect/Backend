package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
