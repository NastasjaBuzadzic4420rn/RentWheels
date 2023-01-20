package com.raf.rentingnotificationservice.controller;

import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.dto.ReservationDto;
import com.raf.rentingnotificationservice.service.ReservationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<Page<ReservationDto>> getAll(Pageable pageable) {

        return new ResponseEntity<>(reservationService.findAll(pageable), HttpStatus.OK);
    }

    //    @PostMapping
    public void addNew(@RequestBody @Valid ReservationDto reservationDto) {
        reservationService.add(reservationDto);
    }

}
