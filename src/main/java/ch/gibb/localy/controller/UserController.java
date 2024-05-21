package ch.gibb.localy.controller;

import ch.gibb.localy.controller.response.UserResponse;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.service.UserService;
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

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        try {
            UserDto userDto = userService.findById(id);
            return new UserResponse(userDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping
    public List<UserResponse> findAll() {
        try {
            List<UserDto> userDtos = userService.findAll();
            return userDtos.stream().map(UserResponse::new).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data integrity violation");
        }
    }
}
