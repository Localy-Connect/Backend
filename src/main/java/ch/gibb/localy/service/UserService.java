package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.UserMapper;
import ch.gibb.localy.data.repository.UserRepository;
import com.sun.jdi.request.InvalidRequestStateException;
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

    public User signUp(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return user;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
