package com.example.reservationservice.repository;

import com.example.reservationservice.domain.Reservation;
import com.example.reservationservice.dto.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Page<Reservation>> findAllByCompanyVehicleIdAndStartDateAndEndDate(Long companyVehicleId, Long startDate, Long endDate, Pageable pageable);
    Optional<Page<Reservation>> findAllByUserId(Long userId, Pageable pageable);
}
