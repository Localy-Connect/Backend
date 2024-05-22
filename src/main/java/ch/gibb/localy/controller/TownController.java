package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.TownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/towns")
public class TownController {

    @Autowired
    private TownService townService;

    @Operation(summary = "Find a town by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the town"),
            @ApiResponse(responseCode = "404", description = "Town not found")
    })
    @GetMapping("/{id}")
    public TownDto findById(@PathVariable Integer id) {
        try {
            return townService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Town not found");
        }
    }

    @Operation(summary = "Get a list of all towns")
    @ApiResponse(responseCode = "200", description = "Found the towns")
    @GetMapping
    public List<TownDto> findAll() {
        return townService.findAll();
    }

    @Operation(summary = "Delete a town by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the town"),
            @ApiResponse(responseCode = "404", description = "Town not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            townService.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Town not found");
        }
    }

    @Operation(summary = "Update a town")
    @ApiResponse(responseCode = "200", description = "Updated the town")
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody TownDto townDto) {
        townService.update(townDto);
    }

    @Operation(summary = "Create a new town")
    @ApiResponse(responseCode = "200", description = "Created the town")
    @PostMapping(consumes = "application/json")
    public TownDto createTown(@RequestBody TownDto townDto) {
        User user = AuthInfo.getUser();
        return townService.createTown(townDto, user);
    }

    @Operation(summary = "Join a town")
    @ApiResponse(responseCode = "200", description = "Joined the town")
    @GetMapping("/{id}/join")
    public void joinTown(@PathVariable Integer id) {
        User userId = AuthInfo.getUser();
        townService.joinTown(id, userId);
    }

    @Operation(summary = "Leave a town")
    @ApiResponse(responseCode = "200", description = "Left the town")
    @GetMapping("/{id}/leave")
    public void leaveTown(@PathVariable Integer id) {
        User userId = AuthInfo.getUser();
        townService.leaveTown(id, userId);
    }
}
