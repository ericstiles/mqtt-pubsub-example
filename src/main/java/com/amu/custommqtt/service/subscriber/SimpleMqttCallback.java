package com.amu.custommqtt.service.subscriber;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.Timestamp;

@Slf4j
public class SimpleMqttCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("connectionLost :" + throwable.getMessage()+" :"+throwable.toString());
        log.info("Kick off trying to reconnect");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("message arrived: {}", mqttMessage.toString());
        String time = new Timestamp(System.currentTimeMillis()).toString();
        log.info("Received a Message!" +
                "\n\tTime:\t\t" + time +
                "\n\tId:\t\t" + mqttMessage.getId() +
                "\n\tMessage:\t\t" + new String(mqttMessage.getPayload()) +
                "\n\tQoS:\t\t" + mqttMessage.getQos());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete: {}", iMqttDeliveryToken.toString());
    }
}
