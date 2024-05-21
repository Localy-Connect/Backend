package ch.gibb.localy.data.dto;

import ch.gibb.localy.data.entity.User;

public class UserDto {

    public Long id;

    private String name;

    private String password;

    private String phoneNr;

    private TownDto town;

    private String email;

    public UserDto(User user) {
        this.name = user.getName();
        this.phoneNr = user.getPhoneNr();
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public TownDto getTown() {
        return town;
    }

    public void setTown(TownDto town) {
        this.town = town;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
