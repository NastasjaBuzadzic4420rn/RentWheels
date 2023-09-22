package com.example.reservationservice.service.impl;

import com.example.reservationservice.domain.UnavailablePeriod;
import com.example.reservationservice.dto.UnavailablePeriodDto;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.mapper.UnavailablePeriodsMapper;
import com.example.reservationservice.repository.UnavailablePeriodsRepository;
import com.example.reservationservice.service.UnavailablePeriodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnavailablePeriodsServiceImpl implements UnavailablePeriodsService {

    private UnavailablePeriodsRepository repository;
    private UnavailablePeriodsMapper mapper;

    public UnavailablePeriodsServiceImpl(UnavailablePeriodsRepository repository, UnavailablePeriodsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<UnavailablePeriodDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }

    @Override
    public UnavailablePeriodDto findById(Long id) {
        UnavailablePeriod unavailablePeriod = repository.findById(id).get();
        return mapper.objectToDto(unavailablePeriod);
    }

    @Override
    public Page<UnavailablePeriodDto> findByCompanyVehicleId(Long companyVehicleId, Pageable pageable) {
        return repository.findAllByCompanyVehicleId(companyVehicleId, pageable).get().map(mapper::objectToDto);
    }

    @Override
    public UnavailablePeriodDto add(UnavailablePeriodDto unavailablePeriodDto) {
        UnavailablePeriod unavailablePeriod = mapper.dtoToObject(unavailablePeriodDto);
        repository.save(unavailablePeriod);
        return mapper.objectToDto(unavailablePeriod);
    }

    @Override
    public UnavailablePeriodDto edit(Long id, UnavailablePeriodDto unavailablePeriodDto) {
        UnavailablePeriod unavailablePeriod = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("UnavailablePeriod with id: %s not found.", id)));
        unavailablePeriod.setCompanyVehicleId(unavailablePeriodDto.getCompanyVehicleId());
        unavailablePeriod.setStartDate(unavailablePeriodDto.getStartDate());
        unavailablePeriod.setEndDate(unavailablePeriodDto.getEndDate());
        repository.save(unavailablePeriod);
        return mapper.objectToDto(unavailablePeriod);
    }
}
