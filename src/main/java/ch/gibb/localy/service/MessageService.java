package ch.gibb.localy.service;

import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.MessageRepository;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private UserInfoRepository userInfoRepo;

    @Autowired
    private TownRepository townRepo;

    public Message sendMessage(Integer senderId, Long townId, String title, String text) {
        UserInfo sender = userInfoRepo.findById(senderId).orElseThrow();
        Town town = null;
        if (townId != null) {
            town = townRepo.findById(townId.intValue()).orElse(null);
        }

        Message m = new Message();
        m.setSender(sender);
        m.setTown(town);
        m.setTitle(title);
        m.setText(text);
        return msgRepo.save(m);
    }

    public List<Message> getSentMessages(Integer senderId) {
        UserInfo sender = userInfoRepo.findById(senderId).orElseThrow();
        return msgRepo.findBySender(sender);
    }
}
