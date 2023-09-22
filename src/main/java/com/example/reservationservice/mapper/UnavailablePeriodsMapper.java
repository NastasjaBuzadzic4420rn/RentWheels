package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.UnavailablePeriod;
import com.example.reservationservice.dto.UnavailablePeriodDto;
import org.springframework.stereotype.Component;

@Component
public class UnavailablePeriodsMapper {

    public UnavailablePeriod dtoToObject (UnavailablePeriodDto unavailablePeriodDto){
        UnavailablePeriod unavailablePeriod = new UnavailablePeriod();
        unavailablePeriod.setId(unavailablePeriodDto.getId());
        unavailablePeriod.setCompanyVehicleId(unavailablePeriodDto.getCompanyVehicleId());
        unavailablePeriod.setStartDate(unavailablePeriodDto.getStartDate());
        unavailablePeriod.setEndDate(unavailablePeriodDto.getEndDate());
        return unavailablePeriod;
    }

    public UnavailablePeriodDto objectToDto (UnavailablePeriod unavailablePeriod) {
        UnavailablePeriodDto unavailablePeriodDto = new UnavailablePeriodDto();
        unavailablePeriodDto.setId(unavailablePeriod.getId());
        unavailablePeriodDto.setCompanyVehicleId(unavailablePeriod.getCompanyVehicleId());
        unavailablePeriodDto.setStartDate(unavailablePeriod.getStartDate());
        unavailablePeriodDto.setEndDate(unavailablePeriod.getEndDate());
        return unavailablePeriodDto;
    }
}
