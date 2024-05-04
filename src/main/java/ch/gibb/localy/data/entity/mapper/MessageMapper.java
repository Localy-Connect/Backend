package ch.gibb.localy.data.entity.mapper;


import ch.gibb.localy.data.dto.MessageDto;
import ch.gibb.localy.data.entity.Message;

public class MessageMapper {
    public static Message fromDto(MessageDto messageDto) {
        Message message = new Message();

        message.setId(messageDto.getId());
        message.setTitle(messageDto.getTitle());
        message.setUser(UserMapper.fromDto(messageDto.getUser()));

        return message;
    }

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();

        messageDto.setId(message.getId());
        messageDto.setTitle(message.getTitle());
        messageDto.setUser(UserMapper.toDto(message.getUser()));

        return messageDto;
    }

}
