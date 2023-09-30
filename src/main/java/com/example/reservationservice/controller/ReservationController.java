package com.example.reservationservice.controller;

import com.example.reservationservice.dto.CreateReservationDto;
import com.example.reservationservice.dto.EditReservationDto;
import com.example.reservationservice.dto.ReservationDto;
import com.example.reservationservice.security.CheckSecurity;
import com.example.reservationservice.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<ReservationDto>> getAllReservations(Pageable pageable) {
        return new ResponseEntity<>(reservationService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byCompanyVehicle/{companyVehicleId}")
    @CheckSecurity(roles = {"client", "manager", "admin"})
    public ResponseEntity<Page<ReservationDto>> getAllReservationsByCompanyVehicle(@RequestHeader("Authorization") String authorization,
                                                                                   @PathVariable("companyVehicleId") Long companyVehicleId, Pageable pageable){
        return new ResponseEntity<>(reservationService.findAllByCompanyVehicle(companyVehicleId, pageable), HttpStatus.OK);
    }

    @GetMapping("/byUser/{userId}")
    @CheckSecurity(roles = {"client", "admin"})
    public ResponseEntity<Page<ReservationDto>> getAllReservationsFromUser(@RequestHeader("Authorization") String authorization,
                                                                           @PathVariable("userId") Long userId, Pageable pageable){
        return new ResponseEntity<>(reservationService.findAllByUser(userId, pageable), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"client", "manager", "admin"})
    public ResponseEntity<ReservationDto> saveReservation(@RequestHeader("Authorization") String authorization,
                                                          @RequestBody @Valid CreateReservationDto createReservationDto) {
        return new ResponseEntity<>(reservationService.add(createReservationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"client", "manager", "admin"})
    public ResponseEntity<ReservationDto> editReservation(@RequestHeader("Authorization") String authorization,
                                                          @PathVariable("id") Long id, @RequestBody EditReservationDto editReservationDto){
        return new ResponseEntity<>(reservationService.edit(id, editReservationDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cancel/{id}")
    @CheckSecurity(roles = {"client", "manager", "admin"})
    public ResponseEntity<ReservationDto> cancelReservation(@RequestHeader("Authorization") String authorization,
                                                            @PathVariable("id") Long id){
        return new ResponseEntity<>(reservationService.cancel(id), HttpStatus.ACCEPTED);
    }
}
