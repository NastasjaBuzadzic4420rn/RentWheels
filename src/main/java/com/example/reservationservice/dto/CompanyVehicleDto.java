package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyVehicleDto {
    private Long id;
    @JsonProperty("company_id")
    private Long companyId;
    @JsonProperty("vehicle_id")
    private Long vehicleId;
    private double price;
}
