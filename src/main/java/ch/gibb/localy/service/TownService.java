package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.LogInDto;
import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.MessageMapper;
import ch.gibb.localy.data.entity.mapper.TownMapper;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    public Town create(Town town) {
        townRepository.save(town);
        return town;
    }

    public List<TownDto> findAll() {
        List<TownDto> towns = new ArrayList<>();
        for ( Town t :townRepository.findAll()) {
            towns.add(TownMapper.toDto(t));
        }
        return towns;
    }


    public Town findById(Integer id) {
        return townRepository.findById(id).orElseThrow();
    }

    public void update(TownDto townDto) {
        townRepository.save(TownMapper.fromDto(townDto));
    }

    public void deleteById(Integer id) {
        townRepository.deleteById(id);
    }

}

