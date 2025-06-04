package ch.gibb.localy.controller;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.dto.UserInfoDto;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.service.UserInfoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
            @RequestBody UserInfoDto dto,
            @RequestHeader("Idempotency-Key") String idempotencyKey) {

        UserInfo ui = userInfoService.updateUser(dto);
        return ResponseEntity
                .ok()
                .header("Idempotency-Key", idempotencyKey)
                .body(ui);
    }
}
