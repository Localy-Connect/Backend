package ch.gibb.localy.controller.response;


import ch.gibb.localy.data.entity.Token;

public class TokenResponse {
    public final String token;
    public final UserResponse user;

    public TokenResponse(Token token) {
        this.token = token.token();
        this.user = new UserResponse(token.user());
    }
}
