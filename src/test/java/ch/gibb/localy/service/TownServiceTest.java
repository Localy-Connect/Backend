package ch.gibb.localy.service;

import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TownServiceTest {

    @Mock
    private TownRepository townRepo;

    @Mock
    private UserInfoRepository userInfoRepo;

    @InjectMocks
    private TownService townService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTown_shouldSaveAndReturnTown() {
        Town t = new Town();
        t.setName("TestTown");
        when(townRepo.save(any())).thenAnswer(inv -> {
            Town saved = inv.getArgument(0);
            saved.setId(1);
            return saved;
        });

        Town result = townService.createTown("TestTown");
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("TestTown", result.getName());
        verify(townRepo).save(any(Town.class));
    }

    @Test
    void getResidents_shouldReturnUserInfos() {
        Town t = new Town();
        t.setId(2);
        UserInfo u1 = new UserInfo(); u1.setId(10); u1.setUsername("u1");
        UserInfo u2 = new UserInfo(); u2.setId(11); u2.setUsername("u2");
        t.setUserInfos(new HashSet<>(Arrays.asList(u1, u2)));

        when(townRepo.findById(2)).thenReturn(Optional.of(t));

        Set<UserInfo> residents = townService.getResidents(2);
        assertEquals(2, residents.size());
        assertTrue(residents.contains(u1));
        assertTrue(residents.contains(u2));
        verify(townRepo).findById(2);
    }
}
