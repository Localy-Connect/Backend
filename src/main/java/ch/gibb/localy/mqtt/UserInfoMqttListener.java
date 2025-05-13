package ch.gibb.localy.mqtt;

import ch.gibb.localy.data.dto.UserInfoDto;
import ch.gibb.localy.data.entity.UserInfo;
import ch.gibb.localy.data.repository.UserInfoRepository;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserInfoMqttListener {

    private final UserInfoRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserInfoMqttListener(UserInfoRepository repo) {
        this.repo = repo;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handle(String payload, @Header(MqttHeaders.TOPIC) String topic) throws Exception {
        UserInfoDto dto = mapper.readValue(payload, UserInfoDto.class);
        UserInfo ui = repo.findById(dto.getUserId()).orElse(new UserInfo());
        ui.setId(dto.getUserId());
        ui.setUsername(dto.getUsername());
        repo.save(ui);
    }
}
