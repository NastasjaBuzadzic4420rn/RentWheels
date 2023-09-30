package com.example.reservationservice.controller;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.domain.Vehicle;
import com.example.reservationservice.dto.*;
import com.example.reservationservice.security.CheckSecurity;
import com.example.reservationservice.service.CompanyService;
import com.example.reservationservice.service.CompanyVehicleService;
import com.example.reservationservice.service.ReservationService;
import com.example.reservationservice.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<Page<VehicleDto>> getAllVehicles(Pageable pageable) {
        return new ResponseEntity<>(vehicleService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<VehicleDto> saveVehicle(@RequestHeader("Authorization") String authorization,
                                                  @RequestBody @Valid VehicleDto vehicleDto) {
        return new ResponseEntity<>(vehicleService.add(vehicleDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<VehicleDto> editVehicle(@RequestHeader("Authorization") String authorization,
                                                  @PathVariable("id") Long id, @RequestBody VehicleDto vehicleDto){
        return new ResponseEntity<>(vehicleService.edit(id, vehicleDto), HttpStatus.ACCEPTED);
    }


}
