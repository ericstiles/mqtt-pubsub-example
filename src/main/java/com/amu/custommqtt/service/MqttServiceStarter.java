package com.amu.custommqtt.service;

import com.amu.custommqtt.client.MqttClientFactory;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;


public interface MqttServiceStarter {
    
    default void startMqtt(String topic) {
        setMqttTopic(topic);

        try {
            IMqttToken mqttConnectionToken = getClient().connectWithResult(getMqttClientFactory().provideConnectOptions());
            //LOG connection status
            getClient().subscribe("#");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    void setMqttTopic(String topic);

    MqttClient getClient();

    MqttClientFactory getMqttClientFactory();
}
