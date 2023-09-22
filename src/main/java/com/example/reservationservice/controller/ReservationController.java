package com.example.reservationservice.controller;

import com.example.reservationservice.dto.CreateReservationDto;
import com.example.reservationservice.dto.EditReservationDto;
import com.example.reservationservice.dto.ReservationDto;
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
    public ResponseEntity<Page<ReservationDto>> getAllReservations(Pageable pageable) {
        return new ResponseEntity<>(reservationService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<Page<ReservationDto>> getAllReservationsFromUser(@PathVariable("userId") Long userId, Pageable pageable){
        return new ResponseEntity<>(reservationService.findAllByUser(userId, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> saveReservation(@RequestBody @Valid CreateReservationDto createReservationDto) {
        return new ResponseEntity<>(reservationService.add(createReservationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> editReservation(@PathVariable("id") Long id, @RequestBody EditReservationDto editReservationDto){
        return new ResponseEntity<>(reservationService.edit(id, editReservationDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<ReservationDto> cancelReservation(@PathVariable("id") Long id){
        return new ResponseEntity<>(reservationService.cancel(id), HttpStatus.ACCEPTED);
    }
}
