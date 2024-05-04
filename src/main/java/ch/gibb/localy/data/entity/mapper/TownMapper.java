package ch.gibb.localy.data.entity.mapper;


import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;

import java.util.ArrayList;
import java.util.List;

public class TownMapper {
    public static Town fromDto(TownDto townDto) {
        Town town = new Town();

        town.setId(townDto.getId());
        town.setName(townDto.getName());
        List<Message> messages = new ArrayList<>();
        for (MessageDto m : townDto.getMessages()) {
            messages.add(MessageMapper.fromDto(m));
        }
        town.setMessages(messages);

        return town;
    }

    public static TownDto toDto(Town town) {
        TownDto townDto = new TownDto();

        townDto.setId(town.getId());
        townDto.setName(town.getName());
        List<MessageDto> messages = new ArrayList<>();
        for (Message m : town.getMessages()) {
            messages.add(MessageMapper.toDto(m));
        }
        townDto.setMessages(messages);

        return townDto;
    }

}
