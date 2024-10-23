package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.UserMapper;
import ch.gibb.localy.data.repository.UserRepository;
import ch.gibb.localy.security.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
            throw new IllegalArgumentException("Invalid username or password");
        }

        return tokenService.generateToken(user);
    }

    // zum einlogen eines Users darin wird das Passwort gehasht und der User initial gespeichert
    public void signUp(UserDto userDto) {
        if (userRepository.findByName(userDto.getName()) != null) {
            throw new IllegalArgumentException("User with this username already exists");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(UserMapper.fromDto(userDto));
    }

    public List<UserDto> findAll() {
        List<UserDto> list = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            list.add(UserMapper.toDto(u));
        }
        return list;
    }

    public UserDto findById(Long id) {
        return UserMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id = " + id)));
    }

    public void update(UserDto userDto) {
        if (!Objects.equals(AuthInfo.getUser().getId(), userDto.getId())) {
            throw new IllegalArgumentException("User can only change his profile");
        }
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id = " + userDto.getId()));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNr(userDto.getPhoneNr());
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void changePassword(Long id, String currentPassword, String newPassword) {
        if (Objects.equals(currentPassword, newPassword)) {
            throw new IllegalArgumentException("New password matching old password");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id = " + id));
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("Wrong Password");
        }
        newPassword = passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
