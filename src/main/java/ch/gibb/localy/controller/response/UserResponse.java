package ch.gibb.localy.controller.response;

import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.entity.mapper.TownMapper;

public class UserResponse {

    public final long id;
    public final String name;
    public final String phoneNr;
    public final String email;
    public Town town;

    public UserResponse(UserDto user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNr = user.getPhoneNr();
        this.email = user.getEmail();
        if (user.getTown() != null) {
            this.town = TownMapper.fromDto(user.getTown());
            this.town.setMessages(null);
            this.town.setUsers(null);
        }
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNr = user.getPhoneNr();
        this.email = user.getEmail();
        if (user.getTown() != null) {
            this.town = user.getTown();
            this.town.setMessages(null);
            this.town.setUsers(null);
        }
    }
}
