package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.UserInfoDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInfoServiceTest {

    @Mock private UserInfoRepository userInfoRepo;
    @Mock private TownRepository townRepo;

    @InjectMocks private UserInfoService userInfoService;

    UserInfoDto userInfoDto = new UserInfoDto();

    @BeforeEach void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldSaveAndReturn() {
        Town town = new Town(); town.setId(8); town.setName("X");
        when(townRepo.findById(8)).thenReturn(Optional.of(town));
        when(userInfoRepo.save(any())).thenAnswer(inv -> {
            UserInfo u = inv.getArgument(0);
            u.setId(20);
            return u;
        });

        UserInfo ui = userInfoService.createUser("alex", 8);
        assertNotNull(ui);
        assertEquals(20, ui.getId());
        assertEquals("alex", ui.getUsername());
        assertEquals(town, ui.getTown());
        verify(userInfoRepo).save(any(UserInfo.class));
    }

    @Test
    void updateUser_shouldModifyAndReturn() {
        userInfoDto.setUserId(30);
        userInfoDto.setUsername("newName");
        userInfoDto.setTownId(9);

        Town newTown = new Town(); newTown.setId(9);
        UserInfo existing = new UserInfo(); existing.setId(30); existing.setUsername("old");
        when(userInfoRepo.findById(30)).thenReturn(Optional.of(existing));
        when(townRepo.findById(9)).thenReturn(Optional.of(newTown));
        when(userInfoRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        UserInfo updated = userInfoService.updateUser(userInfoDto);
        assertEquals(30, updated.getId());
        assertEquals("newName", updated.getUsername());
        assertEquals(newTown, updated.getTown());
        verify(userInfoRepo).save(existing);
    }
}
