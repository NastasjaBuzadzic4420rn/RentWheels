package com.raf.rentingnotificationservice.mapper;

import com.raf.rentingnotificationservice.domain.Activation;
import com.raf.rentingnotificationservice.domain.Reservation;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation reservationDtoToReservation(ReservationDto reservationDto){
        Reservation reservation= new Reservation();
        reservation.setFirstName(reservationDto.getFirstName());
        reservation.setLastName(reservationDto.getLastName());
        reservation.setCompany(reservationDto.getCompany());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setManufacturer(reservationDto.getManufacturer());
        reservation.setModel(reservationDto.getModel());
        reservation.setModel(reservationDto.getModel());
        reservation.setVehicleType(reservationDto.getVehicleType());
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setEndDate(reservationDto.getEndDate());
        return reservation;
    }
}
