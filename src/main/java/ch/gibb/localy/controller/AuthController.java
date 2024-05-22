package ch.gibb.localy.controller;

import ch.gibb.localy.controller.response.TokenResponse;
import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Sign up a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed up successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation")
    })
    @PostMapping(path = "/signup", consumes = "application/json")
    public void signUp(@RequestBody UserDto userDto) {
        try {
            userService.signUp(userDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data integrity violation");
        }
    }

    @Operation(summary = "Sign in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation")
    })
    @PostMapping(path = "/signin", consumes = "application/json")
    public TokenResponse signIn(@RequestBody LogInDto logIn) {
        try {
            return new TokenResponse(userService.signIn(logIn));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data integrity violation");
        }
    }
}
