package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreateDto {
    private Long id;
    private String name;
    private String description;
    private String city;
    @JsonProperty("manager_id")
    private Long managerId;
}
