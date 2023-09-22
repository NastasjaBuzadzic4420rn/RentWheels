package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.domain.CompanyVehicle;
import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.CompanyVehicleDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyVehicleMapper {

    public CompanyVehicleDto objectToDto(CompanyVehicle companyVehicle){
        CompanyVehicleDto companyVehicleDto = new CompanyVehicleDto();
        companyVehicleDto.setId(companyVehicle.getId());
        companyVehicleDto.setCompanyId(companyVehicle.getCompanyId());
        companyVehicleDto.setVehicleId(companyVehicle.getVehicleId());
        companyVehicleDto.setPrice(companyVehicle.getPrice());
        return companyVehicleDto;
    }

    public  CompanyVehicle dtoToObject(CompanyVehicleDto companyVehicleDto){
        CompanyVehicle companyVehicle = new CompanyVehicle();
        companyVehicle.setId(companyVehicleDto.getId());
        companyVehicle.setCompanyId(companyVehicleDto.getCompanyId());
        companyVehicle.setVehicleId(companyVehicleDto.getVehicleId());
        companyVehicle.setPrice(companyVehicleDto.getPrice());
        return companyVehicle;
    }


}
