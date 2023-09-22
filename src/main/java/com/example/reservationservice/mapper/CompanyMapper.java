package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyDto;
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
        companyDto.setManagerId(company.getManagerId());
        return companyDto;
    }

    public Company createDtoToObject(CompanyCreateDto companyCreateDto){
        Company company = new Company();
        company.setId(companyCreateDto.getId());
        company.setName(companyCreateDto.getName());
        company.setDescription(companyCreateDto.getDescription());
        company.setNumOfVehicles(0);
        company.setCity(companyCreateDto.getCity());
        company.setManagerId(companyCreateDto.getManagerId());
        return company;
    }

}
