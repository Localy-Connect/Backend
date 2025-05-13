package ch.gibb.localy.controller;

import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PutMapping("/{id}")
    public ResponseEntity<UserInfo> updateUser(
            @PathVariable Integer id,
            @RequestParam String username,
            @RequestParam Integer townId) {
        UserInfo ui = userInfoService.updateUser(id, username, townId);
        return ResponseEntity.ok(ui);
    }
}
