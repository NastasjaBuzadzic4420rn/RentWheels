package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReservationDto{
        @JsonProperty("user_id")
        private Long userId;
        @JsonProperty("company_vehicle_id")
        private Long companyVehicleId;
        @JsonProperty("price")
        private double price;
        @JsonProperty("start_date")
        private Long startDate;
        @JsonProperty("end_date")
        private Long endDate;
}
