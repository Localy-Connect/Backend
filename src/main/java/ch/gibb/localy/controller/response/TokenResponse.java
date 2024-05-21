package ch.gibb.localy.controller.response;


import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.data.entity.mapper.UserMapper;

public class TokenResponse {
    public final String token;
    public final UserResponse user;

    public TokenResponse(Token token) {
        this.token = token.token();
        this.user = new UserResponse(token.user());
    }
}
