package com.example.reservationservice.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long companyVehicleId;
    private double priceWithDiscount;
    private Long startDate;
    private Long endDate;
    private boolean active;
}
