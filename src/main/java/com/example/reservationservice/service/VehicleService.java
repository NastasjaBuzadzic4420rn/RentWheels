package com.example.reservationservice.service;

import com.example.reservationservice.dto.VehicleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    Page<VehicleDto> findAll(Pageable pageable);

    VehicleDto findById(Long id);

    VehicleDto add(VehicleDto vehicleDto);

    VehicleDto edit(Long id, VehicleDto vehicleDto);
}
