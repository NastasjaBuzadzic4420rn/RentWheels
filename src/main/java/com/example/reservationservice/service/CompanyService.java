package com.example.reservationservice.service;

import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyManagerDto;
import com.example.reservationservice.dto.FilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    Page<CompanyDto> findAll(Pageable pageable);

    CompanyDto findById(Long id);

    Page<CompanyDto> findAllByCityAndName(String city, String name, Pageable pageable);
    Page<CompanyDto> findAllByName(String name, Pageable pageable);

    CompanyDto add(CompanyManagerDto companyManagerDto);

    CompanyDto edit(Long id, CompanyCreateDto companyCreateDto);

    CompanyDto approve(Long id, CompanyManagerDto companyManagerDto);


}
