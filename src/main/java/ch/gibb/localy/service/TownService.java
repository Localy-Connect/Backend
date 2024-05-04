package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.mapper.TownMapper;
import ch.gibb.localy.data.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    public TownDto create(TownDto townDto) {
        townRepository.save(TownMapper.fromDto(townDto));
        return townDto;
    }

    public List<TownDto> findAll() {
        List<TownDto> towns = new ArrayList<>();
        for (Town t : townRepository.findAll()) {
            towns.add(TownMapper.toDto(t));
        }
        return towns;
    }


    public TownDto findById(Integer id) {
        return TownMapper.toDto(townRepository.findById(id).orElseThrow());
    }

    public void update(TownDto townDto) {
        townRepository.save(TownMapper.fromDto(townDto));
    }

    public void deleteById(Integer id) {
        townRepository.deleteById(id);
    }

}

