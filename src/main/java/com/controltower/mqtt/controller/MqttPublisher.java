package com.controltower.mqtt.controller;

import com.controltower.mqtt.DTO.TrackingDTO;
import com.controltower.mqtt.utils.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Controller
@Slf4j
public class MqttPublisher {

    @Autowired
    private MqttClient mqttClient;

    @PostMapping("/publish")
    public ResponseEntity<TrackingDTO> publishToMqtt(@RequestBody TrackingDTO trackingDTO) {

        trackingDTO.setTimestamp(Instant.now().getEpochSecond());
        try {
            String topic= "iot";
            String message = JSONUtils.convertToJson(trackingDTO);
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(2);
            mqttMessage.setPayload(message.getBytes(StandardCharsets.UTF_8));
            mqttMessage.setRetained(true);
            mqttClient.publish(topic,mqttMessage);
        } catch (MqttException e) {
           log.error("Mqtt error in publishing:");
        } catch (JsonProcessingException e) {
            log.error("error in creating json string: ");
        }
        log.info("Message sent to broker successfully");

        return ResponseEntity.ok(trackingDTO);
    }


}
