package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.UserMapper;
import ch.gibb.localy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(TokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Token signIn(LogInDto logInDto) {
        User user = userRepository.findByName(logInDto.getName());
        if (user == null || !passwordEncoder.matches(logInDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("invalid name or password");
        }
        return tokenService.generateToken(user);
    }

    public User signUp(UserDto userDto) {
        if (userRepository.findByName(userDto.getName()) != null) {
            throw new IllegalArgumentException("User with this name already exists");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(UserMapper.fromDto(userDto));
    }

    public List<UserDto> findAll() {
        List<UserDto> towns = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            towns.add(UserMapper.toDto(u));
        }
        return towns;
    }

    public UserDto findById(Integer id) {
        return UserMapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public void update(UserDto userDto) {
        userRepository.save(UserMapper.fromDto(userDto));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
