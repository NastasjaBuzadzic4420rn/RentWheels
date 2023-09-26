package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("num_of_vehicles")
    private int numOfVehicles;
    private String city;
    @JsonProperty("manager_id")
    private Long managerId;
    private boolean approved;
}
