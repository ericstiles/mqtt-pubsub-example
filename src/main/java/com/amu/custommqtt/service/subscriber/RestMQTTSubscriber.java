package com.amu.custommqtt.service.subscriber;

import com.amu.custommqtt.client.MqttClientFactory;
import com.amu.custommqtt.service.MqttServiceStarter;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestMQTTSubscriber implements MqttServiceStarter {

	@Getter
	private final MqttClientFactory mqttClientFactory;

	@Getter
	private MqttClient client = null;

	private Logger LOG = LoggerFactory.getLogger(getClass());
	@Setter
	private String mqttTopic;

	@Autowired
	public RestMQTTSubscriber(MqttClientFactory mqttClientFactory) {
		this.mqttClientFactory = mqttClientFactory;
		this.client = mqttClientFactory.provideClient();
	}
	public void setSubscriber() {
		getClient().setCallback(new SimpleMqttCallback());
		try {
			getClient().subscribe("#");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
