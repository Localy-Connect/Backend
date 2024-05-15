package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.security.AuthInfo;
import ch.gibb.localy.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/towns")
public class TownController {

    @Autowired
    private TownService townService;


    @GetMapping("/{id}")
    public TownDto findById(@PathVariable Integer id) {
        try {
            return townService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @GetMapping
    public List<TownDto> findAll() {
        try {
            return townService.findAll();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            townService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApplicationUser could not be deleted");
        }
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody TownDto townDto) {
        try {
            townService.update(townDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody TownDto townDto) {
        try {
            townService.create(townDto);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }


}
