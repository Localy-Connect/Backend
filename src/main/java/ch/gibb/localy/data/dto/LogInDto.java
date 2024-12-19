package ch.gibb.localy.data.dto;


public class LogInDto {
    private String name;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogInDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
