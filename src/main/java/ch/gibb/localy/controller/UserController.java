package ch.gibb.localy.controller;

import ch.gibb.localy.controller.request.ChangePasswordRequest;
import ch.gibb.localy.controller.response.UserResponse;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Find a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        try {
            UserDto userDto = userService.findById(id);
            return new UserResponse(userDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Operation(summary = "Get a list of all users")
    @ApiResponse(responseCode = "200", description = "Found the users")
    @GetMapping
    public List<UserResponse> findAll() {
        List<UserDto> userDtos = userService.findAll();
        return userDtos.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Operation(summary = "Update a user")
    @ApiResponse(responseCode = "200", description = "Updated the user")
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data integrity violation");
        }
    }

    @Operation(summary = "Change a user's password")
    @ApiResponse(responseCode = "200", description = "Changed the password")
    @PatchMapping(consumes = "application/json", path = "/password")
    public void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(AuthInfo.getUser().getId(), changePasswordRequest.getCurrentPassword(), changePasswordRequest.getNewPassword());
    }
}
