package ch.gibb.localy.controller;

import ch.gibb.localy.controller.response.TokenResponse;
import ch.gibb.localy.data.dto.LogInDto;
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
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping(path = "/signup", consumes = "application/json")
    public void signUp(@RequestBody UserDto userDto) {
        try {
            userService.signUp(userDto);
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
