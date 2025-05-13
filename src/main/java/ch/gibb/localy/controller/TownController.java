package ch.gibb.localy.controller;

import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/towns")
public class TownController {

    @Autowired
    private TownService townService;

    @PostMapping
    public ResponseEntity<Town> createTown(@RequestParam String name) {
        return ResponseEntity.ok(townService.createTown(name));
    }

    @GetMapping("/{id}")
    public List<Town> getTowns() {
        return townService.getTowns();
    }

    @GetMapping("/{id}/residents")
    public ResponseEntity<Set<UserInfo>> getResidents(@PathVariable Integer id) {
        Set<UserInfo> residents = townService.getResidents(id);
        return ResponseEntity.ok(residents);
    }
}
