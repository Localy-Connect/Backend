package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.MessageMapper;
import ch.gibb.localy.data.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public MessageDto create(User user, MessageDto messageDto) {
        Message message = MessageMapper.fromDto(messageDto);
        message.setUser(user);
        messageRepository.save(message);
        return messageDto;
    }

    public List<MessageDto> findAll() {
        List<MessageDto> messages = new ArrayList<>();
        for (Message m : messageRepository.findAll()) {
            messages.add(MessageMapper.toDto(m));
        }
        return messages;
    }

    public MessageDto findById(Integer id) {
        return MessageMapper.toDto(messageRepository.findById(id).orElseThrow());
    }

    public void update(MessageDto messageDto) {
        messageRepository.save(MessageMapper.fromDto(messageDto));
    }

    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }


}
