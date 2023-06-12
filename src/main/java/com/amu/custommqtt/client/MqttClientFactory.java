package com.amu.custommqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttClientFactory {

    @Value("${mqtt.username}")
    private String mqttUserName;
    @Value("${mqtt.password}")
    private String mqttPassword;
    @Value("${mqtt.host}")
    private String mqttIpAddress = "localhost";
    @Value("${mqtt.port}")
    private String mqttPort = "1883";

    public MqttClient provideClient(){
        MqttClient client = null;

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            client = new MqttClient("tcp://" + mqttIpAddress + ":" + mqttPort, MqttClient.generateClientId(),
                    persistence);
        } catch (MqttException e1) {
            e1.printStackTrace();
        }

        return client;
    }

    public MqttConnectOptions provideConnectOptions(){

        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setMaxInflight(3000);
        connectOptions.setAutomaticReconnect(true);
        connectOptions.setUserName(mqttUserName);
        connectOptions.setPassword(mqttPassword.toCharArray());

        return connectOptions;
    }
}
