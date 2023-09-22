package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDto {
    private Long id;
    private String manufacturer;
    private String model;
    @JsonProperty("vehicle_type_id")
    private Long vehicleTypeId;
}
