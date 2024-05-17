package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.TownMapper;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.MessageService;
import ch.gibb.localy.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private TownService townService;


    @GetMapping("/{id}")
    public MessageDto findById(@PathVariable Integer id) {
        try {
            return messageService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @GetMapping
    public List<MessageDto> findAll() {
        try {
            return messageService.findAll();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @GetMapping("byTownId/{id}")
    public List<MessageDto> findAllMessageFromTown(@PathVariable Integer id) {
        try {
            return messageService.findAllMessageFromTown(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            messageService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody MessageDto messageDto) {
        try {
            TownDto town = townService.findById(messageDto.getTownId().intValue());
            messageService.update(messageDto, TownMapper.fromDto(town));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        User user = AuthInfo.getUser();
        TownDto town = townService.findById(messageDto.getTownId().intValue());
        return messageService.createMessage(messageDto, user, TownMapper.fromDto(town));
    }

}
