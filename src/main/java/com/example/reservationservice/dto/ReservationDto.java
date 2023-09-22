package com.example.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("company_vehicle_id")
    private Long companyVehicleId;
    @JsonProperty("price_with_discount")
    private double priceWithDiscount;
    @JsonProperty("start_date")
    private Long startDate;
    @JsonProperty("end_date")
    private Long endDate;
    private boolean active;
}
