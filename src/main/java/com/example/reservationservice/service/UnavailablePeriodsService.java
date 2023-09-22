package com.example.reservationservice.service;

import com.example.reservationservice.dto.UnavailablePeriodDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnavailablePeriodsService {
    Page<UnavailablePeriodDto> findAll(Pageable pageable);

    UnavailablePeriodDto findById(Long id);

    Page<UnavailablePeriodDto> findByCompanyVehicleId(Long companyVehicleId, Pageable pageable);

    UnavailablePeriodDto add(UnavailablePeriodDto unavailablePeriodDto);

    UnavailablePeriodDto edit(Long id, UnavailablePeriodDto unavailablePeriodDto);
}
