package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.TownMapper;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.MessageService;
import ch.gibb.localy.service.TownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Operation(summary = "Find a message by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the message"),
            @ApiResponse(responseCode = "404", description = "Message not found")
    })
    @GetMapping("/{id}")
    public MessageDto findById(@PathVariable Integer id) {
        try {
            return messageService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
    }

    @Operation(summary = "Get a list of all messages")
    @ApiResponse(responseCode = "200", description = "Found the messages")
    @GetMapping
    public List<MessageDto> findAll() {
        return messageService.findAll();
    }

    @Operation(summary = "Find all messages from a specific town")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the messages"),
            @ApiResponse(responseCode = "404", description = "Town not found")
    })
    @GetMapping("byTownId/{id}")
    public List<MessageDto> findAllMessageFromTown(@PathVariable Integer id) {
        try {
            return messageService.findAllMessageFromTown(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Town not found");
        }
    }

    @Operation(summary = "Delete a message by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the message"),
            @ApiResponse(responseCode = "404", description = "Message not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            messageService.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
    }

    @Operation(summary = "Update a message")
    @ApiResponse(responseCode = "200", description = "Updated the message")
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody MessageDto messageDto) {
        try {
            TownDto town = townService.findById(messageDto.getTownId().intValue());
            messageService.update(messageDto, TownMapper.fromDto(town));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data integrity violation");
        }
    }

    @Operation(summary = "Create a new message")
    @ApiResponse(responseCode = "200", description = "Created the message")
    @PostMapping(consumes = "application/json")
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        User user = AuthInfo.getUser();
        TownDto town = townService.findById(messageDto.getTownId().intValue());
        return messageService.createMessage(messageDto, user, TownMapper.fromDto(town));
    }
}
