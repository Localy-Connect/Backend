package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.MessageMapper;
import ch.gibb.localy.data.repository.MessageRepository;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private UserRepository userRepository;



    public MessageDto createMessage(MessageDto messageDto, User user, Town town) {
        Message message = MessageMapper.fromDto(messageDto, town);
        messageRepository.save(message);
        return MessageMapper.toDto(message);
    }


    public List<MessageDto> findAll() {
        return messageRepository.findAll().stream()
                .map(MessageMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> findAllMessageFromTown(Integer id) {
        return messageRepository.findByTownId(id).stream()
                .map(MessageMapper::toDto)
                .collect(Collectors.toList());
    }

    public MessageDto findById(Integer id) {
        return MessageMapper.toDto(messageRepository.findById(id).orElseThrow());
    }

    public void update(MessageDto messageDto, Town town) {
        messageRepository.save(MessageMapper.fromDto(messageDto, town));
    }

    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }


}
