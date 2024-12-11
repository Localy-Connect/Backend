package ch.gibb.localy.data.repository;

import ch.gibb.localy.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByTownId(Integer townId);
}
