package com.example.reservationservice.controller;

import com.example.reservationservice.dto.ReservationDto;
import com.example.reservationservice.dto.VehicleTypeDto;
import com.example.reservationservice.service.VehicleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicleType")
public class VehicleTypeController {

    private VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<VehicleTypeDto>> getAllVehicleTypes(Pageable pageable) {
        return new ResponseEntity<>(vehicleTypeService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeDto> getVehicleTypeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehicleTypeDto> saveVehicleType(@RequestBody @Valid VehicleTypeDto vehicleTypeDto) {
        return new ResponseEntity<>(vehicleTypeService.add(vehicleTypeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleTypeDto> editVehicleType(@PathVariable("id") Long id, @RequestBody VehicleTypeDto vehicleTypeDto){
        return new ResponseEntity<>(vehicleTypeService.edit(id, vehicleTypeDto), HttpStatus.ACCEPTED);
    }
}
