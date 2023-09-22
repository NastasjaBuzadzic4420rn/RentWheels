package com.example.reservationservice.service;

import com.example.reservationservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyVehicleService {
    Page<CompanyVehicleDto> findAll(Pageable pageable);

    Page<CompanyVehicleDto> findAllByCompanyId(Long companyId, Pageable pageable);

    CompanyVehicleDto findById(Long id);

    CompanyVehicleDto add(CompanyVehicleDto companyVehicleDto);

    CompanyVehicleDto edit(Long id, CompanyVehicleDto companyVehicleDto);
}
