package ch.gibb.localy.service;

import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TownService {

    @Autowired
    private TownRepository townRepo;

    @Autowired
    private UserInfoRepository userInfoRepo;

    public Town createTown(String name) {
        Town t = new Town();
        t.setName(name);
        return townRepo.save(t);
    }

    public List<Town> getTowns() {
        return townRepo.findAll();
    }

    public Set<UserInfo> getResidents(Integer townId) {
        Town t = townRepo.findById(townId).orElseThrow();
        return t.getUserInfos();
    }
}
