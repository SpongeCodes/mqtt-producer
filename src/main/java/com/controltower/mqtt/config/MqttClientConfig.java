package com.controltower.mqtt.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MqttClientConfig {

    private MqttClient mqttClient;

    @Value("${mqtt.broker.url}")
    private String brokerURL;

    /*@PostConstruct
    void init() {
        // start the connection here
        mqttClient = new MqttClient();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName("controltower");
        options.setPassword("Admin@1234".toCharArray());
        options.setConnectionTimeout(10000);
    }*/

    @PreDestroy
    void destroy() {
        if(mqttClient!=null && mqttClient.isConnected()) {
            try {
                mqttClient.disconnect();
                mqttClient.close();
            } catch (MqttException e) {
                log.debug("Error in destroying MqttConfig bean: ",e);
            }

        }
    }


    @Bean
    public MqttClient mqttClient() {
        try {
            mqttClient = new MqttClient(brokerURL,"mqtt-producer");
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName("controltower");
            options.setPassword("Admin@1234".toCharArray());
            options.setConnectionTimeout(10000);
            mqttClient.connect(options);
        } catch (MqttException e) {
            log.error("Error in starting mqtt connection: ",e);
        }
        log.info("Mqtt connection made... can send messages");
        return mqttClient;
    }

}
