package com.example.reservationservice.repository;

import com.example.reservationservice.domain.UnavailablePeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnavailablePeriodsRepository extends JpaRepository<UnavailablePeriod, Long> {

    Optional<Page<UnavailablePeriod>> findAllByCompanyVehicleId(Long companyVehicleId, Pageable pageable);

    Optional<UnavailablePeriod> findByCompanyVehicleIdAndAndStartDateAndAndEndDate(Long companyVehicleId, Long startDate, Long endDate);
}
