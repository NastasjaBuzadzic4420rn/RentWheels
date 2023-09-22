package com.example.reservationservice.controller;

import com.example.reservationservice.dto.UnavailablePeriodDto;
import com.example.reservationservice.service.UnavailablePeriodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/unavailablePeriods")
public class UnavailablePeriodsController {

    private UnavailablePeriodsService unavailablePeriodsService;

    public UnavailablePeriodsController(UnavailablePeriodsService unavailablePeriodsService) {
        this.unavailablePeriodsService = unavailablePeriodsService;
    }

    @GetMapping
    public ResponseEntity<Page<UnavailablePeriodDto>> getAllUnavailablePeriods(Pageable pageable) {
        return new ResponseEntity<>(unavailablePeriodsService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnavailablePeriodDto> getUnavailablePeriodById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(unavailablePeriodsService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byCompanyVehicle/{companyVehicleId}")
    public ResponseEntity<Page<UnavailablePeriodDto>> getUnavailablePeriodByCompanyVehicleId(@PathVariable("companyVehicleId") Long companyVehicleId, Pageable pageable) {
        return new ResponseEntity<>(unavailablePeriodsService.findByCompanyVehicleId(companyVehicleId, pageable), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UnavailablePeriodDto> saveUnavailablePeriod(@RequestBody @Valid UnavailablePeriodDto unavailablePeriodDto) {
        return new ResponseEntity<>(unavailablePeriodsService.add(unavailablePeriodDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnavailablePeriodDto> editUnavailablePeriod(@PathVariable("id") Long id, @RequestBody UnavailablePeriodDto unavailablePeriodDto){
        return new ResponseEntity<>(unavailablePeriodsService.edit(id, unavailablePeriodDto), HttpStatus.ACCEPTED);
    }


}
