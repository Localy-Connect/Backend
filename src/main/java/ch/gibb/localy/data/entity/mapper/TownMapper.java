package ch.gibb.localy.data.entity.mapper;


import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.dto.UserDto;
import ch.gibb.localy.data.entity.Town;
import ch.gibb.localy.data.entity.User;

public class TownMapper {
    public static Town fromDto(TownDto townDto) {
        Town town = new Town();

        town.setId(townDto.getId());
        town.setName(townDto.getName());
        town.setMessages(townDto.getMessages());
        town.setUsers(townDto.getUsers());

        return town;
    }

    public static TownDto toDto(Town town) {
        TownDto townDto = new TownDto();

        townDto.setId(town.getId());
        townDto.setName(town.getName());
        townDto.setMessages(town.getMessages());
        townDto.setUsers(town.getUsers());

        return townDto;
    }

}
