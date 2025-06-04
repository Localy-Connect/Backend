package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(
            @RequestBody MessageDto dto,
            @RequestHeader("Idempotency-Key") String idempotencyKey) {

        UserInfo user = AuthInfo.getUser();
        Message saved = messageService.sendMessage(
                user.getId(),
                dto.getTownId(),
                dto.getTitle(),
                dto.getText()
        );

        MessageDto messageDto = new MessageDto();
        messageDto.setId(saved.getId().longValue());
        messageDto.setTitle(saved.getTitle());
        messageDto.setText(saved.getText());
        messageDto.setTownId(saved.getTown().getId().longValue());
        messageDto.setUserId(saved.getSender().getId().longValue());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Idempotency-Key", idempotencyKey)
                .body(messageDto);
    }
}
