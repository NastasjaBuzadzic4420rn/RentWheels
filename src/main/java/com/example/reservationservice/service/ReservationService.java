package com.example.reservationservice.service;

import com.example.reservationservice.domain.Reservation;
import com.example.reservationservice.dto.CompanyVehicleDto;
import com.example.reservationservice.dto.CreateReservationDto;
import com.example.reservationservice.dto.EditReservationDto;
import com.example.reservationservice.dto.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    Page<ReservationDto> findAll(Pageable pageable);
    Page<ReservationDto> findAllByUser(Long id, Pageable pageable);
    Page<ReservationDto> findAllByCompanyVehicle(Long companyVehicleId, Pageable pageable);

    ReservationDto findById(Long id);

    ReservationDto add(CreateReservationDto createReservationDto);

    ReservationDto edit(Long id, EditReservationDto editReservationDto);

    ReservationDto cancel(Long id);
}
