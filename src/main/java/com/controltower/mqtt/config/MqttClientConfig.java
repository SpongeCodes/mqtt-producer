package com.controltower.mqtt.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttClientConfig {

    private MqttClient mqttClient;
    @PostConstruct
    void init() {

    }

    @PreDestroy
    void destroy() {

    }


    @Bean
    public MqttClient mqttClient() {
        return mqttClient;
    }

}
