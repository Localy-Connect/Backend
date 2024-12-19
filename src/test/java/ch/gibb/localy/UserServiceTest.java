package ch.gibb.localy;

import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.repository.UserRepository;
import ch.gibb.localy.service.TokenService;
import ch.gibb.localy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignIn_Success() {
        // Arrange
        LogInDto logInDto = new LogInDto("testUser", "password123");
        User user = new User();
        user.setName("testUser");
        user.setPassword("encodedPassword");
        Token token = new Token("testToken", user);

        when(userRepository.findByName("testUser")).thenReturn(user);
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn(token);

        // Act
        Token result = userService.signIn(logInDto);

        // Assert
        assertNotNull(result);
        assertEquals("testToken", result.token());
        verify(userRepository).findByName("testUser");
        verify(passwordEncoder).matches("password123", "encodedPassword");
        verify(tokenService).generateToken(user);
    }

    @Test
    void testSignIn_Failure_InvalidPassword() {
        // Arrange
        LogInDto logInDto = new LogInDto("testUser", "wrongPassword");
        User user = new User();
        user.setName("testUser");
        user.setPassword("encodedPassword");

        when(userRepository.findByName("testUser")).thenReturn(user);
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.signIn(logInDto));
        verify(userRepository).findByName("testUser");
        verify(passwordEncoder).matches("wrongPassword", "encodedPassword");
        verifyNoInteractions(tokenService);
    }

    @Test
    void testSignUp_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("newUser");
        userDto.setPassword("password123");

        User user = new User();
        user.setName("newUser");
        user.setPassword("encodedPassword");

        when(userRepository.findByName("newUser")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.signUp(userDto);

        // Assert
        verify(userRepository).findByName("newUser");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testSignUp_Failure_UserAlreadyExists() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("existingUser");
        userDto.setPassword("password123");

        User existingUser = new User();
        existingUser.setName("existingUser");

        when(userRepository.findByName("existingUser")).thenReturn(existingUser);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.signUp(userDto));
        verify(userRepository).findByName("existingUser");
        verifyNoInteractions(passwordEncoder);
        verifyNoMoreInteractions(userRepository);
    }
}
