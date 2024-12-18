package ch.gibb.localy.service;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.TownMapper;
import ch.gibb.localy.data.repository.MessageRepository;
import ch.gibb.localy.data.repository.TownRepository;
import ch.gibb.localy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;


    public TownDto createTown(TownDto townDto, User user) {
        if (townRepository.findByName(townDto.getName()).isPresent()) {
            throw new NoSuchElementException("Town with name " + townDto.getName() + " already exists");
        }

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

    @Transactional
    public void deleteById(Integer id) {
        // Find the town by id
        Town town = townRepository.findById(id).orElse(null);
        if (town != null) {
            // Delete all messages associated with the town
            messageRepository.deleteAll(town.getMessages());

            // Dissociate all persons from the town
            town.getUsers().forEach(user -> {
                user.setTown(null);
                userRepository.save(user); // Save the updated user
            });

            // Clear the list of users to make sure no entity is holding a reference
            town.getUsers().clear();

            // Finally, delete the town itself
            townRepository.delete(town);
        }
    }

    @Transactional
    public void joinTown(Integer townId, User user) {
        if (user.getTown() != null) {
            throw new IllegalArgumentException("User is already in a Town");
        }
        Town town = townRepository.findById(townId)
                .orElseThrow(() -> new NoSuchElementException("Town with id " + townId + " not found"));
        town.getUsers().add(user);
        user.setTown(town);
        userRepository.save(user);
        TownMapper.toDto(townRepository.save(town));
    }

    @Transactional
    public void leaveTown(Integer townId, User user) {
        if (user.getTown() == null) {
            throw new IllegalArgumentException("User is not in a Town");
        }
        Town town = townRepository.findById(townId)
                .orElseThrow(() -> new NoSuchElementException("Town with id " + townId + " not found"));
        town.getUsers().remove(user);
        user.setTown(null);
        userRepository.save(user);
        TownMapper.toDto(townRepository.save(town));
    }
}
