package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.TownMapper;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;
    @Autowired
    private UserRepository userRepository;

    public TownDto createTown(TownDto townDto, User user) {
        Town town = TownMapper.fromDto(townDto);

        townRepository.save(town);
        return TownMapper.toDto(town);
    }

    public List<TownDto> findAll() {
        return townRepository.findAll().stream()
                .map(TownMapper::toDto)
                .collect(Collectors.toList());
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

    public TownDto joinTown(Integer townId, User user) {
        if (user.getTown() != null) {
            throw new IllegalArgumentException("User is already in a Town");
        }
        Town town = townRepository.findById(townId)
                .orElseThrow(() -> new IllegalArgumentException("Town with id " + townId + " not found"));
        town.getUsers().add(user);
        return TownMapper.toDto(townRepository.save(town));
    }
}

