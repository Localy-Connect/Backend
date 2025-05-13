package ch.gibb.localy.controller;

import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestParam Integer townId,
            @RequestParam String title,
            @RequestParam String text,
            Authentication authentication) {

        Integer senderId = (Integer) authentication.getPrincipal();
        Message m = messageService.sendMessage(senderId, townId, title, text);
        return ResponseEntity.ok(m);
    }
}
