package ch.gibb.localy.data.entity.mapper;


import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.User;

public class UserMapper {
    public static User fromDto(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNr(userDto.getPhoneNr());
        if (userDto.getTown() != null){
            user.setTown(TownMapper.fromDto(userDto.getTown()));
        }
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNr(user.getPhoneNr());
        if (user.getTown() != null){
            userDto.setTown(TownMapper.toDto(user.getTown()));
        }
        userDto.setEmail(user.getEmail());

        return userDto;
    }

}
