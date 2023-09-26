package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyManagerDto {
    private String name;
    private String description;
    private String city;
    @JsonProperty("manager_id")
    private Long managerId;


    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
