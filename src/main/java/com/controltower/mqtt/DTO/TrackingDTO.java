package com.controltower.mqtt.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDTO {

    private String vehicleId;

    private Double latitude;

    private Double longitude;

    private Double temp;

    private Instant timestamp;

}
