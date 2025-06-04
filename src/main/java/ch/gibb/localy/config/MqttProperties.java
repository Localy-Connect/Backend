package ch.gibb.localy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {
    private String brokerUrl;
    private String clientId;
    private String topicUserUpdate;
    private String topicUserInfo;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTopicUserUpdate() {
        return topicUserUpdate;
    }

    public void setTopicUserUpdate(String topicUserUpdate) {
        this.topicUserUpdate = topicUserUpdate;
    }

    public String getTopicUserInfo() {
        return topicUserInfo;
    }

    public void setTopicUserInfo(String topicUserInfo) {
        this.topicUserInfo = topicUserInfo;
    }
}
