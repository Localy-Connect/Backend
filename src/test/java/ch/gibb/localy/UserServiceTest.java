package ch.gibb.localy;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.repository.UserRepository;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthInfo authInfo;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;
    private Town town;
    private TownDto townDto;

    @BeforeEach
    public void setUp() {
        // Create a town instance and set its properties
        town = new Town();
        town.setId(1L);
        town.setName("Test Town");

        // Create a town DTO instance and set its properties
        townDto = new TownDto();
        townDto.setId(1L);
        townDto.setName("Test Town");

        // Create a user instance and associate it with the town
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setPassword("password123");
        user.setPhoneNr("1234567890");
        user.setTown(town);
        user.setEmail("john.doe@example.com");

        // Create a UserDto instance and set its properties, including the town DTO
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setPassword("newpassword456"); // Updated password
        userDto.setPhoneNr("0987654321");
        userDto.setTown(townDto);
        userDto.setEmail("john.doe@example.com");


        // Mock the behavior of the Authentication and SecurityContext
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);

        // Mock the behavior of the AuthInfo class
        when(authInfo.getUser()).thenReturn(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    public void testUpdate() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setPassword(userDto.getPassword());
            savedUser.setPhoneNr(userDto.getPhoneNr());
            savedUser.setTown(town);
            return savedUser;
        });

        userService.update(userDto);

        verify(userRepository, times(1)).save(argThat(savedUser ->
                savedUser.getName().equals(userDto.getName()) &&
                        savedUser.getPhoneNr().equals(userDto.getPhoneNr()) &&
                        savedUser.getEmail().equals(userDto.getEmail()) &&
                        savedUser.getTown().getId().equals(userDto.getTown().getId()) &&
                        savedUser.getPassword().equals(userDto.getPassword()) // Check for password update
        ));

        assertEquals("newpassword456", user.getPassword());
        assertEquals("0987654321", user.getPhoneNr());
    }
}
