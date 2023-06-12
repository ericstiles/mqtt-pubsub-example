package com.amu.custommqtt;

import com.amu.custommqtt.service.subscriber.RestMQTTSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amu.custommqtt.service.publisher.RestToMQTTPublisher;

@SpringBootApplication
public class RestMqttApplication implements CommandLineRunner{

	@Autowired 
	private RestToMQTTPublisher mqttPublisher;

	@Autowired
	private RestMQTTSubscriber mqttSubscriber;

	
	public static void main(String[] args) {
		SpringApplication.run(RestMqttApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		mqttPublisher.startMqtt(Constants.MQTT_TOPIC_0_PUBLISH);
		mqttSubscriber.startMqtt(Constants.MQTT_TOPIC_0_SUBSCRIBE);
		mqttSubscriber.setSubscriber();
	}

}
