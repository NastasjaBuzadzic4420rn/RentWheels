package com.example.reservationservice.service.impl;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.FilterDto;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.mapper.CompanyMapper;
import com.example.reservationservice.repository.CompanyRepository;
import com.example.reservationservice.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository repository;
    private CompanyMapper mapper;


    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<CompanyDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }

    @Override
    public Page<CompanyDto> findAllByCityAndName(String city, String name, Pageable pageable) {
        return repository.findAllByCityIgnoreCaseAndNameIsContainingIgnoreCase(city, name, pageable).map(companyPage -> companyPage.map(mapper::objectToDto))
                .orElseThrow(() -> new NotFoundException(String
                        .format("Company with city: %s and name containing: %s not found.", city, name)));
    }

    @Override
    public Page<CompanyDto> findAllByName(String name, Pageable pageable) {
        return repository.findAllByNameIsContainingIgnoreCase(name, pageable).map(companyPage -> companyPage.map(mapper::objectToDto))
                .orElseThrow(() -> new NotFoundException(String
                        .format("Company with city: %s and name containing: %s not found.", name)));
    }

    @Override
    public CompanyDto findById(Long id) {
        Company company = repository.findById(id).get();
        return mapper.objectToDto(company);
    }


    @Override
    public CompanyDto add(CompanyCreateDto companyCreateDto) {
        Company company = mapper.createDtoToObject(companyCreateDto);
        repository.save(company);
        return mapper.objectToDto(company);
    }

    @Override
    public CompanyDto edit(Long id, CompanyCreateDto companyCreateDto) {
        Company company = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Company with id: %s not found.", id)));
        company.setName(companyCreateDto.getName());
        company.setDescription(companyCreateDto.getDescription());
        company.setCity(companyCreateDto.getCity());
        company.setManagerId(companyCreateDto.getManagerId());
        repository.save(company);
        return mapper.objectToDto(company);
    }
}
