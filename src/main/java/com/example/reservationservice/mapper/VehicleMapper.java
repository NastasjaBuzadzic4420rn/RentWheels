package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.Vehicle;
import com.example.reservationservice.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle dtoToObject (VehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setManufacturer(vehicleDto.getManufacturer());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setVehicleTypeId(vehicleDto.getVehicleTypeId());
        return vehicle;
    }

    public VehicleDto objectToDto (Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setManufacturer(vehicle.getManufacturer());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setVehicleTypeId(vehicle.getVehicleTypeId());
        return vehicleDto;
    }
}
