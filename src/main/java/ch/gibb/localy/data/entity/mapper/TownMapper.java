package ch.gibb.localy.data.entity.mapper;

import ch.gibb.localy.data.dto.TownDto;
import ch.gibb.localy.data.entity.Town;

import java.util.stream.Collectors;

public class TownMapper {
    public static TownDto toDto(Town town) {
        TownDto dto = new TownDto();
        dto.setId(town.getId());
        dto.setName(town.getName());
        if (town.getMessages() != null){
            dto.setMessages(town.getMessages().stream().map(MessageMapper::toDto).collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Town fromDto(TownDto dto) {
        Town town = new Town();
        town.setId(dto.getId());
        town.setName(dto.getName());
        if (dto.getMessages() != null) {
            town.setMessages(dto.getMessages().stream()
                    .map(messageDto -> MessageMapper.fromDto(messageDto, town))
                    .collect(Collectors.toSet()));
        }
        return town;
    }
}
