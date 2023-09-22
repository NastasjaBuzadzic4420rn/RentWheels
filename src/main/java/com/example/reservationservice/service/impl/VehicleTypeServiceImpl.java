package com.example.reservationservice.service.impl;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.domain.VehicleType;
import com.example.reservationservice.dto.VehicleTypeDto;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.mapper.VehicleTypeMapper;
import com.example.reservationservice.repository.VehicleTypeRepository;
import com.example.reservationservice.service.VehicleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private VehicleTypeRepository repository;
    private VehicleTypeMapper mapper;

    public VehicleTypeServiceImpl(VehicleTypeRepository repository, VehicleTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<VehicleTypeDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }

    @Override
    public VehicleTypeDto findById(Long id) {
        VehicleType vehicleType = repository.findById(id).get();
        return mapper.objectToDto(vehicleType);
    }

    @Override
    public VehicleTypeDto add(VehicleTypeDto vehicleTypeDto) {
        VehicleType vehicleType = mapper.dtoToObject(vehicleTypeDto);
        repository.save(vehicleType);
        return mapper.objectToDto(vehicleType);
    }

    @Override
    public VehicleTypeDto edit(Long id, VehicleTypeDto vehicleTypeDto) {
        VehicleType vehicleType = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Vehicle type with id: %s not found.", id)));
        vehicleType.setName(vehicleTypeDto.getName());
        repository.save(vehicleType);
        return mapper.objectToDto(vehicleType);
    }
}
