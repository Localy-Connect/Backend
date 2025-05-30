package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.dto.UserInfoDto;
import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/towns")
public class TownController {

    @Autowired
    private TownService townService;

    @PostMapping
    public ResponseEntity<TownDto> createTown(@RequestParam String name,
                                              @RequestHeader("Idempotency-Key") String idempotencyKey) {
        Town created = townService.createTown(name);
        return ResponseEntity
                .ok()
                .header("Idempotency-Key", idempotencyKey)
                .body(toDto(created));
    }

    @GetMapping
    public ResponseEntity<List<TownDto>> getTowns() {
        List<Town> all = townService.getTowns();
        List<TownDto> dtos = all.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}/residents")
    public ResponseEntity<Set<UserInfoDto>> getResidents(@PathVariable Integer id) {
        Set<UserInfo> residents = townService.getResidents(id);
        Set<UserInfoDto> dtos = residents.stream()
                .map(this::toUserInfoDto)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(dtos);
    }

    private TownDto toDto(Town t) {
        TownDto dto = new TownDto();
        dto.setId(t.getId().longValue());
        dto.setName(t.getName());
        dto.setMessages(
                t.getMessages()
                        .stream()
                        .map(this::toMessageDto)
                        .collect(Collectors.toSet())
        );
        return dto;
    }

    private UserInfoDto toUserInfoDto(UserInfo u) {
        UserInfoDto dto = new UserInfoDto();
        dto.setUserId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setTownId(u.getTown() != null ? u.getTown().getId() : null);
        return dto;
    }

    private MessageDto toMessageDto(Message m) {
        MessageDto dto = new MessageDto();
        dto.setId(m.getId().longValue());
        dto.setText(m.getText());

        Long senderId = null;
        if (m.getSender() != null && m.getSender().getId() != null) {
            senderId = m.getSender().getId().longValue();
        }
        dto.setUserId(senderId);

        Long townId = null;
        if (m.getTown() != null && m.getTown().getId() != null) {
            townId = m.getTown().getId().longValue();
        }
        dto.setTownId(townId);

        return dto;
    }
}
