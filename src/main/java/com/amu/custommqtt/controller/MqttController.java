package com.amu.custommqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amu.custommqtt.dto.MessageDTO;
import com.amu.custommqtt.service.publisher.RestToMQTTPublisher;

@RestController
public class MqttController {

	@Autowired
	private RestToMQTTPublisher mqttPublisher;

	@PutMapping("/start")
	public String startMqtt() {
		return null;
	}

	@PostMapping("/publish")
	public String publish(@RequestBody MessageDTO messageDTO) {
		mqttPublisher.publish(messageDTO.getTopic(), messageDTO.getMessage());
		return "Success";
	}

}
