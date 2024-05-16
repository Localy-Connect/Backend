package ch.gibb.localy.data.entity.mapper;

import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;
import ch.gibb.localy.data.entity.Town;

public class MessageMapper {
    public static MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setTitle(message.getTitle());
        dto.setText(message.getText());
        dto.setTownId(message.getTown() != null ? message.getTown().getId() : null);
        return dto;
    }

    public static Message fromDto(MessageDto dto, Town town) {
        Message message = new Message();
        message.setId(dto.getId());
        message.setTitle(dto.getTitle());
        message.setText(dto.getText());
        message.setTown(town);
        return message;
    }
}
