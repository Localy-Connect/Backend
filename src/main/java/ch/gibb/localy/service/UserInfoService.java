package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.UserInfoDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepo;

    @Autowired
    private TownRepository townRepo;

    public UserInfo createUser(String username, Integer townId) {
        Town town = townRepo.findById(townId).orElseThrow();
        UserInfo ui = new UserInfo();
        ui.setUsername(username);
        ui.setTown(town);
        return userInfoRepo.save(ui);
    }

    public Optional<UserInfo> getUser(Integer id) {
        return userInfoRepo.findById(id);
    }

    public UserInfo updateUser(UserInfoDto userInfoDto) {
        UserInfo ui = userInfoRepo.findById(userInfoDto.getUserId()).orElseThrow();
        ui.setUsername(userInfoDto.getUsername());
        ui.setTown(townRepo.findById(userInfoDto.getTownId()).orElse(null));
        return userInfoRepo.save(ui);
    }
}
