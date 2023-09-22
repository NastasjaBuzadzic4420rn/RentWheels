package com.example.reservationservice.service;

import com.example.reservationservice.dto.VehicleDto;
import com.example.reservationservice.dto.VehicleTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleTypeService {
    Page<VehicleTypeDto> findAll(Pageable pageable);

    VehicleTypeDto findById(Long id);

    VehicleTypeDto add(VehicleTypeDto vehicleTypeDto);

    VehicleTypeDto edit(Long id, VehicleTypeDto vehicleTypeDto);
}
