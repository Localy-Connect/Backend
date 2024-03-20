package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.UserMapper;
import ch.gibb.localy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signIn(LogInDto logInDto) {
        User user = userRepository.findByName(logInDto.getName());
        if (Objects.equals(logInDto.getPassword(), user.getPassword())) {
            return user;
        } else return null;
    }

    public UserDto signUp(UserDto userDto) {
        userRepository.save(UserMapper.fromDto(userDto));
        return userDto;
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
