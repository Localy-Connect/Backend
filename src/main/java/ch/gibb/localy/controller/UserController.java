package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.UserMapper;
import ch.gibb.localy.data.repository.UserRepository;
import ch.gibb.localy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("{id}")
    public UserDto findById(@PathVariable Integer id) {
        try {
            return UserMapper.toDto(userRepository.findUserById(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }
}
