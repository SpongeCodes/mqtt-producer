package com.controltower.mqtt.controller;

import com.controltower.mqtt.DTO.TrackingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MqttPublisher {

    @PostMapping("/publish")
    public ResponseEntity<TrackingDTO> publishToMqtt(@RequestBody TrackingDTO trackingDTO) {
            return null;
    }


}
