package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.CompanyManagerDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyDto objectToDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setDescription(company.getDescription());
        companyDto.setNumOfVehicles(company.getNumOfVehicles());
        companyDto.setCity(company.getCity());
        companyDto.setApproved(company.isApproved());
        companyDto.setManagerId(company.getManagerId());
        return companyDto;
    }

    public Company compManDtoToObject(CompanyManagerDto companyManagerDto){
        Company company = new Company();
        company.setName(companyManagerDto.getName());
        company.setDescription(companyManagerDto.getDescription());
        company.setCity(companyManagerDto.getCity());
        return company;
    }

}
