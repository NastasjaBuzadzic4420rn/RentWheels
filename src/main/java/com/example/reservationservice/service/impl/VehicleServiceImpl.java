package com.example.reservationservice.service.impl;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.domain.Vehicle;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.FilterDto;
import com.example.reservationservice.dto.VehicleDto;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.mapper.VehicleMapper;
import com.example.reservationservice.repository.CompanyRepository;
import com.example.reservationservice.repository.CompanyVehicleRepository;
import com.example.reservationservice.repository.VehicleRepository;
import com.example.reservationservice.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository repository;
    private VehicleMapper mapper;

    public VehicleServiceImpl(VehicleRepository repository, VehicleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<VehicleDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }



    @Override
    public VehicleDto findById(Long id) {
        Vehicle vehicle = repository.findById(id).get();
        return mapper.objectToDto(vehicle);
    }

    @Override
    public VehicleDto add(VehicleDto vehicleDto) {
        Vehicle vehicle = mapper.dtoToObject(vehicleDto);
        repository.save(vehicle);
        return mapper.objectToDto(vehicle);
    }

    @Override
    public VehicleDto edit(Long id, VehicleDto vehicleDto) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Vehicle with id: %s not found.", id)));
        vehicle.setManufacturer(vehicleDto.getManufacturer());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setVehicleTypeId(vehicleDto.getVehicleTypeId());
        repository.save(vehicle);
        return mapper.objectToDto(vehicle);
    }
}
