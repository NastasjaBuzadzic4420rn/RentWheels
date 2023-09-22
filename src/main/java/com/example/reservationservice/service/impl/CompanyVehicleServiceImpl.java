package com.example.reservationservice.service.impl;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.domain.CompanyVehicle;
import com.example.reservationservice.domain.Vehicle;
import com.example.reservationservice.domain.VehicleType;
import com.example.reservationservice.dto.*;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.mapper.CompanyMapper;
import com.example.reservationservice.mapper.CompanyVehicleMapper;
import com.example.reservationservice.mapper.VehicleMapper;
import com.example.reservationservice.mapper.VehicleTypeMapper;
import com.example.reservationservice.repository.CompanyRepository;
import com.example.reservationservice.repository.CompanyVehicleRepository;
import com.example.reservationservice.repository.VehicleRepository;
import com.example.reservationservice.repository.VehicleTypeRepository;
import com.example.reservationservice.service.CompanyVehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyVehicleServiceImpl implements CompanyVehicleService {

    private CompanyVehicleRepository repository;
    private CompanyVehicleMapper mapper;

    public CompanyVehicleServiceImpl(CompanyVehicleRepository repository, CompanyVehicleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<CompanyVehicleDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }

    @Override
    public Page<CompanyVehicleDto> findAllByCompanyId(Long companyId, Pageable pageable) {
        return repository.findAllByCompanyId(companyId, pageable).map(companyVehiclePage -> companyVehiclePage.map(mapper::objectToDto))
                .orElseThrow(() -> new NotFoundException(String
                        .format("Company vehicle with companyId: %d not found.", companyId)));
    }

    @Override
    public CompanyVehicleDto findById(Long id) {
        CompanyVehicle companyVehicle = repository.findById(id).get();
        return mapper.objectToDto(companyVehicle);
    }


    @Override
    public CompanyVehicleDto add(CompanyVehicleDto companyVehicleDto) {
        CompanyVehicle companyVehicle = mapper.dtoToObject(companyVehicleDto);
        repository.save(companyVehicle);
        return mapper.objectToDto(companyVehicle);
    }

    @Override
    public CompanyVehicleDto edit(Long id, CompanyVehicleDto companyVehicleDto) {
        CompanyVehicle companyVehicle = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Company vehicle with id: %s not found.", id)));
        companyVehicle.setCompanyId(companyVehicleDto.getCompanyId());
        companyVehicle.setVehicleId(companyVehicleDto.getVehicleId());
        companyVehicle.setPrice(companyVehicleDto.getPrice());
        repository.save(companyVehicle);
        return mapper.objectToDto(companyVehicle);
    }
}
