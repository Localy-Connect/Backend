package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.MessageService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody MessageDto dto) {

        UserInfo user = AuthInfo.getUser();
        Message m = messageService.sendMessage(user.getId(), Math.toIntExact(dto.getTownId()), dto.getTitle(), dto.getText());
        return ResponseEntity.ok(m);
    }
}
