package ch.gibb.localy.service;

import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.MessageRepository;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Mock private MessageRepository msgRepo;
    @Mock private UserInfoRepository userInfoRepo;
    @Mock private TownRepository townRepo;

    @InjectMocks private MessageService messageService;

    @BeforeEach void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessage_shouldSaveAndReturnMessage() {
        UserInfo sender = new UserInfo(); sender.setId(5); sender.setUsername("s");
        Town town = new Town(); town.setId(3); town.setName("T");

        when(userInfoRepo.findById(5)).thenReturn(Optional.of(sender));
        when(townRepo.findById(3)).thenReturn(Optional.of(town));
        when(msgRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Message m = messageService.sendMessage(5, 3, "Hi", "Hello");
        assertNotNull(m);
        assertEquals(sender, m.getSender());
        assertEquals(town, m.getTown());
        assertEquals("Hi", m.getTitle());
        assertEquals("Hello", m.getText());
        verify(msgRepo).save(m);
    }

    @Test
    void getSentMessages_shouldReturnList() {
        UserInfo sender = new UserInfo(); sender.setId(7);
        List<Message> list = List.of(new Message(), new Message());
        when(userInfoRepo.findById(7)).thenReturn(Optional.of(sender));
        when(msgRepo.findBySender(sender)).thenReturn(list);

        List<Message> result = messageService.getSentMessages(7);
        assertSame(list, result);
        verify(msgRepo).findBySender(sender);
    }
}
