package ch.gibb.localy.controller.response;

import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;

public class UserResponse {

    public final long id;
    public final String name;
    public final String phoneNr;
    public final String email;
    public final Town town;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNr = user.getPhoneNr();
        this.email = user.getEmail();
        this.town = user.getTown();
    }
}
