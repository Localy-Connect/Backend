package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.MessageService;
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            messageService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody MessageDto townDto) {
        try {
            messageService.update(townDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody MessageDto townDto) {
        try {
            messageService.create(AuthInfo.getUser(), townDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }


}
