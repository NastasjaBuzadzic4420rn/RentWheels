package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.VehicleType;
import com.example.reservationservice.dto.VehicleTypeDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {

    public VehicleType dtoToObject (VehicleTypeDto vehicleTypeDto){
        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(vehicleTypeDto.getId());
        vehicleType.setName(vehicleTypeDto.getName());
        return vehicleType;
    }

    public VehicleTypeDto objectToDto(VehicleType vehicleType){
        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        vehicleTypeDto.setId(vehicleType.getId());
        vehicleTypeDto.setName(vehicleType.getName());
        return vehicleTypeDto;
    }
}
