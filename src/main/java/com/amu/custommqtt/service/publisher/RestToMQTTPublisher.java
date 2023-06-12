package com.amu.custommqtt.service.publisher;

import com.amu.custommqtt.client.MqttClientFactory;
import com.amu.custommqtt.service.MqttServiceStarter;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestToMQTTPublisher implements MqttServiceStarter {

	@Getter
    private final MqttClientFactory mqttClientFactory;

	@Getter
	private MqttClient client = null;

	private Logger LOG = LoggerFactory.getLogger(getClass());
	@Setter
	private String mqttTopic;

    @Autowired
    public RestToMQTTPublisher(MqttClientFactory mqttClientFactory) {
        this.mqttClientFactory = mqttClientFactory;
		this.client = mqttClientFactory.provideClient();
    }

    public void publish(String topicSuffix, String content) {
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        message.setQos(2);
        try {
            String topic0 = mqttTopic + topicSuffix;
            LOG.info("topic0 :" + topic0);
            if (client.isConnected()) {
                LOG.info("Connection Status :" + client.isConnected());
            }
            client.publish(topic0, message);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
