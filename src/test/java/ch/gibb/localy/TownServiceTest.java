package ch.gibb.localy;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserRepository;
import ch.gibb.localy.service.TownService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TownServiceTest {

    @Mock
    private TownRepository townRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TownService townService;

    private Town town;
    private TownDto townDto;
    private User user;

    @BeforeEach
    public void setUp() {
        // Create a Town instance and set its properties
        town = new Town();
        town.setId(1L);
        town.setName("Test Town");
        town.setUsers(Set.of());

        // Create a TownDto instance and set its properties
        townDto = new TownDto();
        townDto.setId(1L);
        townDto.setName("Test Town");

        // Create a User instance
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
    }

    @Test
    public void testCreateTown() {
        when(townRepository.save(any(Town.class))).thenReturn(town);
        when(townRepository.findByName(anyString())).thenReturn(Optional.empty());

        TownDto createdTown = townService.createTown(townDto, user);

        assertNotNull(createdTown);
        assertEquals(townDto.getName(), createdTown.getName());
        verify(townRepository, times(1)).save(any(Town.class));
    }

    @Test
    public void testFindAll() {
        when(townRepository.findAll()).thenReturn(Collections.singletonList(town));

        List<TownDto> towns = townService.findAll();

        assertNotNull(towns);
        assertEquals(1, towns.size());
        verify(townRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(townRepository.findById(anyInt())).thenReturn(Optional.of(town));

        TownDto foundTown = townService.findById(1);

        assertNotNull(foundTown);
        assertEquals(town.getName(), foundTown.getName());
        verify(townRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdate() {
        when(townRepository.save(any(Town.class))).thenReturn(town);

        townService.update(townDto);

        verify(townRepository, times(1)).save(any(Town.class));
    }

    @Test
    public void testDeleteById() {
        doNothing().when(townRepository).deleteById(anyInt());

        townService.deleteById(1);

        verify(townRepository, times(1)).deleteById(1);
    }
}
