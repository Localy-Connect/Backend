package ch.gibb.localy;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.repository.MessageRepository;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserRepository;
import ch.gibb.localy.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private TownRepository townRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MessageService messageService;

    private Message message;
    private MessageDto messageDto;
    private Town town;
    private User user;

    @BeforeEach
    public void setUp() {
        town = new Town();
        town.setId(1L);

        user = new User();
        user.setId(1L);

        message = new Message();
        message.setId(1L);
        message.setTitle("Test title");
        message.setText("Test message");
        message.setTown(town);
        message.setUser(user);

        messageDto = new MessageDto();
        messageDto.setId(1L);
        messageDto.setTitle("Test title");
        messageDto.setText("Test message");
        messageDto.setTownId(1L);
    }

    @Test
    public void testCreateMessage() {
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        MessageDto createdMessage = messageService.createMessage(messageDto, user, town);

        assertNotNull(createdMessage);
        assertEquals(messageDto.getText(), createdMessage.getText());
        assertEquals(messageDto.getTitle(), createdMessage.getTitle());
        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    public void testFindAll() {
        when(messageRepository.findAll()).thenReturn(Collections.singletonList(message));

        List<MessageDto> messages = messageService.findAll();

        assertNotNull(messages);
        assertEquals(1, messages.size());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllMessageFromTown() {
        when(messageRepository.findByTownId(anyInt())).thenReturn(Collections.singletonList(message));

        List<MessageDto> messages = messageService.findAllMessageFromTown(1);

        assertNotNull(messages);
        assertEquals(1, messages.size());
        verify(messageRepository, times(1)).findByTownId(1);
    }

    @Test
    public void testFindById() {
        when(messageRepository.findById(anyInt())).thenReturn(Optional.of(message));

        MessageDto foundMessage = messageService.findById(1);

        assertNotNull(foundMessage);
        assertEquals(message.getText(), foundMessage.getText());
        assertEquals(message.getTitle(), foundMessage.getTitle());
        verify(messageRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdate() {
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        messageService.update(messageDto, town);

        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    public void testDeleteById() {
        doNothing().when(messageRepository).deleteById(anyInt());

        messageService.deleteById(1);

        verify(messageRepository, times(1)).deleteById(1);
    }
}
