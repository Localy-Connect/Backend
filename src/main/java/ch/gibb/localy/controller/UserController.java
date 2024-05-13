package ch.gibb.localy.controller;

import ch.gibb.localy.controller.response.UserResponse;
import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.controller.response.TokenResponse;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id) {
        try {
            return userService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @GetMapping
    public List<UserDto> findAll() {
        try {
            return userService.findAll();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(path = "/signup", consumes = "application/json")
    public UserResponse signUp(@RequestBody UserDto userDto) {
        try {
            return new UserResponse(userService.signUp(userDto));


        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(path = "/signin", consumes = "application/json")
    public TokenResponse signIn(@RequestBody LogInDto logIn) {
        try {
            return new TokenResponse(userService.signIn(logIn));
        } catch (
                DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

}
